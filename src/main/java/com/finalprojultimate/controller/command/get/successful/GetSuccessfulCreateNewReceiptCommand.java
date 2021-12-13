package com.finalprojultimate.controller.command.get.successful;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetSuccessfulCreateNewReceiptCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return Page.SUCCESSFUL_CREATE_NEW_RECEIPT_PAGE;
    }
}
