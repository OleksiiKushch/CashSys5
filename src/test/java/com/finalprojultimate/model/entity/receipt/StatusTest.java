package com.finalprojultimate.model.entity.receipt;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatusTest {

    @Test
    public void getByName() {
        assertEquals(Status.NORMAL, Status.getByName("normal"));
    }

    @Test
    public void toStringTest() {
        assertEquals("Status{id=1, name='normal'}", Status.NORMAL.toString());
    }

    @Test
    public void getMessage() {
        assertEquals("rejected", Status.REJECTED.getMessage());
    }
}