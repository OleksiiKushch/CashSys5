package com.finalprojultimate.controller.validation;

import com.finalprojultimate.model.services.util.LoginData;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.finalprojultimate.controller.CommandRegexAndPatterns.PASSWORD_PATTERN;
import static com.finalprojultimate.util.MessageKey.*;

public class LoginValidator implements Validator<LoginData> {
    private static final Logger logger = Logger.getLogger(LoginValidator.class);

    private boolean isEmailNotEmpty;
    private boolean isEmailValid;
    private boolean isPasswordNotEmpty;
    private boolean isPasswordValid;

    private static final EmailValidator validator = EmailValidator.getInstance();

    private final StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;
    private final NullChecker<String> nullChecker = (str) -> str == null || str.isEmpty();

    @Override
    public boolean isValid(LoginData loginData) {
        isEmailNotEmpty = !nullChecker.isEmpty(loginData.getEmail());
        isEmailValid = validator.isValid(loginData.getEmail());
        isPasswordNotEmpty = !nullChecker.isEmpty(loginData.getPassword());
        isPasswordValid = PASSWORD_PATTERN.matcher(loginData.getPassword()).matches();

        return isEmailNotEmpty && isEmailValid && isPasswordNotEmpty && isPasswordValid;
    }

    @Override
    public List<String> getErrorMessages() {
        return getErrorMessages(isEmailNotEmpty, ERROR_EMPTY_EMAIL, isPasswordNotEmpty, ERROR_EMPTY_PASSWORD);
    }

    @Override
    public List<String> getErrorValidationMessages() {
        return getErrorMessages(isEmailValid, ERROR_WRONG_EMAIL, isPasswordValid, ERROR_WRONG_PASSWORD);
    }

    private List<String> getErrorMessages(boolean isEmailValid, String errorWrongEmail,
                                    boolean isPasswordValid, String errorWrongPassword) {

        List<String> result = new ArrayList<>();
        result.add(extractor.extractIfPositive(!isEmailValid, errorWrongEmail));
        result.add(extractor.extractIfPositive(!isPasswordValid, errorWrongPassword));

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
