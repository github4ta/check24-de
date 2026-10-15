package annanovysh;

import ch.qos.logback.core.encoder.JsonEscapeUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Code2 {
    public static void main(String[] args) {
        String str = "Noon";
        String result = isPalindrom(str) ? "This is palindrom" :
                "This is not palindrom";

        System.out.println(result);
    }

    public static boolean isPalindrom(String str) {
        if (str == null) return false;
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equalsIgnoreCase(reversed);
    }
}
