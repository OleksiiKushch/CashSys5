package com.finalprojultimate.controller;

import java.util.regex.Pattern;

public abstract class CommandRegexAndPatterns {

    private CommandRegexAndPatterns() {
        // hide
    }

    public static final String SHORT_NAME_REGEX = "[\\s\\S]{0,45}";
    public static final String PASSWORD_REGEX = "\\S{4,16}";
    // product name
    public static final String LONG_NAME_REGEX = "[\\s\\S]{0,512}";
    // price, tax, vat
    public static final String DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_REGEX = "\\d+\\.*\\d{0,2}";
    // amount
    public static final String DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_REGEX = "\\d+\\.*\\d{0,3}";
    public static final String BARCODE_REGEX = "[\\S]{0,128}"; // TODO modifier this

    public static final Pattern SHORT_NAME_PATTERN = Pattern.compile(SHORT_NAME_REGEX);
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    public static final Pattern LONG_NAME_PATTERN = Pattern.compile(LONG_NAME_REGEX);
    public static final Pattern DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN =
            Pattern.compile(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_REGEX);
    public static final Pattern DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN =
            Pattern.compile(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_REGEX);
    public static final Pattern BARCODE_PATTERN = Pattern.compile(BARCODE_REGEX);




}
