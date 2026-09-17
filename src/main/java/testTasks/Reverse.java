package testTasks;

/**
 * Эта программа выводит «от конца к началу» аргументы, заданные
 * в командной строке.
 **/
public class Reverse {
    public static void main(String[] args) {
        // Цикл проходит массив аргументов от конца к началу
        for(int i = args.length - 1; i >= 0; i--) {
            // Цикл проходит от конца к началу символы в каждом аргументе
            for(int j=args[i].length() - 1; j>=0; j--) {
                // Печатается символ j аргумента i.

                System.out.print(args[i].charAt(j));
            }
            System.out.print(" "); // После каждого аргумента выводится пробел
        }
        System.out.println(); // И, закончив, переходим на следующую строку
    }
}