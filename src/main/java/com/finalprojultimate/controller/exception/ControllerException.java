package com.finalprojultimate.controller.exception;

import com.finalprojultimate.exception.ApplicationException;

public class ControllerException extends ApplicationException {

    @Override
    public ControllerException addMessage(String message) {
        super.addMessage(message);
        return this;
    }

    @Override
    public ControllerException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }
}
