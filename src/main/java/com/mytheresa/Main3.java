package com.mytheresa;

public class Main3 {
    public static void main(String[] args) {
        int[] nums = {0, 4, 45, 0, 895, 48, 575, 0, -4849, 6, 9, 90, 345, 56, 0, -438, -3874};
        printNums(nums);
    }

    public static void printEven(int[] nums) {
        for (int num : nums) {
            if (num % 2 == 0 && num != 0) {
                System.out.println("Even number: " + num);
            }
        }
    }

    public static void printOdd(int[] nums) {
        for (int num : nums) {
            if (num % 2 != 0) {
                System.out.println("Odd number: " + num);
            }
        }
    }

    public static void printZero(int[] nums) {
        for (int num : nums) {
            if (num == 0) {
                System.out.println("Zero number: " + num);
            }
        }
    }

    public static void printNums(int[] nums){
        for (int num: nums){
            if (num % 2 == 0 && num !=0){
                System.out.println("Even number: " + num);
            }
            else if (num % 2 != 0){
                System.out.println("Odd number: " + num);
            }
            else {
                System.out.println("Zero number: " + num);
            }
        }
    }
}
