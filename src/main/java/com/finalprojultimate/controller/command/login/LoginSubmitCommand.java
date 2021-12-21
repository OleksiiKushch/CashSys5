package com.finalprojultimate.controller.command.login;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.model.service.exception.ServiceException;
import com.finalprojultimate.model.validation.impl.LoginValidator;
import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.controller.writer.RequestAttributeWriter;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.service.UserService;
import com.finalprojultimate.model.service.impl.UserServiceImpl;
import com.finalprojultimate.model.service.util.LoginData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.Page.LOGIN_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.MAIN;
import static com.finalprojultimate.util.Path.*;

public class LoginSubmitCommand extends AbstractCommandWrapper<LoginData> {
    private static final Logger logger = Logger.getLogger(LoginSubmitCommand.class);
    private static final String USER_LOGGED_IN = "User %s logged in!";

    public LoginSubmitCommand() {
        super(LOGIN_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestAttributeWriter attributeWriter = new RequestAttributeWriter(request);

        LoginData loginData = getDataFromRequest(request);
        writeSpecificDataToRequest(request, loginData);
        Validator<LoginData> loginValidator = new LoginValidator();
        if (!loginValidator.isValid(loginData)) {
            extractAndWriteErrorMessagesFromValidator(attributeWriter, loginValidator);
            return LOGIN_PAGE;
        }

        UserService userService = UserServiceImpl.getInstance();
        User user;
        try {
            user = userService.login(loginData);
        } catch (ServiceException e) {
            extractAndWriteErrorMessages(attributeWriter, e.getMessage());
            return LOGIN_PAGE;
        }

        attributeWriter.writeToSession(LOGGED_USER, user);

        logger.info(String.format(USER_LOGGED_IN, user.getEmail()));

        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + MAIN);
        return REDIRECTED;
    }

    @Override
    protected LoginData getDataFromRequest(HttpServletRequest request) {
        String login = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        return new LoginData(login, password);
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, LoginData data) {
        request.setAttribute(LOGIN_DATA, data);
    }
}
