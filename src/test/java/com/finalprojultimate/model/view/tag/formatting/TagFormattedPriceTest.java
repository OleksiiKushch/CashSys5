package com.finalprojultimate.model.view.tag.formatting;

import org.junit.Test;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class TagFormattedPriceTest {

    @Test
    public void doStartTag() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doNothing().when(jspWriter).println(any(BigDecimal.class));
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedPrice tag = new TagFormattedPrice();
        tag.setPageContext(pageContext);
        tag.setPrice(new BigDecimal("13.7500"));
        tag.doStartTag();

        verify(jspWriter, times(1)).println(new BigDecimal("13.75"));
        verify(pageContext, times(1)).getOut();

    }

    @Test
    public void doStartTag_with_exception() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doThrow(IOException.class).when(jspWriter).println(any(BigDecimal.class));
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedPrice tag = new TagFormattedPrice();
        tag.setPageContext(pageContext);
        tag.setPrice(new BigDecimal("13.7500"));
        tag.doStartTag();

        verify(jspWriter, times(1)).println(new BigDecimal("13.75"));
        verify(pageContext, times(1)).getOut();

    }
}