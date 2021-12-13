package com.finalprojultimate.controller.validation;

import com.finalprojultimate.controller.validation.impl.ProductValidator;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ProductValidatorTest {

    @Test
    public void isValid() {
        Validator<Product> productValidator = new ProductValidator();

        Product product = new Product.Builder()
                .withName("Test1")
                .withPrice(new BigDecimal("12"))
                .withAmount(new BigDecimal("81"))
                .withUnit(Unit.PIECES)
                .withBarcode("1233212344325")
                .build();

        boolean result = productValidator.isValid(product);

        assertTrue(result);

        product.setAmount(new BigDecimal("-2"));

        result = productValidator.isValid(product);

        assertFalse(result);

        product.setName("");
        product.setAmount(new BigDecimal("11"));

        result = productValidator.isValid(product);

        assertFalse(result);

        product.setName("Test1");
        product.setPrice(new BigDecimal("12.5212"));

        result = productValidator.isValid(product);

        assertFalse(result);

        product.setPrice(new BigDecimal("12.55"));

        result = productValidator.isValid(product);

        assertTrue(result);

    }

    @Test
    public void getErrorMessages() {
        Validator<Product> productValidator = new ProductValidator();

        Product product = new Product.Builder()
                .withName("")
                .withPrice(new BigDecimal("12"))
                .withAmount(new BigDecimal("81"))
                .withUnit(Unit.PIECES)
                .withBarcode("1233212344325")
                .build();

        boolean result = productValidator.isValid(product);
        assertFalse(result);

        assertEquals("[error.empty.product.name.field]",
                productValidator.getErrorMessages().toString());
    }

    @Test
    public void getErrorValidationMessages() {
        Validator<Product> productValidator = new ProductValidator();

        Product product = new Product.Builder()
                .withName("Test1")
                .withPrice(new BigDecimal("12"))
                .withAmount(new BigDecimal("-81"))
                .withUnit(Unit.PIECES)
                .withBarcode("1233212344325")
                .build();

        boolean result = productValidator.isValid(product);
        assertFalse(result);

        assertEquals("[error.wrong.amount.validation, error.singed.price.validation]",
                productValidator.getErrorValidationMessages().toString());
    }
}