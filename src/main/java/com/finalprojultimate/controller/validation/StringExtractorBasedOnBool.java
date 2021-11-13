package com.finalprojultimate.controller.validation;

@FunctionalInterface
public interface StringExtractorBasedOnBool {
    String extractIfPositive(boolean logicalExpression, String message);
}
