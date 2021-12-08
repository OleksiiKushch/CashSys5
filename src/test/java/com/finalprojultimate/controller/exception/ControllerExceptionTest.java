package com.finalprojultimate.controller.exception;

import com.finalprojultimate.model.services.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerExceptionTest {

    @Test(expected = ControllerException.class)
    public void addMessage() {
        ControllerException controllerException = new ControllerException();
        controllerException.addMessage("An error has occurred!");
        assertEquals("An error has occurred!", controllerException.getMessage());
        controllerException.addMessage("1").addMessage("2");
        assertEquals("An error has occurred!12", controllerException.getMessage());
        throw controllerException;
    }

    @Test(expected = ControllerException.class)
    public void addLogMessage() {
        ControllerException controllerException = new ControllerException();
        controllerException.addLogMessage("an.error.has.occurred");
        assertEquals("an.error.has.occurred", controllerException.getLogMessage());
        controllerException.addLogMessage("1").addLogMessage("2");
        assertEquals("an.error.has.occurred12", controllerException.getLogMessage());
        throw controllerException;
    }
}