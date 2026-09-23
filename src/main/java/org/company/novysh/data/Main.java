package org.company.novysh.data;

public class Main {
    public static void main(String[] args) {
        String baseEmail = "testrunner@gmail.com";

        String emailWithTimestamp = EmailUtils.generateEmail(baseEmail, Rule.TIMESTAMP);
        System.out.println(emailWithTimestamp);

        String emailWithRandom = EmailUtils.generateEmailByString(baseEmail, Rule.RANDOM_NUMBER);
        System.out.println(emailWithRandom);
    }
}
