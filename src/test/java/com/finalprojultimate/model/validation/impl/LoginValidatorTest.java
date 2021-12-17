package com.finalprojultimate.model.validation.impl;

import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.service.util.LoginData;
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

    @Test
    public void testLoginValidator() {
        Validator<LoginData> loginValidator = new LoginValidator();
        LoginData loginData = new LoginData("", "121233212332113123321321233211");

        boolean result = loginValidator.isValid(loginData);
        assertFalse(result);
        assertEquals("[error.empty.email.field]",
                loginValidator.getErrorMessages().toString());
        assertEquals("[error.wrong.email.validation, error.wrong.password.validation]",
                loginValidator.getErrorValidationMessages().toString());
    }
}