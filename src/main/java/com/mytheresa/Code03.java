package com.mytheresa;

public class Code03 {
    public static String generateCode(int symbols){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < symbols; i++) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(Code03.generateCode(9));
    }
}
