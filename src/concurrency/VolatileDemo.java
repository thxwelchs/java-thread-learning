package concurrency;

public class VolatileDemo {

    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public int increment() {
        return counter++;
    }
}
