package com.finalprojultimate.controller.servlet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegistrationServletTest {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServletTest.class);

    private static final String PATH_REGISTER = "views/registration.jsp";
    private static final String PATH_REGISTER_SUCCESS = "views/registration_successful.jsp";

    @Test
    public void registrationServletTest() throws Exception {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("email")).thenReturn("tom.hart@cashssys.com");
        when(request.getParameter("firstName")).thenReturn("Tom");
        when(request.getParameter("middleName")).thenReturn("Adyson");
        when(request.getParameter("lastName")).thenReturn("Hart");
        when(request.getParameter("password")).thenReturn("123");
        when(request.getParameter("confirmationPassword")).thenReturn("123");
        when(request.getParameter("role")).thenReturn("commodity expert");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        final RegistrationServlet servlet = new RegistrationServlet();
        servlet.init();

        servlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("username");


    }

}