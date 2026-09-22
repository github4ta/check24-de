package org.company.novysh.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParse {
    private static final Pattern SESSION_PATTERN = Pattern.compile("SessionID:\\s*'(.*)'");

    public static String extractSessionID(String log) {
        if (log == null) {
            return "";
        }
        Matcher matcher = SESSION_PATTERN.matcher(log);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }
}
