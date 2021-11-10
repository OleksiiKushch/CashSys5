package com.finalprojultimate.model.db.dao.exception;

import com.finalprojultimate.exception.ApplicationException;

public class DaoException extends ApplicationException {

    @Override
    public DaoException addMessage(String message) {
        super.addMessage(message);
        return this;
    }

    @Override
    public DaoException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }

    public static class Builder {
        private final DaoException newDaoException;

        public Builder() {
            newDaoException = new DaoException();
        }

        public Builder withMessage() {
            newDaoException.message = new StringBuffer();
            return this;
        }

        public Builder withMessage(StringBuffer message) {
            newDaoException.message = message;
            return this;
        }

        public Builder addMessage(String message) {
            newDaoException.message.append(message);
            return this;
        }

        public Builder withLogMessage() {
            newDaoException.logMessage = new StringBuffer();
            return this;
        }

        public Builder withLogMessage(StringBuffer logMessage) {
            newDaoException.logMessage = logMessage;
            return this;
        }

        public Builder addLogMessage(String logMessage) {
            newDaoException.logMessage.append(logMessage);
            return this;
        }

        public Builder withClassThrowsException(Class<?> classThrowsException) {
            newDaoException.classThrowsException = classThrowsException;
            return this;
        }

        public DaoException build() {
            return newDaoException;
        }
    }
}
