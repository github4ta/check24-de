package org.company.rogacheva;

public class PriceParser {

    public static int checkPrice(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String cleanText = text.replaceAll("\\D+", "");
        if (cleanText.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(cleanText);
    }

    public static void main(String[] args) {
        System.out.println(checkPrice("Итого: 12 450 руб. (со скидкой)"));
    }
}
