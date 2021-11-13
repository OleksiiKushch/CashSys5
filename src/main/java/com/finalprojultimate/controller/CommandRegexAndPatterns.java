package com.finalprojultimate.controller;

import java.util.regex.Pattern;

public abstract class CommandRegexAndPatterns {

    private CommandRegexAndPatterns() {
        // hide
    }

    public static final String NAME_REGEX = "[\\s\\S]{0,45}";
    public static final String PASSWORD_REGEX = "\\S{4,16}";

    public static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);




}
