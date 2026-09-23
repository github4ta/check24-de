package org.company.rogacheva;

public class TextCleaner {

    public static String cleanString(String text) {
        if (text == null) {
            return " ";
        }
        return text.strip().replaceAll("\\s+", " ");
    }

    public static void main(String[] args) {
        System.out.println(cleanString("\n  Товар   успешно   добавлен!  \t"));
    }
}
