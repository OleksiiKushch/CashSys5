package com.finalprojultimate.controller.command.get.general;

import com.finalprojultimate.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

import static com.finalprojultimate.util.Path.*;

public class GetLogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        clearAllSessionAttributes(request);

        response.sendRedirect(CONTROLLER + "?command=" + MAIN);
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
