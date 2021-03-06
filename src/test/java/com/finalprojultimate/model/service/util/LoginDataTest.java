package com.finalprojultimate.model.service.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginDataTest {

    @Test
    public void loginDataTest() {
        LoginData loginData = new LoginData("bob.dillan@gmail.com", "123456789");
        assertEquals("LoginData{email='bob.dillan@gmail.com', password='123456789'}", loginData.toString());
        assertEquals("bob.dillan@gmail.com", loginData.getEmail());
        assertEquals("123456789", loginData.getPassword());
        loginData.setEmail("bob2.dillan@gmail.com");
        assertEquals("bob2.dillan@gmail.com", loginData.getEmail());
        loginData.setPassword("987654321");
        assertEquals("987654321", loginData.getPassword());
    }

}