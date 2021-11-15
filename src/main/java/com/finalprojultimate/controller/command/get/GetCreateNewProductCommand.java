package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCreateNewProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return Page.CREATE_NEW_PRODUCT_PAGE;
    }
}
