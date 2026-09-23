package org.company.chelei;

public class TextCleaner {
    public static String clean(String text) {
        if (text == null) {
            return null;
        }

        return text.trim().replaceAll("\\s+", " ");
    }
}
