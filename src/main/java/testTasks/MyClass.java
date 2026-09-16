package testTasks;

// Public vs private access.
class MyClass {
    private int alpha; // private access
    public int beta; // public access
    int gamma; // default access

    /* Methods to access alpha. It is OK for a
    member of a class to access a private member
    of the same class.
    */
    void setAlpha(int a) {
        alpha = a;
    }

    int getAlpha() {
        return alpha;
    }
}