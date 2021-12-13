package com.finalprojultimate.model.services.util;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ReportBestProductByCountReceiptTest {

    @Test
    public void testReportBestProductByCountReceipt() {
        ReportBestProductByCountReceipt reportProduct = new ReportBestProductByCountReceipt(3,
                new BigDecimal("23"), new BigDecimal("36.2"), 6);

        assertEquals("ReportBestProductByCountReceipt{productId=3, totalAmount=23, totalSum=36.2, countReceipts=6}",
                reportProduct.toString());

        reportProduct.setProductId(2);
        assertEquals(2, reportProduct.getProductId());

        reportProduct.setTotalAmount(new BigDecimal("37"));
        assertEquals("37", reportProduct.getTotalAmount().toString());

        reportProduct.setTotalSum(new BigDecimal("29.7"));
        assertEquals("29.7", reportProduct.getTotalSum().toString());

        reportProduct.setCountReceipts(7);
        assertEquals(7, reportProduct.getCountReceipts());

    }
}