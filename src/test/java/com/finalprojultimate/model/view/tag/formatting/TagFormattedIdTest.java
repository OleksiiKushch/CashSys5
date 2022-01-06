package com.finalprojultimate.model.view.tag.formatting;

import org.junit.Test;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TagFormattedIdTest {

    @Test
    public void doStartTag() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doNothing().when(jspWriter).println(any(Object.class));
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedId tag1 = new TagFormattedId();
        tag1.setPageContext(pageContext);
        tag1.setIdValue(3);
        tag1.doStartTag();

        verify(jspWriter, times(1)).println(3L);

        TagFormattedId tag2 = new TagFormattedId();
        tag2.setPageContext(pageContext);
        tag2.setIdValue(0);
        tag2.doStartTag();

        verify(jspWriter, times(1)).println("-");

        verify(pageContext, times(2)).getOut();

    }

    @Test
    public void doStartTag_with_exception() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doThrow(IOException.class).when(jspWriter).println(anyLong()); // or anyString() if idValue == 0
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedId tag = new TagFormattedId();
        tag.setPageContext(pageContext);
        tag.setIdValue(3);
        tag.doStartTag();

        verify(jspWriter, times(1)).println(3L);
        verify(pageContext, times(1)).getOut();

    }
}