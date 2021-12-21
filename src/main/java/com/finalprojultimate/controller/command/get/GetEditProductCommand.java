package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.service.ProductService;
import com.finalprojultimate.model.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Attribute.PRODUCT;
import static com.finalprojultimate.util.Page.EDIT_PRODUCT_PAGE;
import static com.finalprojultimate.util.Parameter.PRODUCT_ID;

public class GetEditProductCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetEditProductCommand.class);

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String strProductId = request.getParameter(PRODUCT_ID);
        Integer productId;
        if (!strProductId.isEmpty()) {
            productId = Integer.parseInt(strProductId);
        } else {
            productId = (Integer) request.getSession().getAttribute(PRODUCT_ID);
        }

        ProductService productService = ProductServiceImpl.getInstance();
        Product product = productService.getById(productId);
        request.getSession().setAttribute(PRODUCT_ID, productId);
        request.setAttribute(PRODUCT, product);
        return EDIT_PRODUCT_PAGE;
    }
}
