package com.finalprojultimate.model.validation.impl;

import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ReceiptPropertiesValidatorTest {

    @Test
    public void isValid() {
        Validator<ReceiptDetails> receiptPropertiesValidator = new ReceiptPropertiesValidator();

        ReceiptDetails receiptDetails = new ReceiptDetails.Builder()
                .withOrganizationTaxIdNumber(7802870820L)
                .withNameOrganization("ТОВ \"Епіцентр К\"")
                .withAddressTradePoint("м.Харків, вул.Героїв Праці, 9А")
                .withVat(new BigDecimal("20.00"))
                .withTaxationSys("ОСН")
                .build();

        boolean result = receiptPropertiesValidator.isValid(receiptDetails);

        assertTrue(result);

        receiptDetails.setVat(new BigDecimal("-5"));

        result = receiptPropertiesValidator.isValid(receiptDetails);

        assertFalse(result);
    }

    @Test
    public void getErrorMessages() {
        Validator<ReceiptDetails> receiptPropertiesValidator = new ReceiptPropertiesValidator();

        ReceiptDetails receiptDetails = new ReceiptDetails.Builder()
                .withOrganizationTaxIdNumber(7802870820L)
                .withNameOrganization("ТОВ \"Епіцентр К\"")
                .withAddressTradePoint("м.Харків, вул.Героїв Праці, 9А")
                .withVat(new BigDecimal("20.00"))
                .withTaxationSys("")
                .build();

        boolean result = receiptPropertiesValidator.isValid(receiptDetails);
        assertFalse(result);
        assertEquals("[error.empty.taxation.sys.validation]",
                receiptPropertiesValidator.getErrorMessages().toString());

        receiptDetails.setTaxationSys("ОСН");
        result = receiptPropertiesValidator.isValid(receiptDetails);
        assertTrue(result);

        receiptDetails.setVat(new BigDecimal("20.2020"));
        result = receiptPropertiesValidator.isValid(receiptDetails);
        assertFalse(result);

        receiptDetails.setVat(new BigDecimal("20.2000"));
        result = receiptPropertiesValidator.isValid(receiptDetails);
        assertTrue(result);
    }

    @Test
    public void getErrorValidationMessages() {
        Validator<ReceiptDetails> receiptPropertiesValidator = new ReceiptPropertiesValidator();

        ReceiptDetails receiptDetails = new ReceiptDetails.Builder()
                .withOrganizationTaxIdNumber(7802870820L)
                .withNameOrganization("ТОВ \"Епіцентр К\"")
                .withAddressTradePoint("м.Харків, вул.Героїв Праці, 9А")
                .withVat(new BigDecimal("20.0059"))
                .withTaxationSys("ОСН")
                .build();

        boolean result = receiptPropertiesValidator.isValid(receiptDetails);
        assertFalse(result);
        assertEquals("[error.wrong.vat.validation]",
                receiptPropertiesValidator.getErrorValidationMessages().toString());

        receiptDetails.setVat(new BigDecimal("-15.3"));
        result = receiptPropertiesValidator.isValid(receiptDetails);
        assertFalse(result);
        assertEquals("[error.wrong.vat.validation, error.singed.vat.validation]",
                receiptPropertiesValidator.getErrorValidationMessages().toString());

        receiptDetails.setOrganizationTaxIdNumber(-7802870820L);
        result = receiptPropertiesValidator.isValid(receiptDetails);
        assertFalse(result);
        assertEquals(
                "[error.wrong.vat.validation, error.singed.vat.validation, error.wrong.organization.tax.id.number.validation, " +
                        "error.wrong.singed.or.zero.organization.tax.id.number.validation, error.singed.vat.validation]",
                receiptPropertiesValidator.getErrorValidationMessages().toString());
    }
}