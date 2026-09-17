package testTasks;

/**
 * Эта программа распечатывает все заданные в ее командной строке аргументы.
 **/
public class Echo {
    public static void main(String[] args) {
        int i = 0; // Инициализация переменной цикла
        while(i < args.length) { // Цикл до конца массива
            System.out.print(args[i] + " "); // Печать каждого из аргументов
            i++; // Увеличение переменной цикла
        }
        System.out.println(); // Переход на следующую строку
    }
}