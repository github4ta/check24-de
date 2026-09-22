package anna.novysh.security;

import java.util.Optional;

public class SecurityUtils {
    private static final String CARD_MASK_REGEX = "(\\d{4})\\d{4}\\d{4}(\\d{4})";

    public static String getMaskedCard(String cardNumber) {
        return Optional.ofNullable(cardNumber)
                .filter(str -> str.matches("\\d{16}"))
                .map(str -> str.replaceAll(CARD_MASK_REGEX, "$1-****-****-$2"))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid card number format: '" + cardNumber + "'. Expected exactly 16 digits."
                ));

    }
}
