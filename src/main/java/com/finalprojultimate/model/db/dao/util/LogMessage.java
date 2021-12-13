package com.finalprojultimate.model.db.dao.util;

public abstract class LogMessage {

    private LogMessage() {
        // hide
    }

    // entity
    public static final String GETTING_ROW_FROM_DATABASE_LOG_MSG = "Error while getting concrete row from the table";
    public static final String GETTING_ALL_ROWS_FROM_DATABASE_LOG_MSG = "Error while select all rows from the table";
    public static final String INSERT_ROW_TO_DATABASE_LOG_MSG = "Error while inserting row to the table";
    public static final String UPDATE_ROW_TO_DATABASE_LOG_MSG = "Error while updating row in the table";
    public static final String DELETE_ROW_FROM_DATABASE_LOG_MSG = "Error while deleting row in the table";
    public static final String GET_COUNT_ROWS_IN_DATABASE_LOG_MSG = "Error while getting count of rows in the table";

    // product
    public static final String FIND_PRODUCTS_WITH_PAGINATION_FROM_DATABASE_LOG_MSG
            = "Error while select rows (products) from the product table with pagination";
    public static final String FIND_PRODUCTS_BY_QUERY_FROM_DATABASE_LOG_MSG
            = "Error while select rows (products) from the product table by query";
    public static final String FIND_BEST_PRODUCTS_BY_COUNT_RECEIPT_FROM_DATABASE_LOG_MSG
            = "Error while get rows (products, total_amount, total_sum, count receipts) sorting by count receipt";

    // receipt
    public static final String CREATE_RECEIPT_IN_DATABASE_LOG_MSG = "Error while creating receipt";
    public static final String CREATE_REJECT_RECEIPT_IN_DATABASE_LOG_MSG = "Error while creating reject receipt";
    public static final String FIND_RECEIPTS_WITH_PAGINATION_FROM_DATABASE_LOG_MSG
            = "Error while select rows (receipts) from the receipt table with pagination";
    public static final String GET_GLOBAL_RECEIPT_PROPERTIES_FROM_DATABASE_LOG_MSG
            = "Error while concrete row from the global receipt properties table";
    public static final String SET_GLOBAL_RECEIPT_PROPERTIES_TO_DATABASE_LOG_MSG
            = "Error while inserting row to the global receipt properties table";
    public static final String RESET_GLOBAL_RECEIPT_PROPERTIES_IN_DATABASE_LOG_MSG
            = "Error while deleting row in the global receipt properties table";
    public static final String GET_SUM_RECEIPT_BY_ID_FROM_DATABASE_LOG_MSG = "Error while get sum receipt by id";
    public static final String GET_PRODUCTS_BY_RECEIPT_ID_FROM_DATABASE_LOG_MSG = "Error while get products by receipt id";
    public static final String GET_RECEIPT_DETAILS_BY_ID_FROM_DATABASE_LOG_MSG = "Error while get receipt details by receipt id";
    public static final String PROCESSING_REJECT_RECEIPT_IN_DATABASE_LOG_MSG = "Error while processing reject receipt";

    // user
    public static final String GET_USER_BY_EMAIL_FROM_DATABASE_LOG_MSG = "Error while get row (user) by email";
    public static final String FIND_USERS_WITH_PAGINATION_FROM_DATABASE_LOG_MSG
            = "Error while select rows (users) from the user table with pagination";
    public static final String FIND_BEST_CASHIERS_BY_COUNT_RECEIPT_FROM_DATABASE_LOG_MSG
            = "Error while get rows (cashiers, count receipts) sorting by count receipt";

}
