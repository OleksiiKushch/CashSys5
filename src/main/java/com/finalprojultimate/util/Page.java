package com.finalprojultimate.util;

public abstract class Page {

    private Page() {
        // hide
    }

    private static final String JSP = ".jsp";
    private static final String PATH = "/WEB-INF/view/jsp";

    public static final String MAIN_PAGE = PATH + "/main" + JSP;

    public static final String LOGIN_PAGE = PATH + "/login" + JSP;
    public static final String REGISTRATION_PAGE = PATH + "/registration" + JSP;
    public static final String SUCCESSFUL_REGISTRATION_PAGE = PATH + "/successful_registration" + JSP;

    public static final String PRODUCT_CATALOG_PAGE = PATH + "/product_catalog" + JSP;

    public static final String INTERNAL_SERVER_ERROR_PAGE = PATH + "/error/internal_server_error" + JSP;
    public static final String PAGE_NOT_FOUND_ERROR_PAGE = PATH + "/error/page_not_found" + JSP;

}
