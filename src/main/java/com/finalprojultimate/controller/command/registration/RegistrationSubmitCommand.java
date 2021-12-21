package com.finalprojultimate.controller.command.registration;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.writer.RequestAttributeWriter;
import com.finalprojultimate.model.service.exception.ServiceException;
import com.finalprojultimate.model.validation.impl.PasswordConfirmationValidator;
import com.finalprojultimate.model.validation.impl.RegistrationValidator;
import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.service.UserService;
import com.finalprojultimate.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.MessageKey.ERROR_IS_NOT_SAME_CONFIRMATION_PASSWORD;
import static com.finalprojultimate.util.Page.REGISTRATION_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.SUCCESSFUL_REGISTRATION;
import static com.finalprojultimate.util.Path.*;

public class RegistrationSubmitCommand extends AbstractCommandWrapper<User> {
    private static final Logger logger = Logger.getLogger(RegistrationSubmitCommand.class);
    private static final String USER_SIGNED_UP = "User %s registered successfully!";

    public RegistrationSubmitCommand() {
        super(REGISTRATION_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestAttributeWriter attributeWriter = new RequestAttributeWriter(request);

        User user = getDataFromRequest(request);
        writeSpecificDataToRequest(request, user);

        String password = request.getParameter(PASSWORD);
        String confirmationPassword  = request.getParameter(CONFIRMATION_PASSWORD);
        Entry<String, String> pairPassword = new AbstractMap.SimpleEntry<>(password, confirmationPassword);
        Validator<Entry<String, String>> validatorPassword = new PasswordConfirmationValidator();
        if (!validatorPassword.isValid(pairPassword)) {
            attributeWriter.writeToRequest(ERROR_MESSAGES, ERROR_IS_NOT_SAME_CONFIRMATION_PASSWORD);
            return REGISTRATION_PAGE;
        }

        Validator<User> validator = new RegistrationValidator();
        if(!validator.isValid(user)){
            extractAndWriteErrorMessagesFromValidator(attributeWriter, validator);
            return REGISTRATION_PAGE;
        }

        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.create(user);
        } catch (ServiceException e) {
            extractAndWriteErrorMessages(attributeWriter, e.getMessage());
            return REGISTRATION_PAGE;
        }

        logger.info(String.format(USER_SIGNED_UP, user.getEmail()));
        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + SUCCESSFUL_REGISTRATION);
        return REDIRECTED;
    }

    @Override
    protected User getDataFromRequest(HttpServletRequest request) {
        List<String> parameters = User.getListStrFormatParameters();
        List<String> listStrFormatAttributes = new ArrayList<>();
        for (String parameter : parameters) {
            listStrFormatAttributes.add(request.getParameter(parameter));
        }

        // user class field passHash temporarily (until validation is complete)
        // stores the password instead of the passHash
        return User.mapUser(listStrFormatAttributes);
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, User user) {
        request.setAttribute(REGISTRATION_DATA, user);
    }
}
