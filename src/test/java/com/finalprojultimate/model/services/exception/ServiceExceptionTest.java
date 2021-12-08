package com.finalprojultimate.model.services.exception;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceExceptionTest {

    @Test(expected = ServiceException.class)
    public void addMessage() {
        ServiceException serviceException = new ServiceException();
        serviceException.addMessage("An error has occurred!");
        assertEquals("An error has occurred!", serviceException.getMessage());
        serviceException.addMessage("1").addMessage("2");
        assertEquals("An error has occurred!12", serviceException.getMessage());
        throw serviceException;
    }

    @Test(expected = ServiceException.class)
    public void addLogMessage() {
        ServiceException serviceException = new ServiceException();
        serviceException.addLogMessage("an.error.has.occurred");
        assertEquals("an.error.has.occurred", serviceException.getLogMessage());
        serviceException.addLogMessage("1").addLogMessage("2");
        assertEquals("an.error.has.occurred12", serviceException.getLogMessage());
        throw serviceException;
    }
}