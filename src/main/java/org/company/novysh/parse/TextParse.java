package org.company.novysh.parse;

import java.util.Optional;

public class TextParse {
    private static String NON_DIGITS_REGEX = "\\D+";

    public static int extractTotalSum(String text) {
        return Optional.ofNullable(text)
                .map(str -> str.replaceAll(NON_DIGITS_REGEX, ""))
                .filter(str -> !str.isEmpty())
                .map(Integer::parseInt)
                .orElse(0);
    }
}
