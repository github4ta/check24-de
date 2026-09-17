package testTasks;

/**
 * Этот класс не определяет метод main() и поэтому не является
 * самостоятельной программой. Он, тем не менее, определяет полезный
 * метод, который можно использовать в других программах.
 **/
public class Factorial {
    /** Вычисляем и возвращаем x!, факториал x */
    public static int factorial(int x) {
        if (x < 0) throw new IllegalArgumentException("x должен быть >= 0");
        int fact = 1;
        for(int i = 2; i <= x; i++) // Цикл
            fact *= i; // Краткая запись для fact = fact * i;
        return fact;
    }
}