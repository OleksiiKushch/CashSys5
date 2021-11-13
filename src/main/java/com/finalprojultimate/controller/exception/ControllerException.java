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

    public static class Builder {
        private final ControllerException newControllerException;

        public Builder() {
            newControllerException = new ControllerException();
        }

        public Builder withMessage() {
            newControllerException.message = new StringBuffer();
            return this;
        }

        public Builder withMessage(StringBuffer message) {
            newControllerException.message = message;
            return this;
        }

        public Builder addMessage(String message) {
            newControllerException.message.append(message);
            return this;
        }

        public Builder withLogMessage() {
            newControllerException.logMessage = new StringBuffer();
            return this;
        }

        public Builder withLogMessage(StringBuffer logMessage) {
            newControllerException.logMessage = logMessage;
            return this;
        }

        public Builder addLogMessage(String logMessage) {
            newControllerException.logMessage.append(logMessage);
            return this;
        }

        public Builder withClassThrowsException(Class<?> classThrowsException) {
            newControllerException.classThrowsException = classThrowsException;
            return this;
        }

        public ApplicationException build() {
            return newControllerException;
        }
    }
}
