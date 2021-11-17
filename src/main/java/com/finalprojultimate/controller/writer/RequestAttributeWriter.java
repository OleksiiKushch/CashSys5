package com.finalprojultimate.controller.writer;

import javax.servlet.http.HttpServletRequest;

public class RequestAttributeWriter {
    private final MessageWriter<RequestAttributeData, HttpServletRequest> requestWriter;
    private final MessageWriter<RequestAttributeData, HttpServletRequest> sessionWriter;
    private final HttpServletRequest request;

    @FunctionalInterface
    public interface MessageWriter<M, W> {
        void write(M message, W whereToWrite);
    }

    public RequestAttributeWriter(HttpServletRequest request) {
        requestWriter = (attr, req) ->
                req.setAttribute(attr.getAttributeKey(), attr.getAttributeData());
        sessionWriter = (attr, req) ->
                req.getSession().setAttribute(attr.getAttributeKey(), attr.getAttributeData());
        this.request = request;
    }

    public void writeToRequest(String attributeKey, Object attributeData) {
        requestWriter.write(
                new RequestAttributeData(attributeKey, attributeData),
                request
        );
    }

    public void writeToSession(String attributeKey, Object attributeData) {
        sessionWriter.write(
                new RequestAttributeData(attributeKey, attributeData),
                request
        );
    }
}
