package anna.novysh.data;

import java.util.Optional;

public class EmailUtils {
    public static String generateEmailByRule(String email, Rule rule){
       return Optional.ofNullable(email).
                filter(str -> str.contains("@"))
                .map(str -> str.replaceFirst("@", "+" + rule.generate() + "@"))
                .orElse("");
    }
}
