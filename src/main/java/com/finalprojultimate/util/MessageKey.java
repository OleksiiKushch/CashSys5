package com.finalprojultimate.util;

public abstract class MessageKey {

    private MessageKey() {
        // hide
    }

    // FINE MESSAGES
    public static final String OK_MESSAGE = "message.ok";

    // ERROR MESSAGES

    // user
    public static final String ERROR_EMPTY_EMAIL = "error.empty.email.field";
    public static final String ERROR_EMPTY_PASSWORD = "error.empty.password.field";
    public static final String ERROR_EMPTY_FIRST_NAME = "error.empty.first.name.field";
    public static final String ERROR_EMPTY_MIDDLE_NAME = "error.empty.middle.name.field";
    public static final String ERROR_EMPTY_LAST_NAME = "error.empty.last.name.field";
    public static final String ERROR_EMPTY_ROLE = "error.empty.role.field";
    public static final String ERROR_WRONG_EMAIL = "error.wrong.email.validation";
    public static final String ERROR_WRONG_PASSWORD = "error.wrong.password.validation";
    public static final String ERROR_WRONG_FIRST_NAME = "error.wrong.first.name.validation";
    public static final String ERROR_WRONG_MIDDLE_NAME = "error.wrong.middle.name.validation";
    public static final String ERROR_WRONG_LAST_NAME = "error.wrong.last.name.validation";
    public static final String ERROR_INCORRECT_LOGIN_DATA = "error.incorrect.email.or.password";
    public static final String ERROR_ILLEGAL_PASSWORD_DECRYPTED = "error.invalid.password.decrypted";

    // product
    public static final String ERROR_EMPTY_PRODUCT_NAME = "error.empty.product.name.field";
    public static final String ERROR_EMPTY_PRICE = "error.empty.price.field";
    public static final String ERROR_EMPTY_AMOUNT = "error.empty.amount.field";
    public static final String ERROR_EMPTY_UNIT = "error.empty.unit.field";
    public static final String ERROR_EMPTY_BARCODE = "error.empty.barcode.field";
    public static final String ERROR_WRONG_PRODUCT_NAME = "error.wrong.product.name.validation";
    public static final String ERROR_WRONG_PRICE = "error.wrong.price.validation";
    public static final String ERROR_WRONG_AMOUNT = "error.wrong.amount.validation";
    public static final String ERROR_WRONG_BARCODE = "error.wrong.barcode.validation";

    // receipt
    public static final String ERROR_WRONG_RECEIPT_CHANGE = "error.wrong.receipt.change.validation";

    // general
    public static final String ERROR_UNKNOWN_EXCEPTION = "error.unknown.exception";
}
