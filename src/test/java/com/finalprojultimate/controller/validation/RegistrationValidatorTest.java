package com.finalprojultimate.controller.validation;

import com.finalprojultimate.controller.validation.impl.RegistrationValidator;
import com.finalprojultimate.model.entity.user.Role;
import com.finalprojultimate.model.entity.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationValidatorTest {

    @Test
    public void isValid() {
        Validator<User> registrationValidator = new RegistrationValidator();

        User user = new User.Builder()
                .withEmail("alex.thor@gmail.com")
                .withFirstName("alex")
                .withMiddleName("le")
                .withLastName("thor")
                .withPassHash("1234321") // password
                .withRole(Role.CASHIER)
                .build();

        boolean result = registrationValidator.isValid(user);

        assertTrue(result);

        user.setEmail("alex.thorgmail.com");

        result = registrationValidator.isValid(user);

        assertFalse(result);

    }

    @Test
    public void getErrorMessages() {
        Validator<User> registrationValidator = new RegistrationValidator();

        User user = new User.Builder()
                .withEmail("alex.thor@gmail.com")
                .withFirstName("")
                .withMiddleName("le")
                .withLastName("thor")
                .withPassHash("1234321") // password
                .withRole(Role.CASHIER)
                .build();

        boolean result = registrationValidator.isValid(user);

        assertFalse(result);

        assertEquals("[error.empty.first.name.field]",
                registrationValidator.getErrorMessages().toString());
    }

    @Test
    public void getErrorValidationMessages() {
        Validator<User> registrationValidator = new RegistrationValidator();

        User user = new User.Builder()
                .withEmail("alex.thor@gmail.com")
                .withFirstName("alex")
                .withMiddleName("le")
                .withLastName("thor")
                .withPassHash("1234321423444442352341325235235235") // password
                .withRole(Role.CASHIER)
                .build();

        boolean result = registrationValidator.isValid(user);

        assertFalse(result);

        assertEquals("[error.wrong.password.validation]",
                registrationValidator.getErrorValidationMessages().toString());
    }
}