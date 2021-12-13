package com.finalprojultimate.model.entity.product;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTest {

    @Test
    public void getByName() {
        assertEquals(Unit.PIECES, Unit.getByName("pieces"));
    }

    @Test
    public void getName() {
        assertEquals("kilogram", Unit.KILOGRAM.getName());
    }

    @Test
    public void getMessage() {
        assertEquals("litre", Unit.LITRE.getMessage());
    }
}