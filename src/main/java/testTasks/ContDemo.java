package testTasks;

class ContDemo {
    public static void cont(String[] args) {
        int i;
        // print even numbers between 0 and 100
        for(i = 0; i<=100; i++) {
            if((i%2) != 0) continue; // iterate
            System.out.println(i);
        }
    }
}
