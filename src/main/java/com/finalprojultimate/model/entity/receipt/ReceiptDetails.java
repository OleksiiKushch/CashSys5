package com.finalprojultimate.model.entity.receipt;

import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.finalprojultimate.util.Parameter.*;

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

    public static List<String> getListStrFormatParametersGlobalReceiptProperties() {
        return new ArrayList<>(Arrays.asList(ORGANIZATION_TAX_ID_NUMBER, NAME_ORGANIZATION,
                ADDRESS_TRADE_POINT, VAT, TAXATION_SYS));
    }

    public static ReceiptDetails mapGlobalReceiptProperties(List<String> listStrFormatAttributes) {
        ReceiptDetails result = new ReceiptDetails();
        int i = -1;
        ++i;
        if (!listStrFormatAttributes.get(i).isEmpty() && !(listStrFormatAttributes.get(i).length() > 18)) { // organization tax id number
            result.setOrganizationTaxIdNumber(Long.parseLong(listStrFormatAttributes.get(i)));
        }
        result.setNameOrganization(listStrFormatAttributes.get(++i));
        result.setAddressTradePoint(listStrFormatAttributes.get(++i));
        if (!listStrFormatAttributes.get(++i).isEmpty()) { // vat
            result.setVat(new BigDecimal(listStrFormatAttributes.get(i)));
        }
        result.setTaxationSys(listStrFormatAttributes.get(++i));
        return result;
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
