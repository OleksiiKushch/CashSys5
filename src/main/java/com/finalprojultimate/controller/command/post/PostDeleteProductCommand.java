package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.services.ProductService;
import com.finalprojultimate.model.services.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Page.*;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Path.*;

public class PostDeleteProductCommand extends AbstractCommandWrapper<Product> {
    private static final Logger logger = Logger.getLogger(PostDeleteProductCommand.class);
    private static final String PRODUCT_DELETE = "Product with id: %d delete successfully!";

    private final ProductService productService = ProductServiceImpl.getInstance();

    public PostDeleteProductCommand() {
        super(INTERNAL_SERVER_ERROR_PAGE);
    } // TODO

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product product = getDataFromRequest(request);

        // writeSpecificDataToRequest(request, user);

        productService.delete(product);

        logger.info(String.format(PRODUCT_DELETE, product.getId()));
        response.sendRedirect(CONTROLLER + "?command=" + SUCCESSFUL_DELETE_PRODUCT);
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

//    private void extractAndWriteErrorMessagesToRequest(HttpServletRequest request) {}
}
