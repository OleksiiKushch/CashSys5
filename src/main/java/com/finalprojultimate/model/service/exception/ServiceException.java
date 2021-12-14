package com.finalprojultimate.model.service.exception;

import com.finalprojultimate.exception.ApplicationException;

public class ServiceException extends ApplicationException {

    @Override
    public ServiceException addMessage(String message) {
        super.addMessage(message);
        return this;
    }

    @Override
    public ServiceException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }
}
