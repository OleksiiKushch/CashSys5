package com.finalprojultimate.controller.filter;

import org.junit.Test;

import javax.servlet.*;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CharsetFilterTest {

    @Test
    public void init() throws NoSuchFieldException, IllegalAccessException {
        FilterConfig config = mock(FilterConfig.class);

        when(config.getInitParameter("requestEncoding")).thenReturn(null);

        CharsetFilter filter = new CharsetFilter();
        filter.init(config);

        Field f1 = filter.getClass().getDeclaredField("encoding");
        f1.setAccessible(true);
        assertEquals("UTF-8", f1.get(filter));

        verify(config, times(1)).getInitParameter("requestEncoding");

    }

    @Test
    public void doFilter() throws ServletException, IOException, NoSuchFieldException, IllegalAccessException {
        FilterConfig config = mock(FilterConfig.class);

        ServletRequest request = mock(ServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        FilterChain nextFilter = mock(FilterChain.class);

        CharsetFilter filter = new CharsetFilter();
        filter.init(config);
        filter.doFilter(request, response, nextFilter);

        Field f1 = filter.getClass().getDeclaredField("encoding");
        f1.setAccessible(true);
        assertEquals("UTF-8", f1.get(filter));

        verify(config, times(1)).getInitParameter("requestEncoding");
        verify(request, times(1)).getCharacterEncoding();
        verify(request, times(1)).setCharacterEncoding("UTF-8");
        verify(response, times(1)).setContentType("text/html; charset=UTF-8");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
        verify(nextFilter, times(1)).doFilter(request, response);

    }
}