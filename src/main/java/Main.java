import de.check24.api.util.FileReader;

public class Main {

    public static void main(String[] args) {
        String body = FileReader.readFile("default-body.txt", "api", "user", "auth");
        System.out.println(body);
    }
}
