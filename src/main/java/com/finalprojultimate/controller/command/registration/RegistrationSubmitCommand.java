package com.finalprojultimate.controller.command.registration;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.validation.impl.PasswordConfirmationValidator;
import com.finalprojultimate.controller.validation.impl.RegistrationValidator;
import com.finalprojultimate.controller.validation.Validator;
import com.finalprojultimate.model.entity.user.Role;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.service.UserService;
import com.finalprojultimate.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.Page.REGISTRATION_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Path.*;

public class RegistrationSubmitCommand extends AbstractCommandWrapper<User> {
    private static final Logger logger = Logger.getLogger(RegistrationSubmitCommand.class);
    private static final String USER_SIGNED_UP = "User %s registered successfully!";

    private final UserService userService = UserServiceImpl.getInstance();

    private final Validator<User> validator = new RegistrationValidator();
    private final Validator<Entry<String, String>> validatorPassword = new PasswordConfirmationValidator();

    public RegistrationSubmitCommand() {
        super(REGISTRATION_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getDataFromRequest(request);

        if (user == null) {
            return REGISTRATION_PAGE;
        }

        if(!validator.isValid(user)){
            extractAndWriteErrorMessagesToRequest(request);
            return REGISTRATION_PAGE;
        }

        userService.create(user);

        logger.info(String.format(USER_SIGNED_UP, user.getEmail()));
        response.sendRedirect(CONTROLLER + "?command=" + SUCCESSFUL_REGISTRATION);
        return REDIRECTED;
    }

    @Override
    protected User getDataFromRequest(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String firstName = request.getParameter(FIRST_NAME);
        String middleName = request.getParameter(MIDDLE_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String password = request.getParameter(PASSWORD);
        String confirmationPassword  = request.getParameter(CONFIRMATION_PASSWORD);
        String role = request.getParameter(ROLE);

        Entry<String, String> pairPassword = new AbstractMap.SimpleEntry<>(password, confirmationPassword);

        if (!validatorPassword.isValid(pairPassword)) {
            extractAndWriteErrorMessagesToRequest(request);
            return null;
        }

        // user class field passHash temporarily (until validation is complete)
        // stores the password instead of the passHash
        return new User.Builder()
                .withEmail(email)
                .withFirstName(firstName)
                .withMiddleName(middleName)
                .withLastName(lastName)
                .withPassHash(password)
                .withRole(Role.getByName(role))
                .build();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, User user) {}

    private void extractAndWriteErrorMessagesToRequest(HttpServletRequest request) {
        List<String> errorMessages = validator.getErrorMessages();
        List<String> errorValidationMessages =
                validator.getErrorValidationMessages();
        request.setAttribute(ERROR_MESSAGE, errorMessages);
        request.setAttribute(ERROR_VALIDATION_MESSAGE, errorValidationMessages);
    }
}
