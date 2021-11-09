package com.finalprojultimate.model.db.dao.exception;

import com.finalprojultimate.exception.ApplicationException;

public class DaoException extends ApplicationException {

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
