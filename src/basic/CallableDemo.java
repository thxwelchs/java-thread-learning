package basic;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Thread 실행 흐름을 만들 때 Runnable이 아닌 Callable로 생성 할 수도 있다.
 * Runnable과 다른점은, run이 아닌 call이라는 메소드를 가지고 있다.
 * 또한 call 메소드를 통해, 예외처리가 가능하고, task 작업 후의 설정한 반환값을 반환할 수 있다.
 */

public class CallableDemo implements Callable<String> {
    private Thread t;
    private String threadName;

    @Override
    public String call() throws Exception {
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // Let the thread sleep for a while.
                // Sleep 하지 않으면, 컴퓨터는 너무 빨리 연산하기 때문에, 각 쓰레드가 이미 처리해버려서 쓰레드가 번갈아가면서 처리하는게 아닌, 일반적으로 순차적으로 처리하는 출력결과로 보인다.
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");

        System.out.println("Running " +  threadName );

        System.out.println("Thread " +  threadName + " exiting.");
        return threadName;
    }

    CallableDemo(String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }


    public void start () throws InterruptedException {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            FutureTask<String> futureTask = new FutureTask<>(this);
            Thread t = new Thread(futureTask); // Runnable과는 다르게, Callable을 가진, FutureTask를 생성자 인자로 넘겨주어 Thread를 생성한다.
            // FutureTask 또한, Runnable의 서브클래스이다.
            t.start ();
//            t.join(); //이 쓰레드가 실행시킨 작업이 모두 끝날 때까지 기다리게 한다.
        }
    }


}

class TestThread2 {
    public static void main(String[] args) throws InterruptedException {

        // CPU 개수 확인
        System.out.println("az processors" + Runtime.getRuntime().availableProcessors());

        CallableDemo R1 = new CallableDemo("Thread-1");
        R1.start();

        CallableDemo R2 = new CallableDemo("Thread-2");
        R2.start();
    }
}
