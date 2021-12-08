package com.finalprojultimate.controller.validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Validator<T> {
    boolean isValid(T object);
    List<String> getErrorMessages();
    List<String> getErrorValidationMessages();
    default List<String> getErrorMessages(StringExtractorBasedOnBool extractor, List<Boolean> checks, List<String> messages) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < checks.size(); i++) {
            result.add(extractor.extractIfPositive(!checks.get(i), messages.get(i)));
        }
        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
    default boolean isUnsignedBigDecimal(BigDecimal value) {
        int temp = value.compareTo(new BigDecimal("0"));
        return temp >= 0;
    }
}
