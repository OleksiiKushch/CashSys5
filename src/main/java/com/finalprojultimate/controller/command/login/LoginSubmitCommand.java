package com.finalprojultimate.controller.command.login;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.validation.LoginValidator;
import com.finalprojultimate.controller.validation.Validator;
import com.finalprojultimate.controller.writer.RequestAttributeWriter;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.UserService;
import com.finalprojultimate.model.services.impl.UserServiceImpl;
import com.finalprojultimate.model.services.util.LoginData;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.Page.LOGIN_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Path.*;

public class LoginSubmitCommand extends AbstractCommandWrapper<LoginData> {
    private static final Logger logger = Logger.getLogger(LoginSubmitCommand.class);
    private static final String USER_LOGGED_IN = "User %s logged in!";

    private final UserService userService = UserServiceImpl.getInstance();

    private final Validator<LoginData> loginValidator = new LoginValidator();
    private RequestAttributeWriter attributeWriter;

    public LoginSubmitCommand() {
        super(LOGIN_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        attributeWriter = new RequestAttributeWriter(request);
        LoginData loginData = getDataFromRequest(request);
        writeSpecificDataToRequest(request, loginData);

        if (!loginValidator.isValid(loginData)) {
            extractAndWriteErrorMessages();
            return LOGIN_PAGE;
        }

        User user = userService.login(loginData);
        attributeWriter.writeToSession(LOGGED_USER, user);

        logger.info(String.format(USER_LOGGED_IN, user.getEmail()));

        response.sendRedirect(CONTROLLER + "?command=" + MAIN);
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
        attributeWriter.writeToRequest(LOGIN_DATA, data);
    }

    private void extractAndWriteErrorMessages() {
        List<String> errorMessages = loginValidator.getErrorMessages();
        List<String> errorValidationMessages = loginValidator.getErrorValidationMessages();
        attributeWriter.writeToRequest(ERROR_MESSAGE, errorMessages);
        attributeWriter.writeToRequest(ERROR_VALIDATION_MESSAGE, errorValidationMessages);
    }
}
