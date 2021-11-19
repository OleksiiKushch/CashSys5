package com.finalprojultimate.controller;

import java.util.regex.Pattern;

public abstract class CommandRegexAndPatterns {

    private CommandRegexAndPatterns() {
        // hide
    }

    public static final String NAME_REGEX = "[\\s\\S]{0,45}";
    public static final String PASSWORD_REGEX = "\\S{4,16}";
    public static final String PRODUCT_NAME_REGEX = "[\\s\\S]{0,256}";
    public static final String PRICE_REGEX = "\\d+\\.*\\d{0,2}";
    public static final String AMOUNT_REGEX = "\\d+\\.*\\d{0,3}";
    public static final String BARCODE_REGEX = "[\\S]{0,128}"; // TODO modifier this


    public static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    public static final Pattern PRODUCT_NAME_PATTERN = Pattern.compile(PRODUCT_NAME_REGEX);
    public static final Pattern PRICE_PATTERN = Pattern.compile(PRICE_REGEX);
    public static final Pattern AMOUNT_PATTERN = Pattern.compile(AMOUNT_REGEX);
    public static final Pattern BARCODE_PATTERN = Pattern.compile(BARCODE_REGEX);




}
