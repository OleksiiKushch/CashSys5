package com.finalprojultimate.controller.validation;

import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import com.finalprojultimate.model.services.util.LoginData;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class LoginValidatorTest {

    @Test
    public void isValid() {
        LoginValidator loginValidator = new LoginValidator();

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
        LoginValidator loginValidator = new LoginValidator();

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
        LoginValidator loginValidator = new LoginValidator();

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