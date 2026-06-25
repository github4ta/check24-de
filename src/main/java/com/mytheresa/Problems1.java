package com.mytheresa;

import java.util.Random;

public class Problems1 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] nums = {0, 4, 45, 895, 48, 575, 6, 9, 90, 345, 56};
        String[] cities = {"Minsk", "Gomel", "Vitebsk", "Mogilev", "Grodno", "Brest"};
        String city = "Minsk";

        System.out.printf("Max number: %s \n", getMax(nums));
        System.out.printf("Min number: %s \n", getMin(nums));
        System.out.printf("Average: %s \n", getAverage(nums));
        System.out.printf("Max number index: %s \n", getMaxIndex(nums));
        System.out.printf("Min number index: %s \n", getMinIndex(nums));

        System.out.printf("Max char string: %s \n", getMaxLenghtString(cities));
        System.out.printf("Min char string: %s \n", getMinLenghtString(cities));

        System.out.printf("Vowel letters number: %s \n", getVowelLettersNumber(city));

        checkPositiveOrNegativeNumber(random.nextInt());
        checkEvenOrOddNumber(random.nextInt());
    }

    public static int getMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            max = Math.max(max, value);
        }
        return max;
    }

    public static int getMin(int[] array) {
        int min = array[0];
        for (int value : array) {
            min = Math.min(min, value);
        }
        return min;
    }

    public static int getAverage(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum / array.length;
    }

    public static int getMaxIndex(int[] array) {
        int max = getMax(array);
        for (int i = 0; i < array.length; i++) {
            if (max == array[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int getMinIndex(int[] array) {
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
        String value = array[0];
        for (String str : array) {
            if (str.length() > maxChar) {
                value = str;
                maxChar = str.length();
            }
        }
        return value;
    }

    public static String getMinLenghtString(String[] array) {
        int minChar = array[0].length();
        String value = array[0];
        for (String str : array) {
            if (str.length() < minChar) {
                value = str;
                minChar = str.length();
            }
        }
        return value;
    }

    public static int getVowelLettersNumber(String value) {
        String[] vowelLetters = {"a", "e", "i", "o", "u", "y"};
        int result = 0;
        for (String vowelLetter : vowelLetters) {
            if (value.toLowerCase().contains(vowelLetter)) {
                result++;
            }
        }
        return result;
    }

    public static void checkPositiveOrNegativeNumber(int number) {
        String result = switch (Integer.compare(number, 0)){
            case 1 -> "positive";
            case -1 -> "negative";
            default -> "zero";
        };
        printMethod(number, result);
    }

    public static void checkEvenOrOddNumber(int number){
        String result = (number % 2 == 0) ? "even" : "odd";
        printMethod(number, result);
    }

    public static void printMethod (int number, String result){
        System.out.printf("Number %s is %s\n", number, result);
    }
}