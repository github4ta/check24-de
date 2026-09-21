package com.mytheresa;

public class Main2 {

    public static void main(String[] args) {
        int[] counts = new int[]{1, 3, 34, 44, 54, 443, 221, -1324, -5, 0, -1, 2, 342, 5, 89};
        int[] counts2 = new int[]{1, 2, 3, 4, 5};
        String[] strings = new String[]{
                "hello",
                "world",
                "it",
                "is",
                "me",
                "Mario",
                "YOY loooy",
                "YOY looox",
                "YOY oooox"
        };
        String s = "Podoshva moya otcleyalasya";

        Main2 obj = new Main2();

        System.out.printf("Max:    %d%n", obj.max(counts));
        System.out.printf("Min:    %d%n", obj.min(counts));
        System.out.printf("Average:  %.2f%n", obj.average(counts2));
        System.out.printf("Max index: %d%n", obj.maxIndex(counts));
        System.out.printf("Min index: %d%n", obj.minIndex(counts));

        System.out.printf("First longest:  %s%n", obj.firstMax(strings));
        System.out.printf("Last longest:   %s%n", obj.lastMax(strings));

        System.out.printf("Vowels in \"%s\": %d%n", s, obj.vowelCounter(s));

        System.out.printf("isPositive(-800): %s%n", isPositive(-800));
        System.out.printf("isEven(1):         %s%n", isEven(1));
    }

    public int max(int[] m) {
        int t = m[0];
        for (int i = 0; i < m.length; i++) {
            if (t < m[i]) {
                t = m[i];
            }
        }
        return t;
    }

    public int min(int[] m) {
        int t = m[0];
        for (int i = 0; i < m.length; i++) {
            if (t > m[i]) {
                t = m[i];
            }
        }
        return t;
    }

    public double average(int[] m) {
        double t = 0;
        for (int i = 0; i < m.length; i++) {
            t += m[i];
        }
        t = t / m.length;
        return t;
    }

    public int maxIndex(int[] m) {
        int maxIndex = 0;
        for (int i = 0; i < m.length; i++) {
            if (m[i] == max(m)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public int minIndex(int[] m) {
        int minIndex = 0;
        for (int i = 0; i < m.length; i++) {
            if (m[i] == min(m)) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public String firstMax(String[] strings) {
        String maxString = "";
        for (String str : strings) {
            if (str.length() == maxStringVolume(strings)) {
                maxString = str;
                break;
            }
        }
        return maxString;
    }

    public String lastMax(String[] strings) {
        String maxString = "";
        for (int i = strings.length - 1; i >= 0; i--) {
            if (strings[i].length() == maxStringVolume(strings)) {
                maxString = strings[i];
                break;
            }
        }
        return maxString;
    }

    public int maxStringVolume(String[] strings) {
        int maxLength = 0;
        for (String str : strings) {
            if (str.length() > maxLength) {
                maxLength = str.length();
            }
        }
        return maxLength;
    }

    public int vowelCounter(String string) {
        int count = 0;
        char[] characters = string.toLowerCase().toCharArray();
        for (char c : characters) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y' ) {
                count++;
            }
        }
        return count;
    }

    public static String isPositive(long number) {
        if (number < 0) {
            return "Negative";
        } else if (number == 0) {
            return "Zero";
        } else {
            return "Positive";
        }
    }

    public static String isPositive(int number) {
        if (number < 0) {
            return "Negative";
        } else if (number == 0) {
            return "Zero";
        } else {
            return "Positive";
        }
    }

    public static String isEven(int number) {
        if (number % 2 == 0) {
            return "Even";
        } else return "Odd";
    }

    public static String isEven(long number) {
        if (number % 2 == 0) {
            return "Even";
        } else return "Odd";
    }
}
