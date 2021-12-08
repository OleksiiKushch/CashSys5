package com.finalprojultimate.controller.validation;

import com.finalprojultimate.model.entity.product.Product;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.finalprojultimate.controller.CommandRegexAndPatterns.*;
import static com.finalprojultimate.util.MessageKey.*;

public class ProductValidator implements Validator<Product> {
    private static final Logger logger = Logger.getLogger(ProductValidator.class);

    private boolean isNotEmptyProductName;
    private boolean isValidProductName;
    private boolean isNotEmptyPrice;
    private boolean isValidPrice;
    private boolean isUnsignedProductPrice;
    private boolean isNotEmptyAmount;
    private boolean isValidAmount;
    private boolean isUnsignedProductAmount;
    private boolean isNotEmptyUnit;
    private boolean isNotEmptyBarcode;
    private boolean isValidBarcode;

    private final List<Boolean> checks;
    private final List<String> messages;
    private final List<Boolean> checksValidation;
    private final List<String> messagesValidation;

    private final StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;
    private final NullChecker<String> nullChecker = (input) -> input == null || input.isEmpty();

    public ProductValidator() {
        checks = new ArrayList<>();
        messages = new ArrayList<>();
        checksValidation = new ArrayList<>();
        messagesValidation = new ArrayList<>();
    }

    public boolean isValid(Product product) {
        if (product.getPrice() == null || product.getAmount() == null) {
            return false;
        }

        isNotEmptyProductName = !nullChecker.isEmpty(product.getName());
        isValidProductName = isValidProductName(product.getName());
        isNotEmptyPrice = !nullChecker.isEmpty(product.getPrice().toString());
        isValidPrice = isValidPrice(product.getPrice());
        isUnsignedProductPrice = isUnsignedBigDecimal(product.getPrice());
        isNotEmptyAmount = !nullChecker.isEmpty(product.getAmount().toString());
        isValidAmount = isValidAmount(product.getAmount());
        isUnsignedProductAmount = isUnsignedBigDecimal(product.getAmount());
        isNotEmptyUnit = product.getUnit() != null;
        isNotEmptyBarcode = !nullChecker.isEmpty(product.getBarcode());
        isValidBarcode = isValidBarcode(product.getBarcode());

        return isNotEmptyProductName && isValidProductName &&
                isNotEmptyPrice && isValidPrice && isUnsignedProductPrice &&
                isNotEmptyAmount && isValidAmount && isUnsignedProductAmount &&
                isNotEmptyBarcode && isValidBarcode &&
                isNotEmptyUnit;
    }

    private boolean isValidProductName(String name) {
        return LONG_NAME_PATTERN.matcher(name).matches();
    }

    private boolean isValidPrice(BigDecimal price) {
        return DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher(price.toString()).matches();
    }

    private boolean isValidAmount(BigDecimal amount) {
        return DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN.matcher(amount.toString()).matches();
    }

    private boolean isValidBarcode(String barcode) {
        return BARCODE_PATTERN.matcher(barcode).matches();
    }

    @Override
    public List<String> getErrorMessages() {
        setChecks();
        setMessages();

        return getErrorMessages(extractor, checks, messages);
    }

    private void setChecks() {
        checks.add(isNotEmptyProductName);
        checks.add(isNotEmptyPrice);
        checks.add(isNotEmptyAmount);
        checks.add(isNotEmptyBarcode);
        checks.add(isNotEmptyUnit);
    }
    private void setMessages() {
        messages.add(ERROR_EMPTY_PRODUCT_NAME);
        messages.add(ERROR_EMPTY_PRICE);
        messages.add(ERROR_EMPTY_AMOUNT);
        messages.add(ERROR_EMPTY_BARCODE);
        messages.add(ERROR_EMPTY_UNIT);
    }

    @Override
    public List<String> getErrorValidationMessages() {
        setChecksValidation();
        setMessagesValidation();

        return getErrorMessages(extractor, checksValidation, messagesValidation);
    }

    private void setChecksValidation() {
        checksValidation.add(isValidProductName);
        checksValidation.add(isValidPrice);
        checksValidation.add(isUnsignedProductPrice);
        checksValidation.add(isValidAmount);
        checksValidation.add(isUnsignedProductAmount);
        checksValidation.add(isValidBarcode);
    }
    private void setMessagesValidation() {
        messagesValidation.add(ERROR_WRONG_PRODUCT_NAME);
        messagesValidation.add(ERROR_WRONG_PRICE);
        messagesValidation.add(ERROR_SINGED_PRICE);
        messagesValidation.add(ERROR_WRONG_AMOUNT);
        messagesValidation.add(ERROR_SINGED_AMOUNT);
        messagesValidation.add(ERROR_WRONG_BARCODE);
    }
}
