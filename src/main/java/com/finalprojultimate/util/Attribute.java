package com.finalprojultimate.util;

public abstract class Attribute {

    private Attribute() {
        // hide
    }

    public static final String USER = "user";
    public static final String PRODUCT = "product";

    public static final String LOGIN_DATA = "login_data";

    public static final String LOGGED_USER = "logged_user";
    public static final String PRODUCT_CATALOG = "product_catalog";
    public static final String RECEIPT_CATALOG = "receipt_catalog";

    public static final String ERROR_MESSAGE = "error_message";
    public static final String ERROR_VALIDATION_MESSAGE = "error_validation_message";
}
