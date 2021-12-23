package com.finalprojultimate.util;

public final class Attribute {

    private Attribute() {
        // hide
    }

    // general
    public static final String PAGE = "page";
    public static final String LOCALE = "locale";

    // registration
    public static final String REGISTRATION_DATA = "registration_data";

    // login
    public static final String LOGIN_DATA = "login_data";
    public static final String LOGGED_USER = "logged_user";

    // pagination
    public static final String PAGE_COUNT = "page_count";
    public static final String PAGE_SIZE = "page_size";
    public static final String MIN_POSSIBLE_PAGE = "min_possible_page";
    public static final String MAX_POSSIBLE_PAGE = "max_possible_page";
    public static final String PAGINATE_PRODUCTS = "paginate_products";
    public static final String PAGINATE_RECEIPTS = "paginate_receipts";
    public static final String PAGINATE_USERS = "paginate_users";

    // sorting
    public static final String PRODUCT_SORT_PARAM = "product_sort_param";
    public static final String USER_SORT_PARAM = "user_sort_param";
    public static final String RECEIPT_SORT_PARAM = "receipt_sort_param";

    // create new receipt
    public static final String CART = "cart";
    // product searching
    public static final String PRODUCTS_FOUND = "products_found";
    public static final String PARAMETER_SEARCHING = "parameter_searching";
    public static final String PATTERN_SEARCHING = "pattern_searching";

    // general entity
    public static final String PRODUCT = "product";
    public static final String RECEIPT = "receipt";
    public static final String RECEIPT_ID = "receipt_id";
    public static final String RECEIPT_SERVICE = "receipt_service";
    public static final String RECEIPT_DETAILS = "receipt_details";
    public static final String GLOBAL_RECEIPT_PROPERTIES = "global_receipt_properties";

    public static final String USERS = "users";
    public static final String PRODUCTS = "products";
    public static final String PRODUCT_DATA = "product_data";
    public static final String GLOBAL_RECEIPT_PROPERTIES_DATA = "global_receipt_properties_data";

    public static final String AMOUNT = "amount";
    public static final String REPORT_BEST_PRODUCTS_BY_COUNT_RECEIPT = "report_best_products_by_count_receipt";

    // error
    public static final String ERROR_MESSAGES = "error_messages";
    public static final String ERROR_VALIDATION_MESSAGES = "error_validation_messages";
}
