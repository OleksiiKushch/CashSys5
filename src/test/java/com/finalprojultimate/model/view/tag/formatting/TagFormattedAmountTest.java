package com.finalprojultimate.model.view.tag.formatting;

import com.finalprojultimate.model.entity.product.Unit;
import org.junit.Test;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class TagFormattedAmountTest {

    @Test
    public void doStartTag() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doNothing().when(jspWriter).println(any(BigDecimal.class));
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedAmount tag1 = new TagFormattedAmount();
        tag1.setPageContext(pageContext);
        tag1.setAmount(new BigDecimal("50.000"));
        tag1.setUnit(Unit.PIECES);
        tag1.doStartTag();

        verify(jspWriter, times(1)).println(new BigDecimal("50"));

        TagFormattedAmount tag2 = new TagFormattedAmount();
        tag2.setPageContext(pageContext);
        tag2.setAmount(new BigDecimal("50.0000"));
        tag2.setUnit(Unit.KILOGRAM); // or Unit.LITRE
        tag2.doStartTag();

        verify(jspWriter, times(1)).println(new BigDecimal("50.000"));

        verify(pageContext, times(2)).getOut();

    }

    @Test
    public void doStartTag_with_exception() throws IOException {
        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        doThrow(IOException.class).when(jspWriter).println(any(BigDecimal.class));
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagFormattedAmount tag = new TagFormattedAmount();
        tag.setPageContext(pageContext);
        tag.setAmount(new BigDecimal("50.000"));
        tag.setUnit(Unit.PIECES);
        tag.doStartTag();

        verify(jspWriter, times(1)).println(new BigDecimal("50"));
        verify(pageContext, times(1)).getOut();

    }
}