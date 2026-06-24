package com.mytheresa;

import java.util.Arrays;

public class Main1 {
    public static void main(String[] args) {
        String[] names = {"John", "Ivan", "Nike", "Paul"};
    }

    private static void print(final String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.printf("%s. %s%n", i, args[i]);
            String str = String.format("%s", i);
        }
    }

    private static void printWithForEach(final String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    private static void printWithStreamAPI(final String[] args) {
        Arrays.stream(args).forEach(System.out::println);
    }
}
