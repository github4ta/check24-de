package com.davidflanagan.examples.basics;
/**
 * Эта программа играет в FizzBuzz. Она считает до 100, заменяя каждое число,
 * кратное 5, словом «fizz», каждое число, кратное 7, – словом «buzz»
 * и каждое число, кратное 35, – словом «fizzbuzz». Для определения того,
 * делится ли одно число на другое, в ней используется
 * оператор остатка целочисленного деления (%).
 **/
public class FizzBuzz { // В Java все является классом
    public static void main(String[] args) { // Каждая программа содержит main()
        for(int i = 1; i <= 100; i++) { // Считаем от 1 до 100
            if (((i % 5) == 0) && ((i % 7) == 0)) // Делится ли число и на 5, и на 7?
                System.out.print("fizzbuzz");
            else if ((i % 5) == 0) // Делится ли число на 5?
                System.out.print("fizz");
            else if ((i % 7) == 0) // Делится ли число на 7?
                System.out.print("buzz");
            else System.out.print(i); // Число не делится ни на 5, ни на 7
            System.out.print(" ");
        }
        System.out.println();
    }
}