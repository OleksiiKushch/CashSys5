package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.writer.RequestAttributeWriter;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.validation.impl.ReceiptPropertiesValidator;
import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.impl.ReceiptServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.MessageKey.ERROR_INCORRECT_OR_EMPTY_NUMBER_FIELD;
import static com.finalprojultimate.util.Page.CREATE_NEW_PRODUCT_PAGE;
import static com.finalprojultimate.util.Page.SET_GLOBAL_RECEIPT_PROPERTIES_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.SUCCESSFUL_SET_GLOBAL_RECEIPT_PROPERTIES;
import static com.finalprojultimate.util.Path.*;

public class PostSetGlobalReceiptPropertiesCommand extends AbstractCommandWrapper<ReceiptDetails> {
    private static final Logger logger = Logger.getLogger(PostSetGlobalReceiptPropertiesCommand.class);
    private static final String SET_GLOBAL_RECEIPT_PROPERTIES = "Global receipt properties set successfully!";

    public PostSetGlobalReceiptPropertiesCommand() {
        super(SET_GLOBAL_RECEIPT_PROPERTIES_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestAttributeWriter attributeWriter = new RequestAttributeWriter(request);

        ReceiptDetails globalReceiptProperties;
        try {
            globalReceiptProperties = getDataFromRequest(request);
        } catch (NumberFormatException e) {
            attributeWriter.writeToRequest(ERROR_MESSAGES, ERROR_INCORRECT_OR_EMPTY_NUMBER_FIELD);
            return SET_GLOBAL_RECEIPT_PROPERTIES_PAGE;
        }

        writeSpecificDataToRequest(request, globalReceiptProperties);

        Validator<ReceiptDetails> validator = new ReceiptPropertiesValidator();
        if(!validator.isValid(globalReceiptProperties)){
            extractAndWriteErrorMessagesFromValidator(attributeWriter, validator);
            return SET_GLOBAL_RECEIPT_PROPERTIES_PAGE;
        }

        ReceiptService receiptService = ReceiptServiceImpl.getInstance();
        receiptService.setGlobalReceiptProperties(globalReceiptProperties);

        logger.info(SET_GLOBAL_RECEIPT_PROPERTIES);
        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK
                + SUCCESSFUL_SET_GLOBAL_RECEIPT_PROPERTIES);
        return REDIRECTED;
    }

    @Override
    protected ReceiptDetails getDataFromRequest(HttpServletRequest request) {
        List<String> parameters = ReceiptDetails.getListStrFormatParametersGlobalReceiptProperties();
        List<String> listStrFormatAttributes = new ArrayList<>();
        for (String parameter : parameters) {
            listStrFormatAttributes.add(request.getParameter(parameter));
        }

        return ReceiptDetails.mapGlobalReceiptProperties(listStrFormatAttributes);
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, ReceiptDetails globalReceiptProperties) {
        request.setAttribute(GLOBAL_RECEIPT_PROPERTIES_DATA, globalReceiptProperties);
    }
}
