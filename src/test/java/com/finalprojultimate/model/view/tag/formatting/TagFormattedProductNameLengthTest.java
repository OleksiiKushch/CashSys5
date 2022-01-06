package com.finalprojultimate.model.view.tag.formatting;

import org.junit.Test;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class TagFormattedProductNameLengthTest {

    @Test
    public void doStartTag() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doNothing().when(jspWriter).println(anyString());
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedProductNameLength tag = new TagFormattedProductNameLength();
        tag.setPageContext(pageContext);
        tag.setLength(5);
        tag.setProductName("Coca-Cola 2l");
        tag.doStartTag();

        verify(jspWriter, times(1)).println("Coca-...");
        verify(pageContext, times(1)).getOut();

    }

    @Test
    public void doStartTag_with_exception() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doThrow(IOException.class).when(jspWriter).println(anyString());
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedProductNameLength tag = new TagFormattedProductNameLength();
        tag.setPageContext(pageContext);
        tag.setLength(5);
        tag.setProductName("Coca-Cola 2l");
        tag.doStartTag();

        verify(jspWriter, times(1)).println("Coca-...");
        verify(pageContext, times(1)).getOut();

    }
}