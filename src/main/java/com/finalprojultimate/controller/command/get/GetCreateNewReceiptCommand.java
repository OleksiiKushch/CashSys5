package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.service.util.Cart;
import com.finalprojultimate.util.Attribute;
import com.finalprojultimate.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static com.finalprojultimate.util.Attribute.CART;

public class GetCreateNewReceiptCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetCreateNewReceiptCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute(CART);
        if (cart == null) {
            cart = new Cart();
        }
        session.setAttribute(CART, cart);

        BigDecimal sum = cart.getSum();
        request.setAttribute(Attribute.SUM, sum);

        return Page.CREATE_NEW_RECEIPT_PAGE;
    }
}
