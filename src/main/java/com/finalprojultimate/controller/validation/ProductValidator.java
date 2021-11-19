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

    private final StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;
    private final NullChecker<String> nullChecker = (input) -> input == null || input.isEmpty();

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
        return PRODUCT_NAME_PATTERN.matcher(name).matches();
    }

    private boolean isValidPrice(BigDecimal price) {
        return PRICE_PATTERN.matcher(price.toString()).matches();
    }

    private boolean isValidAmount(BigDecimal amount) {
        return AMOUNT_PATTERN.matcher(amount.toString()).matches();
    }

    private boolean isValidBarcode(String barcode) {
        return BARCODE_PATTERN.matcher(barcode).matches();
    }

    @Override
    public List<String> getErrorMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isNotEmptyProductName, ERROR_EMPTY_PRODUCT_NAME));
        result.add(extractor.extractIfPositive(!isNotEmptyPrice, ERROR_EMPTY_PRICE));
        result.add(extractor.extractIfPositive(!isNotEmptyAmount, ERROR_EMPTY_AMOUNT));
        result.add(extractor.extractIfPositive(!isNotEmptyBarcode, ERROR_EMPTY_BARCODE));
        result.add(extractor.extractIfPositive(!isNotEmptyUnit, ERROR_EMPTY_UNIT));

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<String> getErrorValidationMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isValidProductName, ERROR_WRONG_PRODUCT_NAME));
        result.add(extractor.extractIfPositive(!isValidPrice, ERROR_WRONG_PRICE));
        result.add(extractor.extractIfPositive(!isUnsignedProductPrice, ERROR_WRONG_PRICE));
        result.add(extractor.extractIfPositive(!isValidAmount, ERROR_WRONG_AMOUNT));
        result.add(extractor.extractIfPositive(!isUnsignedProductAmount, ERROR_WRONG_AMOUNT));
        result.add(extractor.extractIfPositive(!isValidBarcode, ERROR_WRONG_BARCODE));

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
