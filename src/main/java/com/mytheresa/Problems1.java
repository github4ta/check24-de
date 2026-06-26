package com.mytheresa;

import java.util.Random;

public class Problems1 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] nums = {0, 4, 45, 895, 48, 575, 6, 9, 90, 345, 56};
        String[] strs = {"cat", "elephant", "dog", "hippopotamus", "bird"};
        String str = "Programming";

        System.out.printf("Max number: %s\n", getMax(nums));
        System.out.printf("Min number: %s\n", getMin(nums));
        System.out.printf("Average: %s\n", getAverage(nums));
        System.out.printf("Max number index: %s\n", getMaxNumIndex(nums));
        System.out.printf("Min number index: %s\n", getMinNumIndex(nums));

        System.out.printf("Max char string: %s\n", getMaxLenghtString(strs));
        System.out.printf("Min char string: %s\n", getMinLenghtString(strs));

        System.out.printf("Vowel letters number: %s\n", getVowelCount(str));

        isPositive(random.nextInt());
        isEven(random.nextInt());
    }

    public static int getMax(int[] array) {
        int result = array[0];
        for (int value : array) {
            result = Math.max(result, value);
        }
        return result;
    }

    public static int getMin(int[] array) {
        int result = array[0];
        for (int value : array) {
            result = Math.min(result, value);
        }
        return result;
    }

    public static int getAverage(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum / array.length;
    }

    public static int getMaxNumIndex(int[] array) {
        int max = getMax(array);
        for (int i = 0; i < array.length; i++) {
            if (max == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int getMinNumIndex(int[] array) {
        int min = getMin(array);
        for (int i = 0; i < array.length; i++) {
            if (min == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static String getMaxLenghtString(String[] array) {
        int maxChar = array[0].length();
        String result = array[0];
        for (String value : array) {
            if (value.length() > maxChar) {
                result = value;
                maxChar = value.length();
            }
        }
        return result;
    }

    public static String getMinLenghtString(String[] array) {
        int minChar = array[0].length();
        String result = array[0];
        for (String value : array) {
            if (value.length() < minChar) {
                result = value;
                minChar = value.length();
            }
        }
        return result;
    }

    public static int getVowelCount(String value) {
        String[] vowelLetters = {"a", "e", "i", "o", "u", "y"};
        int result = 0;
        for (String vowelLetter : vowelLetters) {
            if (value.toLowerCase().contains(vowelLetter)) {
                result++;
            }
        }
        return result;
    }

    public static void isPositive(int number) {
        String result = switch (Integer.compare(number, 0)) {
            case 1 -> "positive";
            case -1 -> "negative";
            default -> "zero";
        };
        printMethod(number, result);
    }

    public static void isEven(int number) {
        String result = (number % 2 == 0) ? "even" : "odd";
        printMethod(number, result);
    }

    public static void printMethod(int number, String result) {
        System.out.printf("Number %s is %s\n", number, result);
    }
}