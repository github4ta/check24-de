package demoTasks;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Echo {
     public static void main(String[] args) {
         int i = 0;
         while(i < args.length) {
             System.out.print(args[i] + " ");
             i++;
         }

       //  Arrays.stream(args).forEach(i-> System.out.println( i + " "));
         // List.of(args).forEach(arg -> System.out.print(arg + " "));
     }
}
