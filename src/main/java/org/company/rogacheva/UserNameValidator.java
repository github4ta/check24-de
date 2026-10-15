package org.company.rogacheva;

public class UserNameValidator {
    private static final String USERNAME_PATTERN = "^user_\\d{10}_qa$";

   static public boolean checkUserName(String username){
       if (username == null) {
           return false;
       }
       return username.matches(USERNAME_PATTERN);
    }

    public static void main(String[] args) {
       System.out.println("First username:" + checkUserName("user_1478523694_qa"));
       System.out.println("Second username:" + checkUserName("user_000005_qa"));
       System.out.println("Third username:" + checkUserName("drtf_1478523694_qa"));
    }
}
