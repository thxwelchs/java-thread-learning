package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {

    private AtomicInteger counter = new AtomicInteger(0);

    public int getCounter() {
        return counter.get();
    }

    public int increment() {
        return counter.incrementAndGet();
    }
}
