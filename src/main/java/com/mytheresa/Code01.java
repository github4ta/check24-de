package com.mytheresa;

public class Code01 {
    public static long arraySum(int[] numbers){
        long num = 0;
        for (long i : numbers){
            num+=i;
        }
        return num;
    }
}
