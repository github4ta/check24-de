package org.company.novysh.removespaces;

public class Main {
    public static void main(String[] args) {
        String text = "Hello      world!\n  How   are you?\b";
        String cleanText = Utils.removeAllSpaces(text);

        System.out.printf("Initial text is %s%n", text);
        System.out.printf("Clean (without extra spaces) text is %s ", cleanText);
    }
}
