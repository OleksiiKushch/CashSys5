package com.finalprojultimate.model.validation.impl;

import com.finalprojultimate.model.validation.NullChecker;
import com.finalprojultimate.model.validation.StringExtractorBasedOnBool;
import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.finalprojultimate.model.validation.RegexPattern.*;
import static com.finalprojultimate.model.validation.RegexPattern.BARCODE_PATTERN;
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

    private final List<Boolean> checks;
    private final List<String> messages;
    private final List<Boolean> checksValidation;
    private final List<String> messagesValidation;

    private final StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;
    private final NullChecker<String> nullChecker = (input) -> input == null || input.isEmpty();

    public ReceiptPropertiesValidator() {
        checks = new ArrayList<>();
        messages = new ArrayList<>();
        checksValidation = new ArrayList<>();
        messagesValidation = new ArrayList<>();
    }

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
        return BIG_INT_PATTERN.matcher(organizationTaxIdNumber).matches();
    }

    private boolean isValidNameOrganization(String nameOrganization) {
        return MEDIUM_NAME_PATTERN.matcher(nameOrganization).matches();
    }

    private boolean isValidAddressTradePoint(String addressTradePoint) {
        return LONG_NAME_PATTERN.matcher(addressTradePoint).matches();
    }

    private boolean isValidVat(BigDecimal vat) {
        return DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher(vat.toString()).matches();
    }

    private boolean isValidTaxationSys(String taxationSys) {
        return MEDIUM_NAME_PATTERN.matcher(taxationSys).matches();
    }

    private boolean isUnsignedLong(long value) {
        return value >= 0;
    }

    @Override
    public List<String> getErrorMessages() {
        setChecks();
        setMessages();

        return getErrorMessages(extractor, checks, messages);
    }

    private void setChecks() {
        checks.add(isNotEmptyOrganizationTaxIdNumber);
        checks.add(isNotEmptyNameOrganization);
        checks.add(isNotEmptyAddressTradePoint);
        checks.add(isNotEmptyVat);
        checks.add(isNotEmptyTaxationSys);
    }
    private void setMessages() {
        messages.add(ERROR_EMPTY_ORGANIZATION_TAX_ID_NUMBER);
        messages.add(ERROR_EMPTY_NAME_ORGANIZATION);
        messages.add(ERROR_EMPTY_ADDRESS_TRADE_POINT);
        messages.add(ERROR_EMPTY_VAT);
        messages.add(ERROR_EMPTY_TAXATION_SYS);
    }

    @Override
    public List<String> getErrorValidationMessages() {
        setChecksValidation();
        setMessagesValidation();

        return getErrorMessages(extractor, checksValidation, messagesValidation);
    }

    private void setChecksValidation() {
        checksValidation.add(isValidOrganizationTaxIdNumber);
        checksValidation.add(isUnsignedOrganizationTaxIdNumber);
        checksValidation.add(isValidNameOrganization);
        checksValidation.add(isValidAddressTradePoint);
        checksValidation.add(isValidVat);
        checksValidation.add(isUnsignedVat);
        checksValidation.add(isValidTaxationSys);
    }
    private void setMessagesValidation() {
        messagesValidation.add(ERROR_WRONG_ORGANIZATION_TAX_ID_NUMBER);
        messagesValidation.add(ERROR_SINGED_ORGANIZATION_TAX_ID_NUMBER);
        messagesValidation.add(ERROR_WRONG_NAME_ORGANIZATION);
        messagesValidation.add(ERROR_WRONG_ADDRESS_TRADE_POINT);
        messagesValidation.add(ERROR_WRONG_VAT);
        messagesValidation.add(ERROR_SINGED_VAT);
        messagesValidation.add(ERROR_WRONG_TAXATION_SYS);
    }

}
