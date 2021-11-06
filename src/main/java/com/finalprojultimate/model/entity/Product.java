package com.finalprojultimate.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private BigDecimal amount;
    private Unit unit;
    private String barcode;

    public Product() {
        // default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public enum Unit {
        PIECES (1, "pieces"),
        KILOGRAM (2, "kilogram"),
        LITRE (3, "litre");

        private final int id;
        private final String name;

        Unit(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * return null if element by id not found
         */
        public static Unit getById(int id) {
            for (Unit rootCategory : Unit.values()) {
                if (rootCategory.getId() == id)
                    return rootCategory;
            }
            return null;
        }

        /**
         * return null if element by name not found
         */
        public static Unit getByName(String name) {
            for (Unit unit : Unit.values()) {
                if (Objects.equals(unit.getName(), name))
                    return unit;
            }
            return null;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Unit{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
