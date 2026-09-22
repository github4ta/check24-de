package anna.novysh.parse;

import java.util.Optional;

public class TextParse {
    public static int extractTotalSum(String text){
        return Optional.ofNullable(text)
                .map(str -> str.replaceAll("\\D+", ""))
                .filter(str -> !str.isEmpty())
                .map(Integer::parseInt)
                .orElse(0);
    }
}
