package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.writer.RequestAttributeWriter;
import com.finalprojultimate.model.service.exception.ServiceException;
import com.finalprojultimate.model.validation.impl.ProductValidator;
import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import com.finalprojultimate.model.service.ProductService;
import com.finalprojultimate.model.service.impl.ProductServiceImpl;
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
import static com.finalprojultimate.util.Page.EDIT_PRODUCT_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.SUCCESSFUL_UPDATE_PRODUCT;
import static com.finalprojultimate.util.Path.*;

public class PostEditProductCommand extends AbstractCommandWrapper<Product> {
    private static final Logger logger = Logger.getLogger(PostEditProductCommand.class);
    private static final String PRODUCT_UPDATE = "Product with id: %d update successfully!";

    public PostEditProductCommand() {
        super(EDIT_PRODUCT_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestAttributeWriter attributeWriter = new RequestAttributeWriter(request);

        Product product;
        try {
            product = getDataFromRequest(request);
        } catch (NumberFormatException e) {
            attributeWriter.writeToRequest(ERROR_MESSAGES, ERROR_INCORRECT_OR_EMPTY_NUMBER_FIELD);
            return EDIT_PRODUCT_PAGE;
        }

        writeSpecificDataToRequest(request, product);

        Validator<Product> validator = new ProductValidator();
        if(!validator.isValid(product)){
            extractAndWriteErrorMessagesFromValidator(attributeWriter, validator);
            return EDIT_PRODUCT_PAGE;
        }

        ProductService productService = ProductServiceImpl.getInstance();
        try {
            productService.update(product);
        } catch (ServiceException e) {
            extractAndWriteErrorMessages(attributeWriter, e.getMessage());
            return EDIT_PRODUCT_PAGE;
        }

        logger.info(String.format(PRODUCT_UPDATE, product.getId()));
        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + SUCCESSFUL_UPDATE_PRODUCT);
        return REDIRECTED;
    }

    @Override
    protected Product getDataFromRequest(HttpServletRequest request) {
        List<String> parameters = Product.getListStrFormatParameters();
        List<String> listStrFormatAttributes = new ArrayList<>();
        for (String parameter : parameters) {
            listStrFormatAttributes.add(request.getParameter(parameter));
        }
        Product result = Product.mapProduct(listStrFormatAttributes);
        result.setId((Integer) request.getSession().getAttribute(PRODUCT_ID));

        return result;
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Product product) {
        request.setAttribute(PRODUCT_DATA, product);
    }
}
