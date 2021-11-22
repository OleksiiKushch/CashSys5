package com.finalprojultimate.controller.validation;

import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.finalprojultimate.controller.CommandRegexAndPatterns.*;
import static com.finalprojultimate.controller.CommandRegexAndPatterns.BARCODE_PATTERN;
import static com.finalprojultimate.util.MessageKey.*;

public class ReceiptPropertiesValidator implements Validator<ReceiptDetails> {
    private static final Logger logger = Logger.getLogger(ReceiptPropertiesValidator.class);

    private boolean isNotEmptyOrganizationTaxIdNumber;
    private boolean isValidOrganizationTaxIdNumber;
    private boolean isUnsignedOrganizationTaxIdNumber;
    private boolean isNotEmptyNameOrganization;
    private boolean isValidNameOrganization;
    private boolean isNotEmptyAddressTradePoint;
    private boolean isValidAddressTradePoint;
    private boolean isNotEmptyVat;
    private boolean isValidVat;
    private boolean isUnsignedVat;
    private boolean isNotEmptyTaxationSys;
    private boolean isValidTaxationSys;

    private final StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;
    private final NullChecker<String> nullChecker = (input) -> input == null || input.isEmpty();

    public boolean isValid(ReceiptDetails receiptProperties) {
        isNotEmptyOrganizationTaxIdNumber = !nullChecker
                .isEmpty(String.valueOf(receiptProperties.getOrganizationTaxIdNumber()));
        isValidOrganizationTaxIdNumber =
                isValidOrganizationTaxIdNumber(String.valueOf(receiptProperties.getOrganizationTaxIdNumber()));
        isUnsignedOrganizationTaxIdNumber = isUnsignedLong(receiptProperties.getOrganizationTaxIdNumber());
        isNotEmptyNameOrganization = !nullChecker.isEmpty(receiptProperties.getNameOrganization());
        isValidNameOrganization = isValidNameOrganization(receiptProperties.getNameOrganization());
        isNotEmptyAddressTradePoint = !nullChecker.isEmpty(receiptProperties.getAddressTradePoint());
        isValidAddressTradePoint = isValidAddressTradePoint(receiptProperties.getAddressTradePoint());
        isNotEmptyVat = !nullChecker.isEmpty(receiptProperties.getVat().toString());
        isValidVat = isValidVat(receiptProperties.getVat());
        isUnsignedVat = isUnsignedBigDecimal(receiptProperties.getVat());
        isNotEmptyTaxationSys = !nullChecker.isEmpty(receiptProperties.getTaxationSys());
        isValidTaxationSys = isValidTaxationSys(receiptProperties.getTaxationSys());

        return isNotEmptyOrganizationTaxIdNumber && isValidOrganizationTaxIdNumber && isUnsignedOrganizationTaxIdNumber &&
                isNotEmptyNameOrganization && isValidNameOrganization &&
                isNotEmptyAddressTradePoint && isValidAddressTradePoint &&
                isNotEmptyVat && isValidVat && isUnsignedVat &&
                isNotEmptyTaxationSys && isValidTaxationSys;
    }

    private boolean isValidOrganizationTaxIdNumber(String organizationTaxIdNumber) {
        return BARCODE_PATTERN.matcher(organizationTaxIdNumber).matches();
    }

    private boolean isValidNameOrganization(String nameOrganization) {
        return LONG_NAME_PATTERN.matcher(nameOrganization).matches();
    }

    private boolean isValidAddressTradePoint(String addressTradePoint) {
        return LONG_NAME_PATTERN.matcher(addressTradePoint).matches();
    }

    private boolean isValidVat(BigDecimal vat) {
        return DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher(vat.toString()).matches();
    }

    private boolean isValidTaxationSys(String taxationSys) {
        return SHORT_NAME_PATTERN.matcher(taxationSys).matches();
    }

    private boolean isUnsignedLong(long value) {
        return value >= 0;
    }

    @Override
    public List<String> getErrorMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isNotEmptyOrganizationTaxIdNumber, ERROR_EMPTY_ORGANIZATION_TAX_ID_NUMBER));
        result.add(extractor.extractIfPositive(!isNotEmptyNameOrganization, ERROR_EMPTY_NAME_ORGANIZATION));
        result.add(extractor.extractIfPositive(!isNotEmptyAddressTradePoint, ERROR_EMPTY_ADDRESS_TRADE_POINT));
        result.add(extractor.extractIfPositive(!isNotEmptyVat, ERROR_EMPTY_VAT));
        result.add(extractor.extractIfPositive(!isNotEmptyTaxationSys, ERROR_EMPTY_TAXATION_SYS));

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<String> getErrorValidationMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isValidOrganizationTaxIdNumber, ERROR_WRONG_ORGANIZATION_TAX_ID_NUMBER));
        result.add(extractor.extractIfPositive(!isUnsignedOrganizationTaxIdNumber, ERROR_WRONG_ORGANIZATION_TAX_ID_NUMBER));
        result.add(extractor.extractIfPositive(!isValidNameOrganization, ERROR_WRONG_NAME_ORGANIZATION));
        result.add(extractor.extractIfPositive(!isValidAddressTradePoint, ERROR_WRONG_ADDRESS_TRADE_POINT));
        result.add(extractor.extractIfPositive(!isValidVat, ERROR_WRONG_VAT));
        result.add(extractor.extractIfPositive(!isUnsignedVat, ERROR_WRONG_VAT));
        result.add(extractor.extractIfPositive(!isValidTaxationSys, ERROR_WRONG_TAXATION_SYS));

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
