package com.finalprojultimate.model.services.impl;

import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.services.util.Cart;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ReceiptServiceImplTest {

    @Test
    public void create() {
        Receipt receipt = new Receipt.Builder()
                .withChange(new BigDecimal("0"))
                .build();

        Cart car = new Cart();

        Product product1 = new Product.Builder()
                .withId(1)
                .build();
        Product product2 = new Product.Builder()
                .withId(2)
                .build();
        Product product3 = new Product.Builder()
                .withId(3)
                .build();
        Product product4 = new Product.Builder()
                .withId(4)
                .build();


    }
}