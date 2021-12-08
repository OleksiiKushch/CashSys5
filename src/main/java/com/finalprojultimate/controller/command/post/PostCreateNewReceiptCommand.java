package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.validation.ReceiptValidator;
import com.finalprojultimate.controller.validation.Validator;
import com.finalprojultimate.model.entity.receipt.Payment;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.Status;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.ReceiptService;
import com.finalprojultimate.model.services.impl.ReceiptServiceImpl;
import com.finalprojultimate.model.services.util.Cart;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.Page.CREATE_NEW_RECEIPT_PAGE;
import static com.finalprojultimate.util.Page.INTERNAL_SERVER_ERROR_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Path.*;

public class PostCreateNewReceiptCommand extends AbstractCommandWrapper<Receipt> {
    private static final Logger logger = Logger.getLogger(PostCreateNewReceiptCommand.class);
    private static final String RECEIPT_CREATE = "New receipt create successfully!";

    private final ReceiptService receiptService = ReceiptServiceImpl.getInstance();

    private final Validator<Receipt> validator = new ReceiptValidator();

    public PostCreateNewReceiptCommand() {
        super(INTERNAL_SERVER_ERROR_PAGE);
    } // TODO

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Receipt receipt = getDataFromRequest(request);

        if(!validator.isValid(receipt)){
            extractAndWriteErrorMessagesToRequest(request);
            return CREATE_NEW_RECEIPT_PAGE;
        }

        // transaction
        receiptService.create(receipt, (Cart) request.getSession().getAttribute(CART));

        logger.info(RECEIPT_CREATE);
        response.sendRedirect(CONTROLLER + "?command=" + SUCCESSFUL_CREATE_NEW_RECEIPT);
        return REDIRECTED;
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Receipt data) {}

    @Override
    protected Receipt getDataFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(CART);
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



    private void extractAndWriteErrorMessagesToRequest(HttpServletRequest request) {
        List<String> errorMessages = validator.getErrorMessages();
        List<String> errorValidationMessages =
                validator.getErrorValidationMessages();
        request.setAttribute(ERROR_MESSAGE, errorMessages);
        request.setAttribute(ERROR_VALIDATION_MESSAGE, errorValidationMessages);
    }
}
