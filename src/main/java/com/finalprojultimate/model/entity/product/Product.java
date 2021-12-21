package com.finalprojultimate.model.entity.product;

import com.finalprojultimate.model.entity.Entity;
import com.finalprojultimate.model.entity.user.Role;
import com.finalprojultimate.model.entity.user.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Parameter.ROLE;

public class Product extends Entity {
    private static final long serialVersionUID = -7715657503844300913L;

    private String name;
    private BigDecimal price;
    private BigDecimal amount;
    private Unit unit;
    private String barcode;

    public Product() {
        // default constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        if (amount == null) {
            return amount;
        }
        if (unit == Unit.PIECES)
            return amount.setScale(0, RoundingMode.DOWN);
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public static List<String> getListStrFormatParameters() {
        return new ArrayList<>(Arrays.asList(PRODUCT_NAME, PRICE, AMOUNT, UNIT, BARCODE));
    }

    public static Product mapProduct(List<String> listStrFormatAttributes) {
        Product result = new Product();
        int i = -1;
        result.setName(listStrFormatAttributes.get(++i));
        if (!listStrFormatAttributes.get(++i).isEmpty()) { // price
            result.setPrice(new BigDecimal(listStrFormatAttributes.get(i)));
        }
        if (!listStrFormatAttributes.get(++i).isEmpty()) { // amount
            result.setAmount(new BigDecimal(listStrFormatAttributes.get(i)));
        }
        result.setUnit(Unit.getByName(listStrFormatAttributes.get(++i)));
        result.setBarcode(listStrFormatAttributes.get(++i));
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", unit=" + unit +
                ", barcode='" + barcode + '\'' +
                '}';
    }

    public static class Builder {
        private final Product newProduct;

        public Builder() {
            newProduct = new Product();
        }

        public Builder withId(int id) {
            newProduct.id = id;
            return this;
        }

        public Builder withName(String name) {
            newProduct.name = name;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            newProduct.price = price;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            newProduct.amount = amount;
            return this;
        }

        public Builder withUnit(Unit unit) {
            newProduct.unit = unit;
            return this;
        }

        public Builder withBarcode(String barcode) {
            newProduct.barcode = barcode;
            return this;
        }

        public Product build() {
            return newProduct;
        }
    }
}
