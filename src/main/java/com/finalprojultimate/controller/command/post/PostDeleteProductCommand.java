package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.writer.RequestAttributeWriter;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.service.ProductService;
import com.finalprojultimate.model.service.exception.ServiceException;
import com.finalprojultimate.model.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Page.*;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.SUCCESSFUL_DELETE_PRODUCT;
import static com.finalprojultimate.util.Path.*;

public class PostDeleteProductCommand extends AbstractCommandWrapper<Product> {
    private static final Logger logger = Logger.getLogger(PostDeleteProductCommand.class);
    private static final String PRODUCT_DELETE = "Product with id: %d delete successfully!";

    public PostDeleteProductCommand() {
        super(INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestAttributeWriter attributeWriter = new RequestAttributeWriter(request);

        Product product = getDataFromRequest(request);

        ProductService productService = ProductServiceImpl.getInstance();
        try {
            productService.delete(product);
        } catch (ServiceException e) {
            extractAndWriteErrorMessages(attributeWriter, e.getMessage());
            return INTERNAL_SERVER_ERROR_PAGE;
        }

        logger.info(String.format(PRODUCT_DELETE, product.getId()));
        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + SUCCESSFUL_DELETE_PRODUCT);
        return REDIRECTED;
    }

    @Override
    protected Product getDataFromRequest(HttpServletRequest request) {
        String id = request.getParameter(PRODUCT_ID);

        return new Product.Builder()
                .withId(Integer.parseInt(id))
                .build();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Product data) {}
}
