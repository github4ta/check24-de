package org.company.chelei;

public class DataMasker{
    public static String maskCardNumber(String cardNumber) {
        String first4 = cardNumber.substring(0, 4);
        String last4 = cardNumber.substring(12, 16);

        return first4 + "-****-****-" + last4;
    }
}