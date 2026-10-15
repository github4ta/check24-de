package org.company.chelei;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    public static String extractSessionId(String logLine) {
        if (logLine == null || logLine.isEmpty()) {
            return null;
        }

        Pattern pattern = Pattern.compile("SessionID: '([^']+)'");
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}