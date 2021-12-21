package com.finalprojultimate.model.validation.impl;

import com.finalprojultimate.model.validation.NullChecker;
import com.finalprojultimate.model.validation.StringExtractorBasedOnBool;
import com.finalprojultimate.model.validation.Validator;
import com.finalprojultimate.model.entity.receipt.Receipt;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.finalprojultimate.model.validation.RegexPattern.*;
import static com.finalprojultimate.util.MessageKey.*;

public class ReceiptValidator implements Validator<Receipt> {
    private static final Logger logger = Logger.getLogger(ReceiptValidator.class);

    private final StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;
    private final NullChecker<String> nullChecker = (input) -> input == null || input.isEmpty();

    private boolean isNotEmptyReceiptChange;
    private boolean isValidReceiptChange;
    private boolean isUnsignedReceiptChange;
    private boolean isNotEmptyPayment;

    private final List<Boolean> checks;
    private final List<String> messages;
    private final List<Boolean> checksValidation;
    private final List<String> messagesValidation;

    public ReceiptValidator() {
        checks = new ArrayList<>();
        messages = new ArrayList<>();
        checksValidation = new ArrayList<>();
        messagesValidation = new ArrayList<>();
    }

    @Override
    public boolean isValid(Receipt receipt) {
        if (receipt.getChange() == null) {
            isNotEmptyReceiptChange = false;
            isValidReceiptChange = true;
            isUnsignedReceiptChange = true;
        } else {
            isNotEmptyReceiptChange = !nullChecker.isEmpty(receipt.getChange().toString());
            isValidReceiptChange = isValidReceiptChange(receipt.getChange());
            isUnsignedReceiptChange = isUnsignedBigDecimal(receipt.getChange());
        }
        isNotEmptyPayment = receipt.getPayment() != null;
        return isNotEmptyReceiptChange && isValidReceiptChange && isUnsignedReceiptChange && isNotEmptyPayment;
    }

    private boolean isValidReceiptChange(BigDecimal change) {
        return DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher(change.toString()).matches();
    }

    @Override
    public List<String> getErrorMessages() {
        setChecks();
        setMessages();

        return getErrorMessages(extractor, checks, messages);
    }

    private void setChecks() {
        checks.add(isNotEmptyReceiptChange);
        checks.add(isNotEmptyPayment);
    }

    private void setMessages() {
        messages.add(ERROR_EMPTY_RECEIPT_CHANGE);
        messages.add(ERROR_EMPTY_PAYMENT);
    }

    @Override
    public List<String> getErrorValidationMessages() {
        setChecksValidation();
        setMessagesValidation();

        return getErrorMessages(extractor, checksValidation, messagesValidation);
    }

    private void setChecksValidation() {
        checksValidation.add(isValidReceiptChange);
        checksValidation.add(isUnsignedReceiptChange);
    }
    private void setMessagesValidation() {
        messagesValidation.add(ERROR_WRONG_RECEIPT_CHANGE);
        messagesValidation.add(ERROR_SINGED_RECEIPT_CHANGE);
    }
}
