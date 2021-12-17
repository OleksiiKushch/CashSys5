package com.finalprojultimate.model.validation.impl;

import com.finalprojultimate.model.validation.StringExtractorBasedOnBool;
import com.finalprojultimate.model.validation.Validator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.finalprojultimate.util.MessageKey.ERROR_IS_NOT_SAME_CONFIRMATION_PASSWORD;

public class PasswordConfirmationValidator implements Validator<Entry<String, String>> {
    private static final Logger logger = Logger.getLogger(PasswordConfirmationValidator.class);

    private final StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;

    private boolean isSameConfirmationPassword;

    /**
     *
     * @param pairPassword entry map including pair where key: password and value: confirmation password
     * @return result of matching password with confirmation password
     */
    @Override
    public boolean isValid(Entry<String, String> pairPassword) {
        return isSameConfirmationPassword = pairPassword.getKey().equals(pairPassword.getValue());
    }

    @Override
    public List<String> getErrorMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isSameConfirmationPassword, ERROR_IS_NOT_SAME_CONFIRMATION_PASSWORD));

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<String> getErrorValidationMessages() {
        return null;
    }
}
