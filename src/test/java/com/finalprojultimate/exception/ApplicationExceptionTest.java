package com.finalprojultimate.exception;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationExceptionTest {

    @Test(expected = ApplicationException.class)
    public void applicationExceptionTest() {
        throw new ApplicationException();
    }

    @Test(expected = ApplicationException.class)
    public void applicationExceptionWithMessageTest() {
        throw new ApplicationException("An error has occurred!");
    }

    @Test(expected = ApplicationException.class)
    public void applicationExceptionWithCauseTest() {
        throw new ApplicationException(new RuntimeException());
    }

    @Test(expected = ApplicationException.class)
    public void applicationExceptionWithMessageAndCauseTest() {
        throw new ApplicationException("An error has occurred!", new RuntimeException());
    }

    @Test
    public void setAndGetMessageTest() {
        ApplicationException applicationException = new ApplicationException();
        applicationException.setMessage(new StringBuffer("An error has occurred!"));
        assertEquals("An error has occurred!", applicationException.getMessage());

        applicationException.addMessage(" Error number 2!");

        assertEquals("An error has occurred! Error number 2!", applicationException.getMessage());

        applicationException.setLogMessage(new StringBuffer("an.error.has.occurred"));
        assertEquals("an.error.has.occurred", applicationException.getLogMessage());

        applicationException.addLogMessage(" error.number.2");
        assertEquals("an.error.has.occurred error.number.2", applicationException.getLogMessage());
    }

    @Test
    public void setAndGetClassThrowsExceptionTest() {
        ApplicationException applicationException = new ApplicationException();
        applicationException.setClassThrowsException(RuntimeException.class);
        assertEquals(RuntimeException.class, applicationException.getClassThrowsException());
    }
}