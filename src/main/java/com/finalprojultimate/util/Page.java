package com.finalprojultimate.util;

import static com.finalprojultimate.util.Command.*;

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
    public static final String LOGIN_PAGE = PATH + GENERAL + LOGIN + JSP;
    public static final String REGISTRATION_PAGE = PATH + GENERAL + REGISTRATION + JSP;
    public static final String SUCCESSFUL_REGISTRATION_PAGE = PATH + SUCCESSFUL + SUCCESSFUL_REGISTRATION + JSP;
    public static final String MAIN_PAGE = PATH + GENERAL + MAIN + JSP;
    public static final String MY_PROFILE_PAGE = PATH + GENERAL + MY_PROFILE + JSP;

    // product
    public static final String PRODUCT_CATALOG_PAGE = PATH + PRODUCT_CATALOG + JSP;
    public static final String EDIT_PRODUCT_PAGE = PATH + EDIT_PRODUCT + JSP;
    public static final String SUCCESSFUL_UPDATE_PRODUCT_PAGE = PATH + SUCCESSFUL + SUCCESSFUL_UPDATE_PRODUCT + JSP;
    public static final String SUCCESSFUL_DELETE_PRODUCT_PAGE = PATH + SUCCESSFUL + SUCCESSFUL_DELETE_PRODUCT + JSP;
    public static final String CREATE_NEW_PRODUCT_PAGE = PATH + CREATE_NEW_PRODUCT + JSP;
    public static final String SUCCESSFUL_CREATE_NEW_PRODUCT_PAGE
            = PATH + SUCCESSFUL + SUCCESSFUL_CREATE_NEW_PRODUCT + JSP;

    // receipt
    public static final String RECEIPT_CATALOG_PAGE = PATH + RECEIPT_CATALOG + JSP;
    public static final String CREATE_NEW_RECEIPT_PAGE = PATH + CREATE_NEW_RECEIPT + JSP;
    public static final String CART_OF_PRODUCTS_PAGE = PATH + CART_OF_PRODUCTS + JSP;
    public static final String SUCCESSFUL_CREATE_NEW_RECEIPT_PAGE
            = PATH + SUCCESSFUL + SUCCESSFUL_CREATE_NEW_RECEIPT + JSP;
    public static final String SHOW_CREATED_RECEIPT_PAGE = PATH + SHOW_CREATED_RECEIPT + JSP;
    public static final String SET_GLOBAL_RECEIPT_PROPERTIES_PAGE = PATH + SET_GLOBAL_RECEIPT_PROPERTIES + JSP;
    public static final String SUCCESSFUL_SET_GLOBAL_RECEIPT_PROPERTIES_PAGE
            = PATH + SUCCESSFUL + SUCCESSFUL_SET_GLOBAL_RECEIPT_PROPERTIES + JSP;
    public static final String SUCCESSFUL_RESET_GLOBAL_RECEIPT_PROPERTIES_PAGE
            = PATH + SUCCESSFUL + SUCCESSFUL_RESET_GLOBAL_RECEIPT_PROPERTIES + JSP;
    public static final String SEE_RECEIPT_DETAILS_PAGE = PATH + SEE_RECEIPT_DETAILS + JSP;
    public static final String SUCCESSFUL_CREATE_NEW_REJECT_RECEIPT_PAGE
            = PATH + SUCCESSFUL + SUCCESSFUL_CREATE_NEW_REJECT_RECEIPT + JSP;

    // user
    public static final String USER_CATALOG_PAGE = PATH + USER_CATALOG + JSP;

    // report
    public static final String GENERATE_REPORT_PAGE = PATH + REPORT + GENERATE_REPORT + JSP;
    public static final String BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH
            = PATH + REPORT + Command.BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH + JSP;
    public static final String BEST_PRODUCTS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH
            = PATH + REPORT + Command.BEST_PRODUCTS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH + JSP;

}
