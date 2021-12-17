package com.finalprojultimate.controller.command.get.general;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.util.Attribute;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.MAIN;
import static com.finalprojultimate.util.Path.*;

public class GetChangeLocaleCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetChangeLocaleCommand.class);
    private static final String CHANGE_LOCAL = "Local has been changed to %s!";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter(Attribute.LOCALE);

        request.getSession().setAttribute(Attribute.LOCALE, locale);

        logger.info(String.format(CHANGE_LOCAL, locale));

        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + MAIN);
        return REDIRECTED;
    }
}
