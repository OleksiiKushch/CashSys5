package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.controller.command.login.LoginSubmitCommand;
import com.finalprojultimate.controller.writer.RequestAttributeWriter;
import com.finalprojultimate.util.Attribute;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Path.*;

public class GetChangeLocaleCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetChangeLocaleCommand.class);
    private static final String CHANGE_LOCAL = "Local has been changed to %s!";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter(Attribute.LOCALE);

        request.getSession().setAttribute(Attribute.LOCALE, locale);

        logger.info(String.format(CHANGE_LOCAL, locale));

        response.sendRedirect(CONTROLLER + "?command=" + MAIN);
        return REDIRECTED;
    }
}
