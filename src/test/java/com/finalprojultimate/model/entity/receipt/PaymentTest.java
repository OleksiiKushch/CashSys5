package com.finalprojultimate.model.entity.receipt;

import com.finalprojultimate.model.entity.user.Role;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void getByName() {
        assertEquals(Payment.ELECTRONIC, Payment.getByName("electronic"));
    }

    @Test
    public void toStringTest() {
        assertEquals("Payment{id=1, name='cash'}", Payment.CASH.toString());
    }

    @Test
    public void getName() {
        assertEquals("cash", Payment.CASH.getName());
    }

    @Test
    public void getMessage() {
        assertEquals("electronic", Payment.ELECTRONIC.getMessage());
    }
}