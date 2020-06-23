package basicConceptsOfThread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {


    private static AtomicInteger counter = new AtomicInteger();

    public static int getNextUniqueIndex(){

        return counter.getAndIncrement();
    }

    public static void main(String[] args) {
        int returnValue = getNextUniqueIndex();
        System.out.println(returnValue);

    }
}
