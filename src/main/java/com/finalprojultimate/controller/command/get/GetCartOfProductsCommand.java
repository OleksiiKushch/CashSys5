package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;

import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.service.util.Cart;
import com.finalprojultimate.util.Attribute;
import com.finalprojultimate.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static com.finalprojultimate.util.Attribute.CART;

public class GetCartOfProductsCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetCartOfProductsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute(CART);
        Collection<Product> products = cart.getContainer().values();

        request.setAttribute(Attribute.PRODUCTS, products);

        return Page.CART_OF_PRODUCTS_PAGE;
    }
}
