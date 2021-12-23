package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.writer.RequestAttributeWriter;
import com.finalprojultimate.model.service.exception.ServiceException;
import com.finalprojultimate.model.service.impl.UserServiceImpl;
import com.finalprojultimate.model.validation.impl.ReceiptValidator;
import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.receipt.Payment;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.Status;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.impl.ReceiptServiceImpl;
import com.finalprojultimate.model.service.util.Cart;
import com.finalprojultimate.util.MessageKey;
import com.finalprojultimate.util.Parameter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.MessageKey.ERROR_INCORRECT_OR_EMPTY_NUMBER_FIELD;
import static com.finalprojultimate.util.Page.*;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.SUCCESSFUL_CREATE_NEW_RECEIPT;
import static com.finalprojultimate.util.Path.*;

public class PostCreateNewReceiptCommand extends AbstractCommandWrapper<Receipt> {
    private static final Logger logger = Logger.getLogger(PostCreateNewReceiptCommand.class);
    private static final String RECEIPT_CREATE = "New receipt create successfully!";
    private static final String LOGGER_RECEIPT_CANNOT_BE_EMPTY
            = "Creating new receipt failed : receipt cannot be empty (do not contain products)";

    public PostCreateNewReceiptCommand() {
        super(INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestAttributeWriter attributeWriter = new RequestAttributeWriter(request);

        Receipt receipt;
        try {
            receipt = getDataFromRequest(request);
        } catch (ServiceException e) {
            extractAndWriteErrorMessages(attributeWriter, e.getMessage());
            return CREATE_NEW_RECEIPT_PAGE;
        } catch (NumberFormatException e) {
            attributeWriter.writeToRequest(ERROR_MESSAGES, ERROR_INCORRECT_OR_EMPTY_NUMBER_FIELD);
            return CREATE_NEW_RECEIPT_PAGE;
        }

        Validator<Receipt> validator = new ReceiptValidator();
        if(!validator.isValid(receipt)){
            extractAndWriteErrorMessagesFromValidator(attributeWriter, validator);
            return CREATE_NEW_RECEIPT_PAGE;
        }

        ReceiptService receiptService = ReceiptServiceImpl.getInstance();
        // transaction
        Receipt resultReceipt = receiptService.create(receipt, (Cart) request.getSession().getAttribute(CART));

        logger.info(RECEIPT_CREATE);
        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + SUCCESSFUL_CREATE_NEW_RECEIPT
                + AMPERSAND + Parameter.RECEIPT_ID + EQUALS_MARK + resultReceipt.getId());
        return REDIRECTED;
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Receipt data) {}

    @Override
    protected Receipt getDataFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(CART);
        if (cart.getContainer().isEmpty()) {
            throw new ServiceException()
                    .addMessage(MessageKey.ERROR_CART_IS_EMPTY)
                    .addLogMessage(LOGGER_RECEIPT_CANNOT_BE_EMPTY)
                    .setClassThrowsException(PostCreateNewReceiptCommand.class);
        }
        BigDecimal sum = cart.getSum();

        String strPaid = request.getParameter(PAID);
        BigDecimal paid;
        if (strPaid == null) {
            paid = sum;
        } else {
            paid = new BigDecimal(request.getParameter(PAID));
        }

        BigDecimal change = getChange(paid, sum);
        String payment = request.getParameter(PAYMENT);
        User user = (User) session.getAttribute(LOGGED_USER);

        return new Receipt.Builder()
                .withChange(change)
                .withPayment(Payment.getByName(payment))
                .withUserId(user.getId())
                .withStatus(Status.NORMAL)
                .build();
    }

    private BigDecimal getChange(BigDecimal paid, BigDecimal sum) {
        return paid.subtract(sum);
    }
}
