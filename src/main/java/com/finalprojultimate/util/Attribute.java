package com.finalprojultimate.util;

public abstract class Attribute {

    private Attribute() {
        // hide
    }

    public static final String PAGE = "page";

    public static final String PAGE_COUNT = "page_count";
    public static final String PAGE_SIZE = "page_size";
    public static final String SORT_BY = "sort_by";
    public static final String MIN_POSSIBLE_PAGE = "min_possible_page";
    public static final String MAX_POSSIBLE_PAGE = "max_possible_page";
    public static final String PARAMETER_SEARCHING = "parameter_searching";
    public static final String PATTERN_SEARCHING = "pattern_searching";

    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String LOGIN_DATA = "login_data";
    public static final String LOGGED_USER = "logged_user";
    public static final String CART = "cart";
    public static final String AMOUNT = "amount";
    public static final String SUM = "sum";

    public static final String PRODUCT = "product";
    public static final String PRODUCTS = "products";
    public static final String USER_SERVICE = "user_service";
    public static final String RECEIPT_SERVICE = "receipt_service";
    public static final String RECEIPT_DETAILS = "receipt_details";
    public static final String PRODUCT_CATALOG = "product_catalog";
    public static final String RECEIPT_CATALOG = "receipt_catalog";
    public static final String PAGINATE_PRODUCTS = "paginate_products";
    public static final String PAGINATE_RECEIPTS = "paginate_receipts";
    public static final String PRODUCTS_FOUND = "products_found";
    public static final String GLOBAL_RECEIPT_PROPERTIES = "global_receipt_properties";
    public static final String RECEIPT = "receipt";

    public static final String LOCALE = "locale";

    public static final String ERROR_MESSAGE = "error_message";
    public static final String ERROR_VALIDATION_MESSAGE = "error_validation_message";
}
