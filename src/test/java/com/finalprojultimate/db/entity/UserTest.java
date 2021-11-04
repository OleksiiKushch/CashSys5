package com.finalprojultimate.db.entity;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static org.junit.Assert.*;

public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void hashTest() {
        String input1 = "21253akus";
        String input2 = "21253akuS";
        String algorithm = User.hashAlgorithm;
        String test1 = User.hashPassword(input1, algorithm);
        logger.info("input1: {} -> {}", input1, test1);
        String test2 = User.hashPassword(input2, algorithm);
        logger.info("input2: {} -> {}", input2, test2);
        assertEquals(test1, User.hashPassword(input1, algorithm));
        assertNotEquals(test1, test2);
        assertEquals(test1.length(), test2.length());
        String input3 = "UnicroniX2001";
        String test3 = User.hashPassword(input3, algorithm);
        logger.info("input3: {} -> {}", input3, test3);
        assertEquals(test1.length(), test3.length());
    }

    @Test
    public void checkEmailTest() {

    }

}