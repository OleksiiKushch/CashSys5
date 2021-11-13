package com.finalprojultimate.controller.command;

import com.finalprojultimate.exception.ApplicationException;
import com.finalprojultimate.util.Attribute;
import com.finalprojultimate.util.MessageKey;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractCommandWrapper<E> implements Command {
    private static final Logger logger = Logger.getLogger(AbstractCommandWrapper.class);

    private final String pageToGoWithErrors;

    protected AbstractCommandWrapper(String pageToGoWithErrors) {
        this.pageToGoWithErrors = pageToGoWithErrors;
    }

    protected abstract String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract E getDataFromRequest(HttpServletRequest request);
    protected abstract void writeSpecificDataToRequest(HttpServletRequest request, E data);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        try {
            return performExecute(request, response);
        } catch (ApplicationException e) {
            processApplicationError(request, e);
        } catch (Exception e) {
            processUnknownException(request, e);
        }

        return pageToGoWithErrors;
    }

    private void processUnknownException(HttpServletRequest request, Exception e) {
        request.setAttribute(Attribute.ERROR_MESSAGE, MessageKey.ERROR_UNKNOWN_EXCEPTION);
        logger.error(e.getMessage(), e);
    }

    private void processApplicationError(HttpServletRequest request, ApplicationException e) {
        request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
        Logger.getLogger(e.getClassThrowsException())
                .error(e.getLogMessage());
    }


}
