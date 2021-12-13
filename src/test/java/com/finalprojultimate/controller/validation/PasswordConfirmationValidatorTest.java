package com.finalprojultimate.controller.validation;

import com.finalprojultimate.controller.validation.impl.PasswordConfirmationValidator;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PasswordConfirmationValidatorTest {

    @Test
    public void isValid() {
        Validator<Map.Entry<String, String>> validatorPassword = new PasswordConfirmationValidator();

        String password = "1234";
        String confirmationPassword  = "1234";

        Map.Entry<String, String> pairPassword = new AbstractMap.SimpleEntry<>(password, confirmationPassword);

        boolean result = validatorPassword.isValid(pairPassword);

        assertTrue(result);

        pairPassword.setValue("1235");

        result = validatorPassword.isValid(pairPassword);

        assertFalse(result);
    }

    @Test
    public void getErrorValidationMessages() {
        Validator<Map.Entry<String, String>> validatorPassword = new PasswordConfirmationValidator();

        String password = "1234";
        String confirmationPassword  = "1235";

        Map.Entry<String, String> pairPassword = new AbstractMap.SimpleEntry<>(password, confirmationPassword);

        boolean result = validatorPassword.isValid(pairPassword);

        assertFalse(result);

        assertNull(validatorPassword.getErrorValidationMessages());
    }

    @Test
    public void getErrorMessages() {
        Validator<Map.Entry<String, String>> validatorPassword = new PasswordConfirmationValidator();

        String password = "1234";
        String confirmationPassword  = "1235";

        Map.Entry<String, String> pairPassword = new AbstractMap.SimpleEntry<>(password, confirmationPassword);

        boolean result = validatorPassword.isValid(pairPassword);

        assertFalse(result);

        assertEquals("[error.is.not.same.confirmation.password]",
                validatorPassword.getErrorMessages().toString());
    }
}
