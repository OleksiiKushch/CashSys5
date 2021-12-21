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
import java.math.BigDecimal;
import java.util.Collection;

import static com.finalprojultimate.util.Attribute.CART;
import static com.finalprojultimate.util.Parameter.AMOUNT;
import static com.finalprojultimate.util.Parameter.PRODUCT_ID;

public class GetEditProductFromCartCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetEditProductFromCartCommand.class);
    private static final String PRODUCT_EDIT_FROM_CART = "Product with id: %d was successfully update (amount) to cart!";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter(PRODUCT_ID);
        Integer id = Integer.valueOf(productId);

        String productAmount = request.getParameter(AMOUNT);

        Cart cart = (Cart) request.getSession().getAttribute(CART);
        cart.updateAmount(id, new BigDecimal(productAmount));

        request.getSession().setAttribute(CART, cart);

        logger.info(String.format(PRODUCT_EDIT_FROM_CART, Integer.parseInt(productId)));

        Collection<Product> products = cart.getContainer().values();
        request.setAttribute(Attribute.PRODUCTS, products);

        return Page.CART_OF_PRODUCTS_PAGE;
    }
}
