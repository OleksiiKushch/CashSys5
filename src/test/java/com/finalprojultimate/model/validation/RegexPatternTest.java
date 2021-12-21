package com.finalprojultimate.model.validation;

import org.junit.Test;

import static com.finalprojultimate.model.validation.RegexPattern.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexPatternTest {

    @Test
    public void testRegexPattern() {
        assertTrue(SHORT_NAME_PATTERN.matcher("Bob").matches());
        assertFalse(SHORT_NAME_PATTERN.matcher(
                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob")
                .matches());

        assertTrue(PASSWORD_PATTERN.matcher("1q2w").matches());
        assertFalse(PASSWORD_PATTERN.matcher("1q 2w").matches());
        assertFalse(PASSWORD_PATTERN.matcher("123").matches());
        assertFalse(PASSWORD_PATTERN.matcher("123123123123123123123123").matches());

        assertTrue(MEDIUM_NAME_PATTERN.matcher("Bob").matches());
        assertFalse(MEDIUM_NAME_PATTERN.matcher(
                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                        "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                        "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob")
                .matches());

        assertTrue(MEDIUM_NAME_PATTERN.matcher("Bob").matches());
        assertFalse(LONG_NAME_PATTERN.matcher(
                        "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob" +
                                "Booooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooob")
                .matches());

        assertTrue(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher("0").matches());
        assertTrue(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher("12").matches());
        assertTrue(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher("12.2").matches());
        assertTrue(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher("12.2000").matches());
        assertTrue(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher("-12.2200").matches());
        assertFalse(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher("12.2020").matches());
        assertFalse(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher("12..2").matches());
        assertFalse(DECIMAL_WITH_TWO_DIGITS_AFTER_POINT_PATTERN.matcher("--12.2").matches());

        assertTrue(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN.matcher("0").matches());
        assertTrue(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN.matcher("12").matches());
        assertTrue(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN.matcher("12.2").matches());
        assertTrue(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN.matcher("12.2000").matches());
        assertTrue(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN.matcher("-12.2220").matches());
        assertFalse(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN.matcher("12.2002").matches());
        assertFalse(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN.matcher("12..2").matches());
        assertFalse(DECIMAL_WITH_THREE_DIGITS_AFTER_POINT_PATTERN.matcher("--12.2").matches());

        assertTrue(BIG_INT_PATTERN.matcher("123").matches());
        assertTrue(BIG_INT_PATTERN.matcher("123123123123123123").matches());
        assertFalse(BIG_INT_PATTERN.matcher("12312312312312312312").matches());

        assertTrue(BARCODE_PATTERN.matcher("1231231231231").matches());
        assertTrue(BARCODE_PATTERN.matcher("1qw21qw2").matches());
        assertFalse(BARCODE_PATTERN.matcher("1qw2 1qw2").matches());
        assertFalse(BARCODE_PATTERN.matcher(
                "1231231231231123123123123112312312312311231231231231123123123123112312312312311231231231231" +
                        "1231231231231123123123123112312312312311231231231231123123123123112312312312311231231231231" +
                        "1231231231231123123123123112312312312311231231231231123123123123112312312312311231231231231")
                .matches());

    }
}
