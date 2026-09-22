package org.company.novysh.removespaces;

import java.util.Optional;

public class Utils {
    public static String removeAllSpaces(String str) {
        return Optional.ofNullable(str)
                .map(String::strip)
                .map(s -> s.replaceAll("\\s+", " "))
                .orElse("");
    }
}
