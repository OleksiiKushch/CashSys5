package com.finalprojultimate.controller.command.login;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Attribute.LOGGED_USER;
import static com.finalprojultimate.util.Command.*;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(LOGGED_USER);
        if (user != null) {
            response.sendRedirect(CONTROLLER + "?command=" + MAIN);
            return REDIRECTED;
        }
        return Page.LOGIN_PAGE;
    }
}
