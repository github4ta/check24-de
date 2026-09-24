package org.company.rogacheva;

public class CardMasker {
    private static final String MASK_DATA = "(\\d{4})\\d{8}(\\d{4})";

    public static String maskCardData(String text) {
        if (text == null || text.isEmpty() || text.length() != 16) {
            return text;
        }

        return text.replaceAll(MASK_DATA, "$1-****-****-$2");
    }

    public static void main(String[] args) {
        System.out.println(maskCardData("4111222233334444"));
        System.out.println(maskCardData("41112233334400"));
    }
}
