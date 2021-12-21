package com.finalprojultimate.controller.command.get.general;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

import static com.finalprojultimate.util.Attribute.LOGGED_USER;
import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.MAIN;
import static com.finalprojultimate.util.Path.*;

public class GetLogoutCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetLogoutCommand.class);
    private static final String USER_LOGOUT = "User %s logout!";
    private static final String USER_NULL_LOGOUT = "User logout!";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(LOGGED_USER);

        clearAllSessionAttributes(request);

        if (user != null) {
            logger.info(String.format(USER_LOGOUT, user.getEmail()));
        } else {
            logger.info(USER_NULL_LOGOUT);
        }

        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + MAIN);
        return REDIRECTED;
    }

    private void clearAllSessionAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributes = session.getAttributeNames();
        while(sessionAttributes.hasMoreElements()){
            session.setAttribute(sessionAttributes.nextElement(),null);
        }
    }
}
