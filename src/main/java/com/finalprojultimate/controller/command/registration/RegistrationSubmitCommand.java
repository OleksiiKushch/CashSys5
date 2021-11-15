package com.finalprojultimate.controller.command.registration;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.validation.RegistrationValidator;
import com.finalprojultimate.controller.validation.Validator;
import com.finalprojultimate.model.entity.user.Role;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.security.BCryptEncryptor;
import com.finalprojultimate.model.services.UserService;
import com.finalprojultimate.model.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.finalprojultimate.util.Attribute.*;
import static com.finalprojultimate.util.Page.REGISTRATION_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Path.*;

public class RegistrationSubmitCommand extends AbstractCommandWrapper<User> {
    private static final Logger logger = Logger.getLogger(RegistrationSubmitCommand.class);
    private static final String USER_SIGNED_UP = "User %s registered successfully!";

    private final UserService userService = UserServiceImpl.getInstance();

    private final Validator<User> validator = new RegistrationValidator();

    public RegistrationSubmitCommand() {
        super(REGISTRATION_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getDataFromRequest(request);

        // writeSpecificDataToRequest(request, user);

        if(!validator.isValid(user)){
            extractAndWriteErrorMessagesToRequest(request);
            return REGISTRATION_PAGE;
        }

        user.setPassHash(encryptUserPassword(user.getPassHash()));

        userService.registration(user);

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

        if (false) {
            // TODO check password on confirmation
        }

        // user class field passHash temporarily (until validation is complete)
        // stores the password instead of the passHash
        return new User.Builder()
                .withEmail(email)
                .withFirstName(firstName)
                .withMiddleName(middleName)
                .withLastName(lastName)
                .withPassHash(password)
                .withRoleId(Role.getByName(role))
                .build();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, User user) {
        // request.setAttribute(PREVIOUS_PERSON, user);
    }

    private String encryptUserPassword(String password) {
        BCryptEncryptor encryptor = new BCryptEncryptor();
        return encryptor.encryptPassword(password);
    }

    private void extractAndWriteErrorMessagesToRequest(HttpServletRequest request) {
        List<String> errorMessages = validator.getErrorMessages();
        List<String> errorValidationMessages =
                validator.getErrorValidationMessages();
        request.setAttribute(ERROR_MESSAGE, errorMessages);
        request.setAttribute(ERROR_VALIDATION_MESSAGE, errorValidationMessages);
    }
}
