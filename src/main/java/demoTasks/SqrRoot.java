package demoTasks;

public class SqrRoot {

    public static void printSqrRootFromOneToHundred() {
        double num;
        double sroot;
        double rerr;

        for(num = 1.0; num < 100.0; num++) {
            sroot = Math.sqrt(num);
            System.out.println("Square root of " + num +
                    " is " + sroot);
            // compute rounding error
            rerr = num - (sroot * sroot);
            System.out.println("Rounding error is " + rerr);
            System.out.println();
        }
    }
}
