package com.finalprojultimate.util;

public final class Command {

    private Command() {
        // hide
    }

    public static final String CONTROLLER = "FrontController";

    public static final String REDIRECTED = "redirected";

    // general
    public static final String LOGIN = "/login";
    public static final String REGISTRATION = "/registration";
    public static final String SUCCESSFUL_REGISTRATION = "/successful_registration";
    public static final String MAIN = "/main";
    public static final String LOGOUT = "/logout";
    public static final String CHANGE_LOCAL = "/change_local";
    public static final String MY_PROFILE = "/my_profile";

    // product
    public static final String PRODUCT_CATALOG = "/product_catalog";
    public static final String EDIT_PRODUCT = "/edit_product";
    public static final String DELETE_PRODUCT = "/delete_product";
    public static final String SUCCESSFUL_UPDATE_PRODUCT = "/successful_update_product";
    public static final String SUCCESSFUL_DELETE_PRODUCT = "/successful_delete_product";
    public static final String CREATE_NEW_PRODUCT = "/create_new_product";
    public static final String SUCCESSFUL_CREATE_NEW_PRODUCT = "/successful_create_new_product";
    public static final String FIND_PRODUCTS_BY_PARAMETER = "/find_products_by_parameter";
    public static final String ADD_PRODUCT_TO_CART = "/add_product_to_cart";
    public static final String EDIT_PRODUCT_AMOUNT_FROM_CART = "/edit_product_amount_from_cart";
    public static final String DELETE_PRODUCT_FROM_CART = "/delete_product_from_cart";
    public static final String CART_OF_PRODUCTS = "/cart_of_products";

    // receipt
    public static final String RECEIPT_CATALOG = "/receipt_catalog";
    public static final String NEW_RECEIPT = "/new_receipt";
    public static final String CREATE_NEW_RECEIPT = "/create_new_receipt";
    public static final String SUCCESSFUL_CREATE_NEW_RECEIPT = "/successful_create_new_receipt";
    public static final String SHOW_CREATED_RECEIPT = "/show_created_receipt";
    public static final String SET_GLOBAL_RECEIPT_PROPERTIES = "/set_global_receipt_properties";
    public static final String SUCCESSFUL_SET_GLOBAL_RECEIPT_PROPERTIES = "/successful_set_global_receipt_properties";
    public static final String RESET_GLOBAL_RECEIPT_PROPERTIES = "/reset_global_receipt_properties";
    public static final String SUCCESSFUL_RESET_GLOBAL_RECEIPT_PROPERTIES
            = "/successful_reset_global_receipt_properties";
    public static final String SEE_RECEIPT_DETAILS = "/see_receipt_details";
    public static final String CREATE_REJECT_RECEIPT = "/create_reject_receipt";
    public static final String SUCCESSFUL_CREATE_NEW_REJECT_RECEIPT = "/successful_create_new_reject_receipt";

    // user
    public static final String USER_CATALOG = "/user_catalog";

    // report
    public static final String GENERATE_REPORT = "/generate_report";
    public static final String BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH
            = "/best_cashiers_by_count_receipts_for_the_last_month";
    public static final String BEST_PRODUCTS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH
            = "/best_products_by_count_receipts_for_the_last_month";
}
