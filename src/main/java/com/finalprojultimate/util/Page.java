package com.finalprojultimate.util;

public abstract class Page {

    private Page() {
        // hide
    }

    private static final String JSP = ".jsp";
    private static final String PATH = "/WEB-INF/view/jsp";
    private static final String ERROR = "/error";
    private static final String GENERAL = "/general";
    private static final String SUCCESSFUL = "/successful";
    private static final String REPORT = "/report";

    public static final String INTERNAL_SERVER_ERROR_PAGE = PATH + ERROR + "/internal_server_error" + JSP;
    public static final String PAGE_NOT_FOUND_ERROR_PAGE = PATH + ERROR + "/page_not_found" + JSP;

    // general
    public static final String MAIN_PAGE = PATH + GENERAL + "/main" + JSP;
    public static final String LOGIN_PAGE = PATH + GENERAL + "/login" + JSP;
    public static final String REGISTRATION_PAGE = PATH + GENERAL + "/registration" + JSP;
    public static final String SUCCESSFUL_REGISTRATION_PAGE = PATH + SUCCESSFUL + "/successful_registration" + JSP;
    public static final String MY_PROFILE_PAGE = PATH + GENERAL + "/my_profile" + JSP;

    // product
    public static final String PRODUCT_CATALOG_PAGE = PATH + "/product_catalog" + JSP;
    public static final String EDIT_PRODUCT_PAGE = PATH + "/edit_product" + JSP;
    public static final String SUCCESSFUL_UPDATE_PRODUCT_PAGE = PATH + SUCCESSFUL + "/successful_update_product" + JSP;
    public static final String SUCCESSFUL_DELETE_PRODUCT_PAGE = PATH + SUCCESSFUL + "/successful_delete_product" + JSP;
    public static final String CREATE_NEW_PRODUCT_PAGE = PATH + "/create_new_product" + JSP;
    public static final String SUCCESSFUL_CREATE_NEW_PRODUCT_PAGE
            = PATH + SUCCESSFUL + "/successful_create_new_product" + JSP;

    // receipt
    public static final String RECEIPT_CATALOG_PAGE = PATH + "/receipt_catalog" + JSP;
    public static final String CREATE_NEW_RECEIPT_PAGE = PATH + "/create_new_receipt" + JSP;
    public static final String CART_OF_PRODUCTS_PAGE = PATH + "/cart_of_products" + JSP;
    public static final String SUCCESSFUL_CREATE_NEW_RECEIPT_PAGE
            = PATH + SUCCESSFUL + "/successful_create_new_receipt" + JSP;
    public static final String SET_GLOBAL_RECEIPT_PROPERTIES_PAGE = PATH + "/set_global_receipt_properties" + JSP;
    public static final String SUCCESSFUL_SET_GLOBAL_RECEIPT_PROPERTIES_PAGE
            = PATH + SUCCESSFUL + "/successful_set_global_receipt_properties" + JSP;
    public static final String SUCCESSFUL_RESET_GLOBAL_RECEIPT_PROPERTIES_PAGE
            = PATH + SUCCESSFUL + "/successful_reset_global_receipt_properties" + JSP;
    public static final String SEE_RECEIPT_DETAILS_PAGE = PATH + "/see_receipt_details" + JSP;
    public static final String SUCCESSFUL_CREATE_NEW_REJECT_RECEIPT_PAGE
            = PATH + SUCCESSFUL + "/successful_create_new_reject_receipt" + JSP;

    // user
    public static final String USER_CATALOG_PAGE = PATH + "/user_catalog" + JSP;

    // report
    public static final String GENERATE_REPORT_PAGE = PATH + REPORT + "/generate_report" + JSP;
    public static final String BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH
            = PATH + REPORT + "/best_cashiers_by_count_receipts_for_the_last_month" + JSP;
    public static final String BEST_PRODUCTS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH
            = PATH + REPORT + "/best_products_by_count_receipts_for_the_last_month" + JSP;

}
