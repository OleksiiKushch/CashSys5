package com.finalprojultimate.controller.validation;

import java.math.BigDecimal;
import java.util.List;

public interface Validator<T> {
    boolean isValid(T object);
    List<String> getErrorMessages();
    List<String> getErrorValidationMessages();
    default boolean isUnsignedBigDecimal(BigDecimal value) {
        int temp = value.compareTo(new BigDecimal("0"));
        return temp >= 0;
    }
}
