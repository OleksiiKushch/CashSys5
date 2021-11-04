package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.entity.Receipt;

import java.util.Objects;

public abstract class MySqlConstants {

    private MySqlConstants() {
        // hide
    }

    // Fields mapping

    // Product
    static class ProductField {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String AMOUNT = "amount";
        public static final String BARCODE = "barcode";
        public static final String UNIT = "unit_id";
    }



    // Queries mapping

    // Product
    static class ProductQuery {
        public static final String CREATE_PRODUCT = "INSERT INTO product (id, `name`, price, amount, barcode, unit_id) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?);";
        public static final String GET_ALL_PRODUCTS = "SELECT * FROM product;";
        public static final String FIND_PRODUCTS_BY_NAME = "SELECT * FROM product WHERE name LIKE ? ORDER BY name;";
        public static final String FIND_PRODUCTS_BY_BARCODE = "SELECT * FROM product WHERE barcode LIKE ? ORDER BY barcode;";

    }
}
