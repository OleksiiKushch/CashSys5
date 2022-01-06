package com.finalprojultimate.model.view.tag.supplying;

import com.finalprojultimate.model.service.impl.ReceiptServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ReceiptServiceImpl.class)
public class TagTotalReceiptSumTest {

    @Test
    public void doStartTag() throws Exception {
        ReceiptServiceImpl receiptServiceImpl = mock(ReceiptServiceImpl.class);

        PowerMockito.mockStatic(ReceiptServiceImpl.class);
        PowerMockito.when(ReceiptServiceImpl.getInstance()).thenReturn(receiptServiceImpl);

        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        when(receiptServiceImpl.getSumReceiptById(1)).thenReturn(new BigDecimal("20.25"));
        doNothing().when(jspWriter).println(any(BigDecimal.class));
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagTotalReceiptSum tag = new TagTotalReceiptSum();
        tag.setPageContext(pageContext);
        tag.setReceiptId(1);
        tag.doStartTag();

        verify(receiptServiceImpl, times(1)).getSumReceiptById(1);
        verify(jspWriter, times(1)).println(any(BigDecimal.class));
        verify(pageContext, times(1)).getOut();

    }

    @Test
    public void doStartTag_with_exception() throws Exception {
        ReceiptServiceImpl receiptServiceImpl = mock(ReceiptServiceImpl.class);

        PowerMockito.mockStatic(ReceiptServiceImpl.class);
        PowerMockito.when(ReceiptServiceImpl.getInstance()).thenReturn(receiptServiceImpl);

        JspWriter jspWriter = mock(JspWriter.class);
        PageContext pageContext = mock(PageContext.class);

        when(receiptServiceImpl.getSumReceiptById(1)).thenReturn(new BigDecimal("20.25"));
        doThrow(IOException.class).when(jspWriter).println(any(BigDecimal.class));
        when(pageContext.getOut()).thenReturn(jspWriter);

        TagTotalReceiptSum tag = new TagTotalReceiptSum();
        tag.setPageContext(pageContext);
        tag.setReceiptId(1);
        tag.doStartTag();

        verify(receiptServiceImpl, times(1)).getSumReceiptById(1);
        verify(jspWriter, times(1)).println(any(BigDecimal.class));
        verify(pageContext, times(1)).getOut();

    }
}