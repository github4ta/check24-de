package org.company.novysh.security;

import java.util.Optional;

public class SecurityUtils {
    private static final String CARD_GROUPS_REGEX = "(\\d{4})\\d{4}\\d{4}(\\d{4})";
    private static final String SIXTEEN_DIGITS_REGEX = "\\d{16}";

    public static String getMaskedCardNumber(String cardNumber) {
        return Optional.ofNullable(cardNumber)
                .filter(str -> str.matches(SIXTEEN_DIGITS_REGEX))
                .map(str -> str.replaceAll(CARD_GROUPS_REGEX, "$1-****-****-$2"))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid card number format: '" + cardNumber + "'. Expected exactly 16 digits."
                ));
    }
}
