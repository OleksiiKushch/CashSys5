package com.finalprojultimate.model.service.util;

import com.finalprojultimate.model.entity.product.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class CartTest {

    @Test
    public void cartTest() {
        Cart cart = new Cart();
        LinkedHashMap<Integer, Product> container = new LinkedHashMap<>();
        cart.setContainer(container);
        cart.put(1, new Product.Builder().withPrice(new BigDecimal("10")).withAmount(new BigDecimal("2")).build());
        cart.put(2, new Product.Builder().withPrice(new BigDecimal("4.5")).withAmount(new BigDecimal("3")).build());
        assertEquals("Cart{sum=33.5, container={1=Product{id=0, name='null', price=10, amount=2, unit=null, barcode='null'}, " +
                "2=Product{id=0, name='null', price=4.5, amount=3, unit=null, barcode='null'}}}",
                cart.toString());
        cart.put(1, new Product.Builder().withPrice(new BigDecimal("10")).withAmount(new BigDecimal("1")).build());
        assertEquals("Cart{sum=43.5, container={1=Product{id=0, name='null', price=10, amount=3, unit=null, barcode='null'}, " +
                        "2=Product{id=0, name='null', price=4.5, amount=3, unit=null, barcode='null'}}}",
                cart.toString());
        assertEquals("43.5", cart.getSum().toString());
        cart.remove(2);
        assertEquals("Cart{sum=30, container={1=Product{id=0, name='null', price=10, amount=3, unit=null, barcode='null'}}}",
                cart.toString());
        cart.updateAmount(1, new BigDecimal("1"));
        assertEquals("Cart{sum=10, container={1=Product{id=0, name='null', price=10, amount=1, unit=null, barcode='null'}}}",
                cart.toString());
    }

}