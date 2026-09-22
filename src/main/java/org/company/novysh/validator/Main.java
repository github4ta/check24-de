package org.company.novysh.validator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Info: " + FormatType.USER_TIMESTAMP_QA);
        String userName = "user_1774254215_qa";

        boolean result = FormatType.USER_TIMESTAMP_QA.isValid(userName);
        System.out.printf("Username format verification result: %b, username is %s" , result, userName);
    }
}
