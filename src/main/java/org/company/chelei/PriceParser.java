package org.company.chelei;

public class PriceParser {

    public static int extractPrice(String text) {
        String numericOnly = text.replaceAll("\\D+", "");
        return Integer.parseInt(numericOnly);
    }
}