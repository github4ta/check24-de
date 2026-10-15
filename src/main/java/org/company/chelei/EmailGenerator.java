package org.company.chelei;

class EmailGenerator{
    public static String generateUniqueEmail(String baseEmail) {
        int atIndex = baseEmail.lastIndexOf("@");

        String username = baseEmail.substring(0, atIndex);
        String domain = baseEmail.substring(atIndex);

        long timestamp = System.currentTimeMillis() / 1000L;

        return username + "+" + timestamp + domain;
    }
}