package com.finalprojultimate.controller.writer;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestAttributeDataTest {

    @Test
    public void requestAttributeDataTest() {
        Object testObject = new Object();
        RequestAttributeData requestAttributeData = new RequestAttributeData("test", testObject);

        assertEquals("test", requestAttributeData.getAttributeKey());
        assertEquals(testObject, requestAttributeData.getAttributeData());

        Object newTestObject = new Object();
        requestAttributeData.setAttributeKey("newTest").setAttributeData(newTestObject).setAttributeKey("newTest1");
        assertEquals("newTest1", requestAttributeData.getAttributeKey());
        assertEquals(newTestObject, requestAttributeData.getAttributeData());


    }

}