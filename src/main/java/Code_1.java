public class Code_1 {
    public static int findSum(int[] numbers) {
        int sum = 0;

        for(int number:numbers){
            sum += number;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int res = findSum(arr);

        System.out.println("Сумма чисел в массиве:" + res);
    }
}
