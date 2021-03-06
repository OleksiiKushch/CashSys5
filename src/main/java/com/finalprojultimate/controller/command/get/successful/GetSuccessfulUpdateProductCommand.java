package com.finalprojultimate.controller.command.get.successful;

import com.finalprojultimate.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Page.SUCCESSFUL_UPDATE_PRODUCT_PAGE;
import static com.finalprojultimate.util.Parameter.PRODUCT_ID;

public class GetSuccessfulUpdateProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute(PRODUCT_ID);

        return SUCCESSFUL_UPDATE_PRODUCT_PAGE;
    }
}
