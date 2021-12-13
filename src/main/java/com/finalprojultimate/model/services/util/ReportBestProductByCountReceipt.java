package com.finalprojultimate.model.services.util;

import java.math.BigDecimal;

public class ReportBestProductByCountReceipt {
    private int productId;
    private BigDecimal totalAmount;
    private BigDecimal totalSum;
    // count receipts contains this product
    private int countReceipts;

    public ReportBestProductByCountReceipt(int productId, BigDecimal totalAmount, BigDecimal totalSum, int countReceipts) {
        this.productId = productId;
        this.totalAmount = totalAmount;
        this.totalSum = totalSum;
        this.countReceipts = countReceipts;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public int getCountReceipts() {
        return countReceipts;
    }

    public void setCountReceipts(int countReceipts) {
        this.countReceipts = countReceipts;
    }

    @Override
    public String toString() {
        return "ReportBestProductByCountReceipt{" +
                "productId=" + productId +
                ", totalAmount=" + totalAmount +
                ", totalSum=" + totalSum +
                ", countReceipts=" + countReceipts +
                '}';
    }
}
