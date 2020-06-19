package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        final int TASK_COUNT = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(TASK_COUNT);

        VolatileDemo volatileDemo = new VolatileDemo();


        for (int i = 0; i < TASK_COUNT; i++) {
            // lambda
//            task = () -> Thread.currentThread().getName();

            Callable<String> task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String currentThreadName = Thread.currentThread().getName();

                    for(int i = 0; i < 10; i++) {
                        volatileDemo.increment();
//                        System.out.println(String.format("current thread name = %s, index = %d", currentThreadName, i));
                    }

                    return currentThreadName;
                }
            };

            executorService.submit(task);

        }


        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);

            System.out.println(String.format("counter is %d", volatileDemo.getCounter()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
