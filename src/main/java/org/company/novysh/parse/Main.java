package org.company.novysh.parse;

public class Main {
    public static void main(String[] args) {
        String text = "Итого: 12 450 руб. (со скидкой)";

        int totalSum = TextParse.extractTotalSum(text);
        System.out.println(totalSum);
    }
}
