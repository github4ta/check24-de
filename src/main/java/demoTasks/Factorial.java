package demoTasks;

import java.util.stream.IntStream;

public class Factorial {

    public static int factorial(int x) {
        if (x < 0) throw new IllegalArgumentException("x должен быть >= 0");
        int fact = 1;
        for(int i = 2; i <= x; i++) // Цикл
            fact *= i;
        return fact;
    }

    public static int factorialLambda(int x){
        int fact = 1;
        if (x < 0) throw new IllegalArgumentException("x должен быть >= 0");

        return IntStream.rangeClosed(2, x).reduce(1, Math :: multiplyExact );

    }
}
