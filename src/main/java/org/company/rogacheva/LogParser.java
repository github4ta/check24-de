package org.company.rogacheva;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private static final Pattern SESSION_ID_PATTERN = Pattern.compile("SessionID:\\s*'([^']+)'");

    public static String findSessionID(String log) {
        if (log == null || log.isEmpty()) {
            return null;
        }

        Matcher matcher = SESSION_ID_PATTERN.matcher(log);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(findSessionID("[INFO] 2026-09-22 10:30:15: User logged in successfully. SessionID: '7b9a12c4-8ef3'. Status: ACTIVE"));
    }
}

