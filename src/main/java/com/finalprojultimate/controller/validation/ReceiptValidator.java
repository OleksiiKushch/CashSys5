package com.finalprojultimate.controller.validation;

import com.finalprojultimate.model.entity.receipt.Receipt;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.finalprojultimate.controller.CommandRegexAndPatterns.*;
import static com.finalprojultimate.util.MessageKey.*;

public class ReceiptValidator implements Validator<Receipt> {
    private static final Logger logger = Logger.getLogger(ReceiptValidator.class);

    private final StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;

    private boolean isValidReceiptChange;
    private boolean isUnsignedReceiptChange;

    private final List<Boolean> checksValidation;
    private final List<String> messagesValidation;

    public ReceiptValidator() {
        checksValidation = new ArrayList<>();
        messagesValidation = new ArrayList<>();
    }

    @Override
    public boolean isValid(Receipt receipt) {
        isValidReceiptChange = isValidReceiptChange(receipt.getChange());
        isUnsignedReceiptChange = isUnsignedBigDecimal(receipt.getChange());
        return isValidReceiptChange && isUnsignedReceiptChange;
    }

    private boolean isValidReceiptChange(BigDecimal change) {
        return DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher(change.toString()).matches();
    }

    @Override
    public List<String> getErrorMessages() {
        return null;
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
