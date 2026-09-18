package annanovysh;

import java.util.stream.IntStream;

public class Code01 {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 3};
        long result = sumArrayElements(numbers);
        System.out.println("Sum of array elements: " + result);

        int[] numbers2 = {};
        long result2 = sumArrayElements(numbers2);
        System.out.println("Sum of array elements: " + result2);

        int[] numbers3 = null;
        long result3 = sumArrayElements(numbers3);
        System.out.println("Sum of array elements: " + result3);
    }

    public static long sumArrayElements(int[] numbers) {
        if(numbers == null)
            return 0;
        return IntStream.of(numbers).
                asLongStream().
                sum();
    }
}
