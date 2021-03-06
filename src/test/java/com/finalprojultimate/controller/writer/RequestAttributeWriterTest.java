package com.finalprojultimate.controller.writer;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RequestAttributeWriterTest {

    @Test
    public void writeToRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);


        RequestAttributeWriter attributeWriter = new RequestAttributeWriter(request);
        attributeWriter.writeToRequest("attributeKey", "attributeData");
    }

    @Test
    public void writeToSession() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        RequestAttributeWriter attributeWriter = new RequestAttributeWriter(request);
        attributeWriter.writeToSession("attributeKey", "attributeData");

        verify(request, times(1)).getSession();
    }
}