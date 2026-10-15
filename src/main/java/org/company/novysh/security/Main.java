package org.company.novysh.security;

public class Main {
    public static void main(String[] args) {
        String cardNumber = "4111222233334441";

        try {
            String maskedCardNumber = SecurityUtils.getMaskedCardNumber(cardNumber);
            System.out.printf("Masked number is %s%n", maskedCardNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Error masked card: " + e.getMessage());
        }
    }
}
