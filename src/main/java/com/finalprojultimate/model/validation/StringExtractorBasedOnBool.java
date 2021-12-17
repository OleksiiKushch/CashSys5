package com.finalprojultimate.model.validation;

@FunctionalInterface
public interface StringExtractorBasedOnBool {
    String extractIfPositive(boolean logicalExpression, String message);
}
