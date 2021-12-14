package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.service.ProductService;
import com.finalprojultimate.model.service.impl.ProductServiceImpl;
import com.finalprojultimate.model.service.util.Cart;
import com.finalprojultimate.util.Attribute;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Path.*;

public class GetAddProductToCartCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetAddProductToCartCommand.class);
    private static final String PRODUCT_ADD_TO_CART =
            "Product with id: %d was successfully added to the cart in amount ";

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter(PRODUCT_ID);
        Product product = productService.getById(Integer.parseInt(id));
        String amount = request.getParameter(Attribute.AMOUNT);
        BigDecimal amountProducts = new BigDecimal(amount);
        product.setAmount(amountProducts);

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute(CART);
        cart.put(product.getId(), product);
        session.setAttribute(CART, cart);

        logger.info(String.format(PRODUCT_ADD_TO_CART + product.getAmount() + " " +
                        product.getUnit().getName(), product.getId()));

        response.sendRedirect(CONTROLLER + "?command=" + CREATE_NEW_RECEIPT);
        return REDIRECTED;
    }

}
