package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.validation.ProductValidator;
import com.finalprojultimate.controller.validation.Validator;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import com.finalprojultimate.model.services.ProductService;
import com.finalprojultimate.model.services.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.finalprojultimate.util.Attribute.ERROR_MESSAGE;
import static com.finalprojultimate.util.Attribute.ERROR_VALIDATION_MESSAGE;
import static com.finalprojultimate.util.Page.EDIT_PRODUCT_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Path.*;

public class PostEditProductCommand extends AbstractCommandWrapper<Product> {
    private static final Logger logger = Logger.getLogger(PostEditProductCommand.class);
    private static final String PRODUCT_UPDATE = "Product with id: %d update successfully!";

    private final ProductService productService = ProductServiceImpl.getInstance();

    private final Validator<Product> validator = new ProductValidator();

    public PostEditProductCommand() {
        super(EDIT_PRODUCT_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product product = getDataFromRequest(request);

        // writeSpecificDataToRequest(request, user);

        if(!validator.isValid(product)){
            extractAndWriteErrorMessagesToRequest(request);
            return EDIT_PRODUCT_PAGE;
        }

        productService.update(product);

        logger.info(String.format(PRODUCT_UPDATE, product.getId()));
        response.sendRedirect(CONTROLLER + "?command=" + SUCCESSFUL_UPDATE_PRODUCT);
        return REDIRECTED;
    }

    @Override
    protected Product getDataFromRequest(HttpServletRequest request) {
        String id = request.getParameter(PRODUCT_ID);
        String name = request.getParameter(PRODUCT_NAME);
        String price = request.getParameter(PRICE);
        String amount = request.getParameter(AMOUNT);
        String unit = request.getParameter(UNIT);
        String barcode = request.getParameter(BARCODE);

        return new Product.Builder()
                .withId(Integer.parseInt(id))
                .withName(name)
                .withPrice(new BigDecimal(price))
                .withAmount(new BigDecimal(amount))
                .withUnit(Unit.getByName(unit))
                .withBarcode(barcode)
                .build();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Product data) {}

    private void extractAndWriteErrorMessagesToRequest(HttpServletRequest request) {
        List<String> errorMessages = validator.getErrorMessages();
        List<String> errorValidationMessages =
                validator.getErrorValidationMessages();
        request.setAttribute(ERROR_MESSAGE, errorMessages);
        request.setAttribute(ERROR_VALIDATION_MESSAGE, errorValidationMessages);
    }
}
