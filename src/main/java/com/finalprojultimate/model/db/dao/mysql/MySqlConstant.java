package com.finalprojultimate.model.db.dao.mysql;

import java.math.BigInteger;

public abstract class MySqlConstant {

    private MySqlConstant() {
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

        // ReportBestProductByCountReceipt
        public static final String PRODUCT_ID = "product_id";
        public static final String TOTAL_AMOUNT = "total_amount";
        public static final String TOTAL_SUM = "total_sum";
        public static final String COUNT = "count";
    }

    // User
    static class UserField {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "first_name";
        public static final String MIDDLE_NAME = "middle_name";
        public static final String LAST_NAME = "last_name";
        public static final String PASS_HASH = "passhash";
        public static final String ROLE_ID = "role_id";

        public static final String COUNT = "count";
    }

    // Receipt
    static class ReceiptField {
        public static final String ID = "id";
        public static final String DATE_TIME = "date_time";
        public static final String CHANGE = "change";
        public static final String PAYMENT_ID = "payment_id";
        public static final String USER_ID = "user_id";
        public static final String STATUS_ID = "status_id";

        // ReceiptDetails
        public static final String RECEIPT_ID = "receipt_id";
        public static final String ROOT_RECEIPT_ID = "root_receipt_id";
        public static final String ORGANIZATION_TAX_ID_NUMBER = "organization_tax_id_number";
        public static final String NAME_ORGANIZATION = "name_organization";
        public static final String ADDRESS_TRADE_POINT = "address_trade_point";
        public static final String VAT = "vat";
        public static final String TAXATION_SYS = "taxation_sys";
    }


    // Queries mapping

    // Product
    static class ProductQuery {
        public static final String CREATE_PRODUCT = "INSERT INTO product (id, `name`, price, amount, barcode, unit_id) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        public static final String UPDATE_PRODUCT = "UPDATE product " +
                "SET `name` = ?, price = ?, amount = ?, barcode = ?, unit_id = ? " +
                "WHERE id = ?";
        public static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE id = ?";
        public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
        public static final String GET_ALL_PRODUCTS = "SELECT * FROM product";
        public static final String FIND_PRODUCTS_BY_NAME = "SELECT * FROM product WHERE `name` LIKE ? ORDER BY `name`";
        public static final String FIND_PRODUCTS_BY_BARCODE = "SELECT * FROM product WHERE barcode LIKE ? ORDER BY barcode";
        public static final String FIND_PRODUCTS_SORT_BY_NONE = "SELECT * FROM product LIMIT ? OFFSET ?";
        public static final String FIND_PRODUCTS_SORT_BY_NAME = "SELECT * FROM product ORDER BY `name` LIMIT ? OFFSET ?";
        public static final String GET_COUNT_OF_PRODUCTS = "SELECT COUNT(*) FROM product";
        public static final String FIND_BEST_PRODUCTS_BY_COUNT_RECEIPT_FOR_THE_LAST_MONTH = "SELECT receipt_has_product.product_id, " +
                "sum(receipt_has_product.amount) AS total_amount, " +
                "sum(receipt_has_product.amount * receipt_has_product.price) AS total_sum, " +
                "count(*) AS count FROM receipt_has_product " +
                "JOIN receipt ON receipt_has_product.receipt_id = receipt.id " +
                "WHERE receipt.date_time >= DATE_SUB(NOW(), INTERVAL 1 MONTH) " +
                "GROUP BY receipt_has_product.product_id " +
                "ORDER BY count DESC " +
                "LIMIT ?";

        public static final String GET_AMOUNT_PRODUCT_IN_STOCK_BY_ID = "SELECT amount FROM product WHERE id = ?";
        public static final String UPDATE_AMOUNT_PRODUCT_IN_STOCK_BY_ID = "UPDATE product SET amount = ? WHERE id = ?";
    }

    // User
    static class UserQuery {
        public static final String CREATE_USER = "INSERT INTO `user` (id, email, first_name, middle_name, last_name, passhash, role_id) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
        public static final String UPDATE_USER = "UPDATE `user` " +
                "SET email = ?, first_name = ?, middle_name = ?, last_name = ?, passhash = ?, role_id = ? " +
                "WHERE id = ?";
        public static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE id = ?";
        public static final String GET_USER_BY_ID = "SELECT * FROM `user` WHERE id = ?";
        public static final String GET_ALL_USERS = "SELECT * FROM `user`";
        public static final String GET_USER_BY_EMAIL = "SELECT * FROM `user` WHERE email = ?";
        public static final String FIND_USERS_SORT_BY_NONE = "SELECT * FROM user LIMIT ? OFFSET ?";
        public static final String FIND_USERS_SORT_BY_EMAIL = "SELECT * FROM user ORDER BY `email` LIMIT ? OFFSET ?";
        public static final String GET_COUNT_OF_USERS = "SELECT COUNT(*) FROM user";
        public static final String FIND_BEST_CASHIERS_BY_COUNT_RECEIPT_FOR_THE_LAST_MONTH
                = "SELECT `user`.id, count(*) AS count  FROM `user` " +
                "JOIN user_role ON `user`.role_id = user_role.id " +
                "JOIN receipt ON `user`.id = receipt.user_id " +
                "WHERE user_role.`name` = 'cashier' AND receipt.date_time >= DATE_SUB(NOW(), INTERVAL 1 MONTH) " +
                "GROUP BY `user`.id " +
                "ORDER BY count DESC " +
                "LIMIT ?";
    }

    // Receipt
    static class ReceiptQuery {
        public static final String CREATE_RECEIPT = "INSERT INTO receipt (id, date_time, `change`, payment_id, user_id, status_id) " +
                "VALUES (DEFAULT, now(), ?, ?, ?, ?)";
        public static final String UPDATE_RECEIPT = "UPDATE receipt " +
                "SET `change` = ?, payment_id = ?, user_id = ?, status_id = ? " +
                "WHERE id = ?";
        public static final String DELETE_RECEIPT_BY_ID = "DELETE FROM receipt WHERE id = ?";
        public static final String GET_RECEIPT_BY_ID = "SELECT * FROM receipt WHERE id = ?";
        public static final String GET_ALL_RECEIPTS = "SELECT * FROM receipt";
        public static final String FIND_RECEIPTS_SORT_BY_NONE = "SELECT * FROM receipt LIMIT ? OFFSET ?";
        public static final String FIND_RECEIPTS_SORT_BY_DATE_TIME = "SELECT * FROM receipt ORDER BY date_time DESC LIMIT ? OFFSET ?";
        public static final String GET_COUNT_OF_RECEIPTS = "SELECT COUNT(*) FROM receipt";
        public static final String INSERT_RECEIPT_HAS_PRODUCT = "INSERT INTO receipt_has_product (receipt_id, product_id, price, amount) " +
                "VALUES (?, ?, ?, ?)";
        public static final String GET_GLOBAL_RECEIPT_PROPERTIES = "SELECT * FROM global_receipt_properties";
        public static final String SET_GLOBAL_RECEIPT_PROPERTIES = "CALL set_global_receipt_properties(?, ?, ?, ?, ?)";
        public static final String SET_RECEIPT_DETAILS = "INSERT INTO receipt_details (receipt_id, root_receipt_id, organization_tax_id_number, " +
                "name_organization, address_trade_point, vat, taxation_sys) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // TRUNCATE is faster than DELETE since it does not generate rollback information and does not fire any delete triggers
        public static final String RESET_GLOBAL_RECEIPT_PROPERTIES = "TRUNCATE global_receipt_properties";
        public static final String GET_SUM_RECEIPT_BY_ID = "SELECT SUM(price * amount) FROM receipt_has_product WHERE receipt_id = ?";
        public static final String GET_PRODUCTS_BY_RECEIPT_ID = "SELECT p.id, p.`name`, rhp.price, rhp.amount, p.barcode, p.unit_id " +
                "FROM product AS p JOIN receipt_has_product AS rhp ON p.id = product_id WHERE receipt_id = ?";
        public static final String GET_RECEIPT_DETAILS_BY_ID = "SELECT * FROM receipt_details WHERE receipt_id = ?";
        public static final String PROCESSING_REJECT_RECEIPT= "CALL processing_reject_receipt(?, ?, ?)";
    }


}
