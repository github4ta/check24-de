package demoTasks;

public class FizzBuzz {

    public static void main(String[] args){
        for(int i = 1; i <= 100;i++ ){
            if( (i % 5 == 0) && (i % 7 ==0)){
                System.out.println("fizzbuzz");
            } else if(i % 5 == 0 ) System.out.println("fizz");
                   else if( i % 7 == 0) System.out.println("buzz");
                        else System.out.println(i);
        }
    }

}
/*

private static final Map<Integer, String> LOOKUP = Map.of(
        0, "fizzbuzz", // 00 in binary (rem 5 == 0, rem 7 == 0)
        1, "fizz",     // 01 in binary (rem 5 == 0, rem 7 != 0)
        2, "buzz"      // 10 in binary (rem 5 != 0, rem 7 == 0)
    );

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            int key = (i % 5 == 0 ? 0 : 1) | (i % 7 == 0 ? 0 : 2);
            System.out.println(LOOKUP.getOrDefault(key, String.valueOf(i)));
        });
    }


    IntStream.rangeClosed(1, 100).forEach(i -> {
            String result = (i % 5 == 0 ? "fizz" : "") + (i % 7 == 0 ? "buzz" : "");
            System.out.println(result.isEmpty() ? i : result);
        });
* */


