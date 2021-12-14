package com.finalprojultimate.model.entity.receipt;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReceiptDetails implements Serializable {
    private static final long serialVersionUID = -36002671907753256L;

    private int receiptId;
    private int rootReceiptId;
    private long organizationTaxIdNumber;
    private String nameOrganization;
    private String addressTradePoint;
    private BigDecimal vat;
    private String taxationSys;

    public ReceiptDetails() {
        // default constructor
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getRootReceiptId() {
        return rootReceiptId;
    }

    public void setRootReceiptId(int rootReceiptId) {
        this.rootReceiptId = rootReceiptId;
    }

    public long getOrganizationTaxIdNumber() {
        return organizationTaxIdNumber;
    }

    public void setOrganizationTaxIdNumber(long organizationTaxIdNumber) {
        this.organizationTaxIdNumber = organizationTaxIdNumber;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public String getAddressTradePoint() {
        return addressTradePoint;
    }

    public void setAddressTradePoint(String addressTradePoint) {
        this.addressTradePoint = addressTradePoint;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public String getTaxationSys() {
        return taxationSys;
    }

    public void setTaxationSys(String taxationSys) {
        this.taxationSys = taxationSys;
    }

    @Override
    public String toString() {
        return "ReceiptDetails{" +
                "receiptId=" + receiptId +
                ", rootReceiptId=" + rootReceiptId +
                ", organizationTaxIdNumber=" + organizationTaxIdNumber +
                ", nameOrganization='" + nameOrganization + '\'' +
                ", addressTradePoint='" + addressTradePoint + '\'' +
                ", vat=" + vat +
                ", taxationSys='" + taxationSys + '\'' +
                '}';
    }

    public static class Builder {
        private final ReceiptDetails newReceiptProperties;

        public Builder() {
            newReceiptProperties = new ReceiptDetails();
        }

        public Builder withReceiptId(int receiptId) {
            newReceiptProperties.receiptId = receiptId;
            return this;
        }

        public Builder withRootReceiptId(int rootReceiptId) {
            newReceiptProperties.rootReceiptId = rootReceiptId;
            return this;
        }

        public Builder withOrganizationTaxIdNumber(long organizationTaxIdNumber) {
            newReceiptProperties.organizationTaxIdNumber = organizationTaxIdNumber;
            return this;
        }

        public Builder withNameOrganization(String nameOrganization) {
            newReceiptProperties.nameOrganization = nameOrganization;
            return this;
        }

        public Builder withAddressTradePoint(String addressTradePoint) {
            newReceiptProperties.addressTradePoint = addressTradePoint;
            return this;
        }

        public Builder withVat(BigDecimal vat) {
            newReceiptProperties.vat = vat;
            return this;
        }

        public Builder withTaxationSys(String taxationSys) {
            newReceiptProperties.taxationSys = taxationSys;
            return this;
        }

        public ReceiptDetails build() {
            return newReceiptProperties;
        }
    }
}
