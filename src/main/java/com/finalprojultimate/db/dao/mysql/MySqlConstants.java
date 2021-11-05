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
        public static final String UNIT_ID = "unit_id";
    }

    // User
    static class UserField {
        public static final String ID = "id";
        public static final String FIRST_NAME = "first_name";
        public static final String MIDDLE_NAME = "middle_name";
        public static final String LAST_NAME = "last_name";
        public static final String PASS_HASH = "passhash";
        public static final String ROLE_ID = "role_id";
    }

    // Receipt
    static class ReceiptField {
        public static final String ID = "id";
        public static final String DATE_TIME = "date_time";
        public static final String CHANGE = "change";
        public static final String PAYMENT_ID = "payment_id";
        public static final String USER_ID = "user_id";
        public static final String STATUS_ID = "status_id";
    }


    // Queries mapping

    // Product
    static class ProductQuery {
        public static final String CREATE_PRODUCT = "INSERT INTO product (id, `name`, price, amount, barcode, unit_id) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?);";
        public static final String UPDATE_PRODUCT = "UPDATE product " +
                "SET `name` = ?, price = ?, amount = ?, barcode = ?, unit_id = ? " +
                "WHERE barcode = ?;";
        public static final String DELETE_PRODUCT_BY_BARCODE = "DELETE FROM product WHERE barcode = ?;";
        public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?;";
        public static final String GET_ALL_PRODUCTS = "SELECT * FROM product;";
        public static final String FIND_PRODUCTS_BY_NAME = "SELECT * FROM product WHERE `name` LIKE ? ORDER BY `name`;";
        public static final String FIND_PRODUCTS_BY_BARCODE = "SELECT * FROM product WHERE barcode LIKE ? ORDER BY barcode;";
    }

    // User
    static class UserQuery {
        public static final String CREATE_USER = "INSERT INTO `user` (id, first_name, middle_name, last_name, passhash, role_id) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?);";
        public static final String UPDATE_USER = "UPDATE `user` " +
                "SET first_name = ?, middle_name = ?, last_name = ?, passhash = ?, role_id = ? " +
                "WHERE id = ?;";
        public static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE id = ?;";
        public static final String GET_USER_BY_ID = "SELECT * FROM `user` WHERE id = ?;";
        public static final String GET_ALL_USERS = "SELECT * FROM `user`;";
    }

    // Receipt
    static class ReceiptQuery {
        public static final String CREATE_RECEIPT = "INSERT INTO receipt (id, date_time, `change`, payment_id, user_id, status_id) " +
                "VALUES (DEFAULT, now(), ?, ?, ?, ?);";
        public static final String UPDATE_RECEIPT = "UPDATE receipt " +
                "SET `change` = ?, payment_id = ?, user_id = ?, status_id = ? " +
                "WHERE id = ?;";
        public static final String DELETE_RECEIPT_BY_ID = "DELETE FROM receipt WHERE id = ?;";
        public static final String GET_RECEIPT_BY_ID = "SELECT * FROM receipt WHERE id = ?;";
        public static final String GET_ALL_RECEIPTS = "SELECT * FROM receipt;";
    }


}
