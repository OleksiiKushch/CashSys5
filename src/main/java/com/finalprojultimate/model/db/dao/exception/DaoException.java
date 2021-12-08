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
}
