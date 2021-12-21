package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.writer.RequestAttributeWriter;
import com.finalprojultimate.model.service.exception.ServiceException;
import com.finalprojultimate.model.validation.impl.ProductValidator;
import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.service.ProductService;
import com.finalprojultimate.model.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.MessageKey.ERROR_INCORRECT_OR_EMPTY_NUMBER_FIELD;
import static com.finalprojultimate.util.Page.CREATE_NEW_PRODUCT_PAGE;
import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.SUCCESSFUL_CREATE_NEW_PRODUCT;
import static com.finalprojultimate.util.Path.*;

public class PostCreateNewProductCommand extends AbstractCommandWrapper<Product> {
    private static final Logger logger = Logger.getLogger(PostCreateNewProductCommand.class);
    private static final String PRODUCT_SIGNED_UP = "New product with id: %d registered successfully!";

    public PostCreateNewProductCommand() {
        super(CREATE_NEW_PRODUCT_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestAttributeWriter attributeWriter = new RequestAttributeWriter(request);

        Product product;
        try {
            product = getDataFromRequest(request);
        } catch (NumberFormatException e) {
            attributeWriter.writeToRequest(ERROR_MESSAGES, ERROR_INCORRECT_OR_EMPTY_NUMBER_FIELD);
            return CREATE_NEW_PRODUCT_PAGE;
        }

        writeSpecificDataToRequest(request, product);

        Validator<Product> validator = new ProductValidator();
        if(!validator.isValid(product)){
            extractAndWriteErrorMessagesFromValidator(attributeWriter, validator);
            return CREATE_NEW_PRODUCT_PAGE;
        }

        ProductService productService = ProductServiceImpl.getInstance();
        try {
            productService.create(product);
        } catch (ServiceException e) {
            extractAndWriteErrorMessages(attributeWriter, e.getMessage());
            return CREATE_NEW_PRODUCT_PAGE;
        }

        logger.info(String.format(PRODUCT_SIGNED_UP, product.getId()));
        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + SUCCESSFUL_CREATE_NEW_PRODUCT);
        return REDIRECTED;
    }

    @Override
    protected Product getDataFromRequest(HttpServletRequest request) {
        List<String> parameters = Product.getListStrFormatParameters();
        List<String> listStrFormatAttributes = new ArrayList<>();
        for (String parameter : parameters) {
            listStrFormatAttributes.add(request.getParameter(parameter));
        }

        return Product.mapProduct(listStrFormatAttributes);
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Product product) {
        request.setAttribute(PRODUCT_DATA, product);
    }
}
