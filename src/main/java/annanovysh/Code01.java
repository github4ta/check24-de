package annanovysh;

import java.util.stream.IntStream;

public class Code01 {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 3};
        int result = sumArrayElements(numbers);
        System.out.println("Sum of array elements: " + result);

        int[] numbers2 = {};
        int result2 = sumArrayElements(numbers2);
        System.out.println("Sum of array elements: " + result2);

        int[] numbers3 = null;
        int result3 = sumArrayElements(numbers2);
        System.out.println("Sum of array elements: " + result3);
    }

    public static int sumArrayElements(int[] numbers) {
        return numbers == null ? 0 : IntStream.of(numbers).sum();
    }
}
