package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.model.validation.impl.ProductValidator;
import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import com.finalprojultimate.model.service.ProductService;
import com.finalprojultimate.model.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.finalprojultimate.util.Attribute.ERROR_MESSAGES;
import static com.finalprojultimate.util.Attribute.ERROR_VALIDATION_MESSAGES;
import static com.finalprojultimate.util.Page.CREATE_NEW_PRODUCT_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.SUCCESSFUL_CREATE_NEW_PRODUCT;
import static com.finalprojultimate.util.Path.*;

public class PostCreateNewProductCommand extends AbstractCommandWrapper<Product> {
    private static final Logger logger = Logger.getLogger(PostCreateNewProductCommand.class);
    private static final String PRODUCT_SIGNED_UP = "New product with id: %d registered successfully!";

    private final ProductService productService = ProductServiceImpl.getInstance();

    private final Validator<Product> validator = new ProductValidator();

    public PostCreateNewProductCommand() {
        super(CREATE_NEW_PRODUCT_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Product product = getDataFromRequest(request);

        // writeSpecificDataToRequest(request, user);

        if(!validator.isValid(product)){
            extractAndWriteErrorMessagesToRequest(request);
            return CREATE_NEW_PRODUCT_PAGE;
        }

        productService.create(product);

        logger.info(String.format(PRODUCT_SIGNED_UP, product.getId()));
        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + SUCCESSFUL_CREATE_NEW_PRODUCT);
        return REDIRECTED;
    }

    @Override
    protected Product getDataFromRequest(HttpServletRequest request) {
        String name = request.getParameter(PRODUCT_NAME);
        String price = request.getParameter(PRICE);
        String amount = request.getParameter(AMOUNT);
        String unit = request.getParameter(UNIT);
        String barcode = request.getParameter(BARCODE);

        return new Product.Builder()
                .withName(name)
                .withPrice(new BigDecimal(price))
                .withAmount(new BigDecimal(amount))
                .withUnit(Unit.getByName(unit))
                .withBarcode(barcode)
                .build();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Product data) {
        // request.setAttribute(PREVIOUS_PRODUCT, product);
    }

    private void extractAndWriteErrorMessagesToRequest(HttpServletRequest request) {
        List<String> errorMessages = validator.getErrorMessages();
        List<String> errorValidationMessages =
                validator.getErrorValidationMessages();
        request.setAttribute(ERROR_MESSAGES, errorMessages);
        request.setAttribute(ERROR_VALIDATION_MESSAGES, errorValidationMessages);
    }
}
