package com.finalprojultimate.model.view.tag.formatting;

import com.finalprojultimate.model.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceImpl.class)
public class TagFormattedUserNameTest {

    @Test
    public void doStartTag() throws IOException {
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);

        PowerMockito.mockStatic(UserServiceImpl.class);
        PowerMockito.when(UserServiceImpl.getInstance()).thenReturn(userServiceImpl);

        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        when(userServiceImpl.getFormattedNameById(1)).thenReturn("Filip M.P.");
        doNothing().when(jspWriter).println(anyString());
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedUserName tag = new TagFormattedUserName();
        tag.setPageContext(pageContext);
        tag.setUserId(1);
        tag.doStartTag();

        verify(userServiceImpl, times(1)).getFormattedNameById(1);
        verify(jspWriter, times(1)).println("Filip M.P.");
        verify(pageContext, times(1)).getOut();

    }

    @Test
    public void doStartTag_with_exception() throws IOException {
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);

        PowerMockito.mockStatic(UserServiceImpl.class);
        PowerMockito.when(UserServiceImpl.getInstance()).thenReturn(userServiceImpl);

        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        when(userServiceImpl.getFormattedNameById(1)).thenReturn("Filip M.P.");
        doThrow(IOException.class).when(jspWriter).println(anyString());
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedUserName tag = new TagFormattedUserName();
        tag.setPageContext(pageContext);
        tag.setUserId(1);
        tag.doStartTag();

        verify(userServiceImpl, times(1)).getFormattedNameById(1);
        verify(jspWriter, times(1)).println("Filip M.P.");
        verify(pageContext, times(1)).getOut();

    }
}