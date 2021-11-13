package com.finalprojultimate.model.services.exception;

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

    public static class Builder {
        private final ServiceException newServiceException;

        public Builder() {
            newServiceException = new ServiceException();
        }

        public Builder withMessage() {
            newServiceException.message = new StringBuffer();
            return this;
        }

        public Builder withMessage(StringBuffer message) {
            newServiceException.message = message;
            return this;
        }

        public Builder addMessage(String message) {
            newServiceException.message.append(message);
            return this;
        }

        public Builder withLogMessage() {
            newServiceException.logMessage = new StringBuffer();
            return this;
        }

        public Builder withLogMessage(StringBuffer logMessage) {
            newServiceException.logMessage = logMessage;
            return this;
        }

        public Builder addLogMessage(String logMessage) {
            newServiceException.logMessage.append(logMessage);
            return this;
        }

        public Builder withClassThrowsException(Class<?> classThrowsException) {
            newServiceException.classThrowsException = classThrowsException;
            return this;
        }

        public ApplicationException build() {
            return newServiceException;
        }
    }
}
