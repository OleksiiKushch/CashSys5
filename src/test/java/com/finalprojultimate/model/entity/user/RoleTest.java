package com.finalprojultimate.model.entity.user;

import com.finalprojultimate.model.entity.product.Unit;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleTest {

    @Test
    public void getByName() {
        assertEquals(Role.CASHIER, Role.getByName("cashier"));
    }

    @Test
    public void getName() {
        assertEquals("senior cashier", Role.SENIOR_CASHIER.getName());
    }
}