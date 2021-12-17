package com.finalprojultimate.model.validation.impl;

import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.receipt.Receipt;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ReceiptValidatorTest {

    @Test
    public void isValid() {
        Validator<Receipt> receiptValidator = new ReceiptValidator();

        Receipt receipt = new Receipt.Builder()
                .withChange(new BigDecimal("50"))
                .build();

        boolean result = receiptValidator.isValid(receipt);
        assertTrue(result);

        receipt.setChange(new BigDecimal("-32"));
        result = receiptValidator.isValid(receipt);
        assertFalse(result);
    }

    @Test
    public void getErrorValidationMessages() {
        Validator<Receipt> receiptValidator = new ReceiptValidator();

        Receipt receipt = new Receipt.Builder()
                .withChange(new BigDecimal("-50"))
                .build();

        boolean result = receiptValidator.isValid(receipt);
        assertFalse(result);
        assertEquals("[error.singed.receipt.change.validation]",
                receiptValidator.getErrorValidationMessages().toString());
    }

    @Test
    public void getErrorMessages() {
        Validator<Receipt> receiptValidator = new ReceiptValidator();

        Receipt receipt = new Receipt.Builder()
                .withChange(new BigDecimal("-25"))
                .build();

        boolean result = receiptValidator.isValid(receipt);
        assertFalse(result);
        assertNull(receiptValidator.getErrorMessages());
    }
}