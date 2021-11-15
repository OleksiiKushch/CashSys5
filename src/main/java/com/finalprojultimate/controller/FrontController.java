package com.finalprojultimate.controller;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.controller.command.CommandHolder;
import com.finalprojultimate.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "FrontController", value = "/FrontController")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 3052404603089713444L;

    private static final Logger logger = Logger.getLogger(FrontController.class);

    private static final String COMMAND = "command";
    private static final String DELIMITER = ":";

    private final CommandHolder commands;

    public FrontController() {
        commands = new CommandHolder();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = getCommandFromRequest(request);
        logger.info("command: " + command.getClass().getSimpleName());
        String viewPage = command.execute(request, response);
        logger.info(viewPage);
        if(!isRedirected(viewPage)) {
            request.getRequestDispatcher(viewPage)
                    .forward(request, response);
        }
    }

    private Command getCommandFromRequest(HttpServletRequest request) {
        String key = getKeyForCommand(request);
        return commands.getCommandByKey(key);
    }

    private String getKeyForCommand(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getParameter(COMMAND);
        logger.info("key: " + method + DELIMITER + path);
        return method + DELIMITER + path;
    }

    private boolean isRedirected(String viewPage){
        return viewPage.equals(Path.REDIRECTED);
    }
}
