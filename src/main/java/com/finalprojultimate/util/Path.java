package com.finalprojultimate.util;

import static com.finalprojultimate.util.Attribute.*;

public final class Path {

    private Path() {
        // hide
    }

    public static final String DEFAULT_SIZE_REPORT_PAGE = "5";
    public static final String DEFAULT_SIZE_CATALOG_PAGE = "8";
    public static final String DEFAULT_START_PAGE_NUMBER = "1";

    public static final String COMMAND = "command";
    public static final String DELIMITER = ":";
    public static final String QUESTION_MARK = "?";
    public static final String EQUALS_MARK = "=";
    public static final String AMPERSAND = "&";

    public static final String APP_CONTEXT = "/FinalProjUltimate";
    public static final String CONTROLLER = "/FrontController";

    // general
    public static final String LOGIN = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.LOGIN;
    public static final String REGISTRATION = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.REGISTRATION;
    public static final String MAIN = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.MAIN;
    public static final String MY_PROFILE = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.MY_PROFILE;

    // product
    public static final String PRODUCT_CATALOG = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.PRODUCT_CATALOG
            + AMPERSAND + PAGE + EQUALS_MARK + DEFAULT_START_PAGE_NUMBER
            + AMPERSAND + PAGE_SIZE + EQUALS_MARK + DEFAULT_SIZE_CATALOG_PAGE;
    public static final String CREATE_NEW_PRODUCT = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.CREATE_NEW_PRODUCT;
    public static final String CART_OF_PRODUCTS = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.CART_OF_PRODUCTS;

    // receipt
    public static final String RECEIPT_CATALOG = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.RECEIPT_CATALOG
            + AMPERSAND + PAGE + EQUALS_MARK + DEFAULT_START_PAGE_NUMBER
            + AMPERSAND + PAGE_SIZE + EQUALS_MARK + DEFAULT_SIZE_CATALOG_PAGE;
    public static final String NEW_RECEIPT = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.NEW_RECEIPT;
    public static final String CREATE_NEW_RECEIPT = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.CREATE_NEW_RECEIPT;
    public static final String SET_GLOBAL_RECEIPT_PROPERTIES = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.SET_GLOBAL_RECEIPT_PROPERTIES;
    public static final String RESET_GLOBAL_RECEIPT_PROPERTIES = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.RESET_GLOBAL_RECEIPT_PROPERTIES;
    public static final String CREATE_REJECT_RECEIPT = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.CREATE_REJECT_RECEIPT;;

    // user
    public static final String USER_CATALOG = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.USER_CATALOG
            + AMPERSAND + PAGE + EQUALS_MARK + DEFAULT_START_PAGE_NUMBER
            + AMPERSAND + PAGE_SIZE + EQUALS_MARK + DEFAULT_SIZE_CATALOG_PAGE;


    // report
    public static final String GENERATE_REPORT = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + Command.GENERATE_REPORT;
    public static final String BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_MONTH = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK
            + Command.BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH + AMPERSAND + PAGE_SIZE + EQUALS_MARK + DEFAULT_SIZE_REPORT_PAGE;
    public static final String BEST_PRODUCTS_BY_COUNT_RECEIPTS_FOR_THE_MONTH = APP_CONTEXT + CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK
            + Command.BEST_PRODUCTS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH + AMPERSAND+ PAGE_SIZE + EQUALS_MARK + DEFAULT_SIZE_REPORT_PAGE;

}
