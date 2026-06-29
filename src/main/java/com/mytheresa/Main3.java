package com.mytheresa;

public class Main3 {
    public static void main(String[] args) {
        int[] counts = new int[]{1, 3, 34, 44, 54, 443, 221, -1324, -5, 0, -1, 2, 342, 5, 89, 0};

        printNegativeNums(counts);
        printIfNumIsZero(counts);
        print(counts);
    }

    public static void printPositiveNums(int[] nums) {
        for (int num : nums) {
            if (num > 0) {
                System.out.println("+Positive number : " + num);
            }
        }
    }

    public static void printNegativeNums(int[] nums) {
        for (int num : nums) {
            if (num < 0) {
                System.out.println("- Negative number : " + num);
            }
        }
    }

    public static void printIfNumIsZero(int[] nums) {
        for (int num : nums) {
            if (num == 0) {
                System.out.println("o Zero number : " + num);
            }
        }
    }

    public static void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                System.out.println("Positive number : " + nums[i]);
            } else if (nums[i] < 0) {
                System.out.println("Negative number : " + nums[i]);
            } else System.out.println("zero number : " + nums[i]);
        }
    }
}
