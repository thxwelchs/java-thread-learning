package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException  {
        final int TASK_COUNT = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(TASK_COUNT);

        // 공유할 객체
        VolatileDemo volatileDemo = new VolatileDemo();

        List<Callable<String>> tasks = new ArrayList<>();

        for (int i = 0; i < TASK_COUNT; i++) {
            // lambda
//            task = () -> Thread.currentThread().getName();

            Callable<String> task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String currentThreadName = Thread.currentThread().getName();

                    for(int i = 0; i < 10; i++) {
                        volatileDemo.increment(); //총 5개의 쓰레드가 10번 반복 증가하는 작업을 하니까.. 최종값은 50이 되어야 하지 않을까?
//                        System.out.println(String.format("current thread name = %s, index = %d", currentThreadName, i));
                    }

                    return String.format("thread name = %s, current count = %d", currentThreadName, volatileDemo.getCounter());
                }
            };
            tasks.add(task);
        }


            // 쓰레드에서 모두 작업을 끝낼때까지 기다려본다.
            List<Future<String>> futures = executorService.invokeAll(tasks);


            // Task에서 return한 값을 살펴보기
//            for (Future<String> future : futures) {
//                System.out.println(future.get());
//            }
//            executorService.awaitTermination(1, TimeUnit.SECONDS);

            System.out.println(String.format("counter is %d", volatileDemo.getCounter())); // 최종값은 원자성이 보장되지 않는이상 항상 50이 되지 않는다.

    }

}
