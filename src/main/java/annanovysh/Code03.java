package annanovysh;

import java.util.ArrayList;
import java.util.Random;

public class Code03 {
    public static void main(String[] args) {
        try {
            System.out.println("Generate code: " + generateCode(-1));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

    }

    public static String generateCode(int symbols) {
        if (symbols < 0 || symbols > 10) {
            throw new IllegalArgumentException("Symbols length must be between 0 and 10, got: " + symbols);
        }
        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < symbols; i++) {
            codeBuilder.append(random.nextInt(symbols));
        }

        return codeBuilder.toString();
    }
}
