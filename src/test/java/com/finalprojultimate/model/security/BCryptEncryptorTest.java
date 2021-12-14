package com.finalprojultimate.model.security;

import org.junit.Test;

import static org.junit.Assert.*;

public class BCryptEncryptorTest {

    @Test
    public void encryptAndCheckPasswordTest() {
        BCryptEncryptor bCryptEncryptor = new BCryptEncryptor();
        String test = bCryptEncryptor.encryptPassword("1234qwe4321");
        boolean result = bCryptEncryptor.checkPassword("1234qwe4321", test);
        assertTrue(result);
    }

}