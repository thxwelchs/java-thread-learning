package basicConceptsOfThread;

public class NonAtomicOperationTest extends Thread{
    static int count = 1;

    public void run(){
        count ++;
        System.out.println(count);
    }

    public static void main(String[] args) {
        NonAtomicOperationTest nonAtomicOperationTest1 = new NonAtomicOperationTest();
        NonAtomicOperationTest nonAtomicOperationTest2 = new NonAtomicOperationTest();
        NonAtomicOperationTest nonAtomicOperationTest3 = new NonAtomicOperationTest();

        nonAtomicOperationTest1.start();
        nonAtomicOperationTest2.start();
        nonAtomicOperationTest3.start();
    }
}
