package org.company.chelei;

public class UsernameValidator {
    public static boolean validate(String username) {
        if (username == null) {
            return false;
        }

        return username.matches("^user_\\d{10}_qa$");
    }
}
