package com.mytheresa;

public class Code02 {
    public static Boolean isPalindrome(String str){
        return str.contentEquals(new StringBuilder(str).reverse());
    }

    public static void main(String[] args) {
        System.out.println(Code02.isPalindrome("foof"));

    }
}
