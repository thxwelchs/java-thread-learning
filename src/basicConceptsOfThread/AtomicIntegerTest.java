package basicConceptsOfThread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    private AtomicInteger counter = new AtomicInteger();

    public int getNextUniqueIndex(){
        return counter.getAndIncrement();
    }
}
