package demoTasks;

import java.util.stream.IntStream;

public class Fibonacci {
    /*public static void main(String[] args) {
        int n0 = 1, n1 = 1, n2;
        System.out.print(n0 + " " + n1 + " "); // ряда
        for(int i = 0; i < 18; i++) {
            n2 = n1 + n0;
            System.out.print(n2 + " ");
            n0 = n1;
            n1 = n2;
        }
        System.out.println();
    }
 */

    public static void main(String[] args){
        int [] fib = {1, 1};
        System.out.print(fib[0] + " " + fib[1] + " ");
        IntStream.rangeClosed(1, 18).forEach(i -> {
            int n2 = fib[0] + fib[1];
            System.out.print(n2 + " ");
            fib[0] = fib[1];
            fib[1] = n2;
        });
        System.out.println();
    }
}
