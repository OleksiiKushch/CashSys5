package com.finalprojultimate.util;

public abstract class MessageKey {

    private MessageKey() {
        // hide
    }

    // FINE MESSAGES
    public static final String OK_MESSAGE = "message.ok";

    // ERROR MESSAGES

    // general validation
    public static final String ERROR_INCORRECT_OR_EMPTY_NUMBER_FIELD = "error.incorrect.or.empty.number.field";

    // user validation
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
    public static final String ERROR_ACCOUNT_ALREADY_EXISTS = "error.account.already.exists";
    public static final String ERROR_ILLEGAL_PASSWORD_DECRYPTED = "error.invalid.password.decrypted";

    public static final String ERROR_IS_NOT_SAME_CONFIRMATION_PASSWORD = "error.is.not.same.confirmation.password";

    // product validation
    public static final String ERROR_EMPTY_PRODUCT_NAME = "error.empty.product.name.field";
    public static final String ERROR_EMPTY_PRICE = "error.empty.price.field";
    public static final String ERROR_EMPTY_AMOUNT = "error.empty.amount.field";
    public static final String ERROR_EMPTY_UNIT = "error.empty.unit.field";
    public static final String ERROR_EMPTY_BARCODE = "error.empty.barcode.field";
    public static final String ERROR_WRONG_PRODUCT_NAME = "error.wrong.product.name.validation";
    public static final String ERROR_WRONG_PRICE = "error.wrong.price.validation";
    public static final String ERROR_SINGED_PRICE = "error.singed.price.validation";
    public static final String ERROR_WRONG_AMOUNT = "error.wrong.amount.validation";
    public static final String ERROR_SINGED_AMOUNT = "error.singed.amount.validation";
    public static final String ERROR_WRONG_BARCODE = "error.wrong.barcode.validation";
    public static final String ERROR_PRODUCT_ALREADY_EXISTS = "error.product.already.exists";
    public static final String ERROR_BARCODE_ALREADY_TAKEN = "error.barcode.already.taken";
    public static final String ERROR_PRODUCT_CONTAINED_IN_RECEIPTS = "error.product.contained.in.receipts";

    // receipt validation
    public static final String ERROR_EMPTY_RECEIPT_CHANGE = "error.empty.receipt.change.validation";
    public static final String ERROR_EMPTY_PAYMENT = "error.empty.payment.validation";
    public static final String ERROR_WRONG_RECEIPT_CHANGE = "error.wrong.receipt.change.validation";
    public static final String ERROR_SINGED_RECEIPT_CHANGE = "error.singed.receipt.change.validation";
    public static final String ERROR_CART_IS_EMPTY = "error.cart.is.empty.validation";
    public static final String ERROR_EMPTY_ORGANIZATION_TAX_ID_NUMBER = "error.empty.organization.tax.id.number.validation";
    public static final String ERROR_EMPTY_NAME_ORGANIZATION = "error.empty.name.organization.validation";
    public static final String ERROR_EMPTY_ADDRESS_TRADE_POINT = "error.empty.address.trade.point.validation";
    public static final String ERROR_EMPTY_VAT = "error.empty.vat.validation";
    public static final String ERROR_EMPTY_TAXATION_SYS = "error.empty.taxation.sys.validation";
    public static final String ERROR_WRONG_ORGANIZATION_TAX_ID_NUMBER = "error.wrong.organization.tax.id.number.validation";
    public static final String ERROR_WRONG_SINGED_OR_ZERO_ORGANIZATION_TAX_ID_NUMBER = "error.wrong.singed.or.zero.organization.tax.id.number.validation";
    public static final String ERROR_WRONG_NAME_ORGANIZATION = "error.wrong.name.organization.validation";
    public static final String ERROR_WRONG_ADDRESS_TRADE_POINT = "error.wrong.address.trade.point.validation";
    public static final String ERROR_WRONG_VAT = "error.wrong.vat.validation";
    public static final String ERROR_SINGED_VAT = "error.singed.vat.validation";
    public static final String ERROR_WRONG_TAXATION_SYS = "error.wrong.taxation.sys.validation";

    // dao entity
    public static final String ERROR_GETTING_ROW_FROM_DATABASE = "error.getting.row.from.database";
    public static final String ERROR_GETTING_ALL_ROWS_FROM_DATABASE = "error.getting.all.rows.from.database";
    public static final String ERROR_INSERT_ROW_TO_DATABASE = "error.insert.row.to.database";
    public static final String ERROR_UPDATE_ROW_TO_DATABASE = "error.update.row.to.database";
    public static final String ERROR_DELETE_ROW_FROM_DATABASE = "error.delete.row.from.database";

    public static final String ERROR_GET_COUNT_ROWS_IN_DATABASE = "error.get.count.rows.in.database";

    // dao product
    public static final String ERROR_FIND_PRODUCTS_WITH_PAGINATION_FROM_DATABASE = "error.find.products.with.pagination.from.database";
    public static final String ERROR_FIND_PRODUCTS_BY_QUERY_FROM_DATABASE = "error.find.products.by.query.from.database";
    public static final String ERROR_FIND_BEST_PRODUCTS_BY_COUNT_RECEIPT_FOR_THE_LAST_MONTH_FROM_DATABASE
            = "error.find.best.products.by.count.receipt.for.the.last.month.from.database";

    // dao receipt
    public static final String ERROR_CREATE_RECEIPT_IN_DATABASE = "error.create.receipt.in.database";
    public static final String ERROR_CREATE_REJECT_RECEIPT_IN_DATABASE = "error.create.reject.receipt.in.database";
    public static final String ERROR_FIND_RECEIPTS_WITH_PAGINATION_FROM_DATABASE = "error.find.receipts.with.pagination.from.database";
    public static final String ERROR_FIND_RECEIPTS_CONTAIN_PRODUCT_FROM_DATABASE = "error.find.receipts.contain.product.from.database";
    public static final String ERROR_GET_GLOBAL_RECEIPT_PROPERTIES_FROM_DATABASE = "error.get.global.receipt.properties.from.database";
    public static final String ERROR_SET_GLOBAL_RECEIPT_PROPERTIES_TO_DATABASE = "error.set.global.receipt.properties.to.database";
    public static final String ERROR_RESET_GLOBAL_RECEIPT_PROPERTIES_IN_DATABASE = "error.reset.global.receipt.properties.in.database";
    public static final String ERROR_GET_SUM_RECEIPT_BY_ID_FROM_DATABASE = "error.get.sum.receipt.by.id.from.database";
    public static final String ERROR_GET_PRODUCTS_BY_RECEIPT_ID_FROM_DATABASE = "error.get.products.by.receipt.id.from.database";
    public static final String ERROR_GET_RECEIPT_DETAILS_BY_ID_FROM_DATABASE = "error.get.receipt.details.by.id.from.database";
    public static final String ERROR_PROCESSING_REJECT_RECEIPT_IN_DATABASE = "error.processing.reject.receipt.id.from.database";

    // dao user
    public static final String ERROR_GET_USER_BY_EMAIL_FROM_DATABASE = "error.get.user.by.email.from.database";
    public static final String ERROR_FIND_USERS_WITH_PAGINATION_FROM_DATABASE = "error.find.users.with.pagination.from.database";
    public static final String ERROR_FIND_BEST_CASHIERS_BY_COUNT_RECEIPT_FOR_THE_LAST_MONTH_FROM_DATABASE
            = "error.find.best.cashiers.by.count.receipt.for.the.last.month.from.database";

    // general
    public static final String ERROR_UNKNOWN_EXCEPTION = "error.unknown.exception";
}
