package com.finalprojultimate.exception;

public class ApplicationException extends RuntimeException {
    protected StringBuffer message;
    protected StringBuffer logMessage;
    protected Class<?> classThrowsException;

    public ApplicationException() {
        this.message = new StringBuffer();
        this.logMessage = new StringBuffer();
    }

    public ApplicationException(String message) {
        super(message);
        this.message = new StringBuffer(message);
        this.logMessage = new StringBuffer();
    }

    public ApplicationException(Throwable cause) {
        super(cause);
        this.message = new StringBuffer();
        this.logMessage = new StringBuffer();
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.message = new StringBuffer(message);
        this.logMessage = new StringBuffer();
    }

    public void setMessage(StringBuffer message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.toString();
    }

    public void setLogMessage(StringBuffer logMessage) {
        this.logMessage = logMessage;
    }

    public String getLogMessage() {
        return logMessage.toString();
    }

    public ApplicationException setClassThrowsException(Class<?> classThrowsException) {
        this.classThrowsException = classThrowsException;
        return this;
    }

    public Class<?> getClassThrowsException() {
        return classThrowsException;
    }

    public ApplicationException addMessage(String message) {
        this.message.append(message);
        return this;
    }

    public ApplicationException addLogMessage(String logMessage) {
        this.logMessage.append(logMessage);
        return this;
    }

}
