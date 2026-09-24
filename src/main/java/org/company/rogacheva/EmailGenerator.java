package org.company.rogacheva;

public class EmailGenerator {

    public static String generateUniqueEmail(String baseEmail) {
        if (baseEmail == null || baseEmail.isEmpty() || !baseEmail.contains("@")) {
            return null;
        }
        int index = baseEmail.indexOf("@");
        return baseEmail.substring(0, index) + "+" + System.currentTimeMillis() + baseEmail.substring(index, baseEmail.length());
    }

    public static void main(String[] args) {
        System.out.println(generateUniqueEmail("testrunner@gmail.com"));
    }
}
