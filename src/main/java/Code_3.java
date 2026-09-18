import java.util.Random;

public class Code_3 {
    public static String generateCode(int symbols){
        if(symbols <=0) {
            return " ";
        }

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

            for (int i = 0; i < symbols; i++) {
                int rndDigit = random.nextInt(10);
                sb.append(rndDigit);
            }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Строка:" + generateCode(10));
    }
}
