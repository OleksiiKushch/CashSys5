package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Attribute.CART;
import static com.finalprojultimate.util.Path.*;

public class GetNewReceiptCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(CART);
        response.sendRedirect(CONTROLLER + "?command=" + CREATE_NEW_RECEIPT);
        return REDIRECTED;
    }
}
