package basic;

/**
 * Thread는 Thread를 상속받거나, Runnable을 구현받아 run을 오버라이딩 하는 방법이 있다.
 * 일반적으로 다른 부모 객체를 상속 할 수 있기 때문에, Runnable을 구현 받는 것이 좋다.
 */

public class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    RunnableDemo(String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public void run() {
        System.out.println("Running " +  threadName );
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
    }

    public void start () throws InterruptedException {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
//            t.join(); //이 쓰레드가 실행시킨 작업이 모두 끝날 때까지 기다리게 한다.
        }
    }
}

class TestThread {
    public static void main(String[] args) throws InterruptedException {

        // CPU 개수 확인
        System.out.println("az processors " + Runtime.getRuntime().availableProcessors());

        RunnableDemo R1 = new RunnableDemo( "Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo( "Thread-2");
        R2.start();
    }
}
