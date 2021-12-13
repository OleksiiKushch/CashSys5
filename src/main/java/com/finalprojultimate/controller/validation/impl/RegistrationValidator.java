package com.finalprojultimate.controller.validation.impl;

import com.finalprojultimate.controller.validation.NullChecker;
import com.finalprojultimate.controller.validation.StringExtractorBasedOnBool;
import com.finalprojultimate.controller.validation.Validator;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.util.LoginData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.finalprojultimate.controller.CommandRegexAndPatterns.SHORT_NAME_PATTERN;
import static com.finalprojultimate.util.MessageKey.*;

public class RegistrationValidator implements Validator<User> {
    private static final Logger logger = Logger.getLogger(RegistrationValidator.class);

    private boolean isNotEmptyFirstName;
    private boolean isValidFirstName;
    private boolean isNotEmptyMiddleName;
    private boolean isValidMiddleName;
    private boolean isNotEmptyLastName;
    private boolean isValidLastName;
    private boolean isNotEmptyRole;

    private final Validator<LoginData> loginDataValidator = new LoginValidator();
    private final StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;
    private final NullChecker<String> nameNullChecker = (name) -> name == null || name.isEmpty();

    public boolean isValid(User user) {
        isNotEmptyFirstName = !nameNullChecker.isEmpty(user.getFirstName());
        isValidFirstName = isValidName(user.getFirstName());
        isNotEmptyMiddleName = !nameNullChecker.isEmpty(user.getMiddleName());
        isValidMiddleName = isValidName(user.getMiddleName());
        isNotEmptyLastName = !nameNullChecker.isEmpty(user.getLastName());
        isValidLastName = isValidName(user.getLastName());
        isNotEmptyRole = user.getRole() != null;
        LoginData loginData = new LoginData(user.getEmail(), user.getPassHash());

        return loginDataValidator.isValid(loginData) &&
                isNotEmptyFirstName && isValidFirstName &&
                isNotEmptyMiddleName && isValidMiddleName &&
                isNotEmptyLastName && isValidLastName &&
                isNotEmptyRole;
    }

    private boolean isValidName(String name) {
        return SHORT_NAME_PATTERN.matcher(name).matches();
    }

    @Override
    public List<String> getErrorMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isNotEmptyFirstName, ERROR_EMPTY_FIRST_NAME));
        result.add(extractor.extractIfPositive(!isNotEmptyMiddleName, ERROR_EMPTY_MIDDLE_NAME));
        result.add(extractor.extractIfPositive(!isNotEmptyLastName, ERROR_EMPTY_LAST_NAME));
        result.add(extractor.extractIfPositive(!isNotEmptyRole, ERROR_EMPTY_ROLE));
        result.addAll(loginDataValidator.getErrorMessages());

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<String> getErrorValidationMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isValidFirstName, ERROR_WRONG_FIRST_NAME));
        result.add(extractor.extractIfPositive(!isValidMiddleName, ERROR_WRONG_MIDDLE_NAME));
        result.add(extractor.extractIfPositive(!isValidLastName, ERROR_WRONG_LAST_NAME));
        result.addAll(loginDataValidator.getErrorValidationMessages());

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
