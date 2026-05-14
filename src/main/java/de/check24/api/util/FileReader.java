package de.check24.api.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {

/*    public static String readFile(String fileName, String... subdirectories) {
        try {
            Path path = Paths.get("src", "test", "resources", fileName);
            return Files.readString(path);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + fileName + ": " + e.getMessage());
            return "";
        }
    }*/

    public static String readFile(String fileName, String... subdirectories) {
        try {
            // Начинаем с базового пути к ресурсам тестов
            Path path = Paths.get("src", "test", "resources");

            // Последовательно добавляем все переданные подпапки
            for (String sub : subdirectories) {
                path = path.resolve(sub);
            }

            // В конце добавляем имя самого файла
            path = path.resolve(fileName);

            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + fileName + ": " + e.getMessage());
            return "";
        }
    }
}
