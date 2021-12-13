package com.finalprojultimate.controller.validation;

import com.finalprojultimate.controller.validation.impl.LoginValidator;
import com.finalprojultimate.model.services.util.LoginData;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginValidatorTest {

    @Test
    public void isValid() {
        Validator<LoginData> loginValidator = new LoginValidator();

        LoginData loginData = new LoginData("bob.fel@gmail.com", "123321");

        boolean result = loginValidator.isValid(loginData);

        assertTrue(result);

        loginData.setEmail("bob.felgmail.com");

        result = loginValidator.isValid(loginData);

        assertFalse(result);

        loginData.setEmail("");

        result = loginValidator.isValid(loginData);

        assertFalse(result);

        loginData.setEmail("bob1.fel@gmail.com");

        result = loginValidator.isValid(loginData);

        assertTrue(result);
    }

    @Test
    public void getErrorMessages() {
        Validator<LoginData> loginValidator = new LoginValidator();

        LoginData loginData = new LoginData("bob.fel@gmail.com", "");

        boolean result = loginValidator.isValid(loginData);

        assertFalse(result);

        assertEquals("[error.empty.password.field]",
                loginValidator.getErrorMessages().toString());

        loginData.setEmail("");

        result = loginValidator.isValid(loginData);

        assertFalse(result);

        assertEquals("[error.empty.email.field, error.empty.password.field]",
                loginValidator.getErrorMessages().toString());
    }

    @Test
    public void getErrorValidationMessages() {
        Validator<LoginData> loginValidator = new LoginValidator();

        LoginData loginData = new LoginData("bob.felgmail.com", "123321");

        boolean result = loginValidator.isValid(loginData);

        assertFalse(result);

        assertEquals("[error.wrong.email.validation]",
                loginValidator.getErrorValidationMessages().toString());

        loginData.setEmail("");

        result = loginValidator.isValid(loginData);

        assertFalse(result);

        assertEquals("[error.wrong.email.validation]",
                loginValidator.getErrorValidationMessages().toString());
    }
}