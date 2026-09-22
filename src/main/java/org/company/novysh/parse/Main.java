package org.company.novysh.parse;

public class Main {
    public static void main(String[] args) {
        String log = "[INFO] 2026-09-22 10:30:15: User logged in successfully. SessionID: '7b9a12c4-8ef3'. Status: ACTIVE";
        String extractSessionID = LogParse.extractSessionID(log);

        System.out.printf("Extracted session is %s ", extractSessionID);
    }
}
