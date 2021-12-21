package com.finalprojultimate.model.validation;

import java.util.regex.Pattern;

public abstract class RegexPattern {

    private RegexPattern() {
        // hide
    }

    // first/middle/last name
    public static final String SHORT_NAME_REGEX = "[\\s\\S]{0,45}";
    public static final String PASSWORD_REGEX = "[\\S]{4,16}";
    // name organization, taxation system
    public static final String MEDIUM_NAME_REGEX = "[\\s\\S]{0,128}";
    // product name
    public static final String LONG_NAME_REGEX = "[\\s\\S]{0,512}";
    // price, tax (vat), change
    public static final String DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_REGEX = "-?\\d+\\.?\\d{0,2}[0]*";
    // amount
    public static final String DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_REGEX = "-?\\d+\\.?\\d{0,3}[0]*";
    // organization tax id number
    public static final String BIG_INT_REGEX = "[\\d]{0,18}";
    // barcode
    public static final String BARCODE_REGEX = "[\\S]{4,128}"; // TODO modifier this

    public static final Pattern SHORT_NAME_PATTERN = Pattern.compile(SHORT_NAME_REGEX);
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    public static final Pattern MEDIUM_NAME_PATTERN = Pattern.compile(MEDIUM_NAME_REGEX);
    public static final Pattern LONG_NAME_PATTERN = Pattern.compile(LONG_NAME_REGEX);
    public static final Pattern DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN =
            Pattern.compile(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_REGEX);
    public static final Pattern DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN =
            Pattern.compile(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_REGEX);
    public static final Pattern BIG_INT_PATTERN = Pattern.compile(BIG_INT_REGEX);
    public static final Pattern BARCODE_PATTERN = Pattern.compile(BARCODE_REGEX);




}
