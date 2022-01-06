package com.finalprojultimate.model.view.tag.processing;

import org.junit.Test;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class TagTotalSumTest {

    @Test
    public void doStartTag() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doNothing().when(jspWriter).println(any(BigDecimal.class));
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagTotalSum tag = new TagTotalSum();
        tag.setPageContext(pageContext);
        tag.setAmount(new BigDecimal("5.5"));
        tag.setPrice(new BigDecimal("0.9"));
        tag.doStartTag();

        verify(jspWriter, times(1)).println(new BigDecimal("4.95"));
        verify(pageContext, times(1)).getOut();

    }

    @Test
    public void doStartTag_with_exception() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doThrow(IOException.class).when(jspWriter).println(any(BigDecimal.class));
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagTotalSum tag = new TagTotalSum();
        tag.setPageContext(pageContext);
        tag.setAmount(new BigDecimal("5.5"));
        tag.setPrice(new BigDecimal("0.9"));
        tag.doStartTag();

        verify(jspWriter, times(1)).println(new BigDecimal("4.95"));
        verify(pageContext, times(1)).getOut();

    }
}