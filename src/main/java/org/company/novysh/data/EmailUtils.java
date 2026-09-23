package org.company.novysh.data;

import java.util.Optional;

public class EmailUtils {
    public static String generateEmail(String email, Rule rule) {
        return Optional.ofNullable(email).
                filter(str -> str.contains("@"))
                .map(str -> str.replaceFirst("@", "+" + rule.generate() + "@"))
                .orElse("");
    }

    public static String generateEmailByString(String email, Rule rule) {
        if (email == null || rule == null) {
            return "";
        }

        int atIndex = email.indexOf("@");
        if (atIndex == -1) {
            return "";
        }

        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex);

        return username + "+" + rule.generate() + domain;
    }
}
