package com.finalprojultimate.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.finalprojultimate.util.Page.MAIN_PAGE;

public class UnsupportedPathCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        return MAIN_PAGE;
    }
}
