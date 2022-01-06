package com.finalprojultimate.model.view.tag.processing;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TagRoleSecurityTest {

    @Test
    public void doStartTag() throws ServletException, IOException {
        PageContext pageContext = mock(PageContext.class);

        doNothing().when(pageContext).forward(anyString());

        TagRoleSecurity tag = new TagRoleSecurity();
        tag.setPageContext(pageContext);
        tag.setRole("senior cashier");
        tag.setLoggedUserRole("cashier");
        tag.doStartTag();

        verify(pageContext, times(1)).forward(anyString());

    }

    @Test
    public void doStartTag_with_exception() throws ServletException, IOException {
        PageContext pageContext = mock(PageContext.class);

        doThrow(ServletException.class).when(pageContext).forward(anyString());

        TagRoleSecurity tag = new TagRoleSecurity();
        tag.setPageContext(pageContext);
        tag.setRole("senior cashier");
        tag.setLoggedUserRole("cashier");
        tag.doStartTag();

        verify(pageContext, times(1)).forward(anyString());

    }
}