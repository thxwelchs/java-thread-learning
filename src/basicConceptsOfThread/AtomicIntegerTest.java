package basicConceptsOfThread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    /**
     * TODO: ++, -- 연산 등에서만 사용할 수 있는 걸까?
     */
    private static AtomicInteger counter = new AtomicInteger();

    public static int getNextUniqueIndex(){
        return counter.getAndIncrement();
    }

    public static void main(String[] args) {
        int returnValue = getNextUniqueIndex();
        System.out.println(returnValue);

    }
}
