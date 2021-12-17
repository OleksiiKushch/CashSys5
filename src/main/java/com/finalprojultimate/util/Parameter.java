package com.finalprojultimate.util;

public final class Parameter {

    private Parameter() {
        // hide
    }

    // user
    public static final String USER_ID = "userId";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "firstName";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";
    public static final String PASSWORD = "password";
    public static final String CONFIRMATION_PASSWORD = "confirmationPassword";
    public static final String ROLE = "role";

    // product
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_NAME = "productName";
    public static final String PRICE = "price";
    public static final String AMOUNT = "amount";
    public static final String UNIT = "unit";
    public static final String BARCODE = "barcode";

    // receipt
    public static final String RECEIPT_ID = "receiptId";
    public static final String DATE_TIME = "dateTime";
    public static final String PAYMENT = "payment";
    public static final String PAID = "paid";

    public static final String REJECT_RECEIPT_ID = "rejectReceiptId";

    public static final String ORGANIZATION_TAX_ID_NUMBER = "organizationTaxIdNumber";
    public static final String NAME_ORGANIZATION = "nameOrganization";
    public static final String ADDRESS_TRADE_POINT = "addressTradePoint";
    public static final String VAT = "vat";
    public static final String TAXATION_SYS = "taxationSys";

    // optional
    public static final String NONE = "none";

    public static final String BY_NAME = "byName";
    public static final String BY_BARCODE = "byBarcode";
}
