package basic;

import java.util.concurrent.*;

/**
 * Thread Pool 을 생성하여 관리해본다.
 */

public class ThreadPoolDemo {
    public static void main(String[] args) {

        // JVM이 돌아가는 환경의 CPU 코어 갯수
        final int CPU_CORES = Runtime.getRuntime().availableProcessors();

        // 최대 스레드 개수를 코어 개수만큼 설정한, 스레드 생성
        ExecutorService executorService = Executors.newFixedThreadPool(CPU_CORES);
        // 쓰레드풀 자체의 갯수 ? 쓰레드풀 갯수와 CPU 코어 갯수가 관련이 있나 ?

        for (int i = 0; i < 10; i++) {
            // 작업 생성 Callable을 활용

            final int index = i; // callable inner클래스에서 사용하기 위해 final로 재할당

            Callable<String> task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    // 현재 task의 쓰레드명

                    String threadName = Thread.currentThread().getName();

                    System.out.println(String.format("현재 인덱스 = [%d], 현재 쓰레드 = [%s]", index, threadName));

                    return threadName;
                }
            };

            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
            System.out.println(String.format("CPU Core 갯수 = %d, 쓰레드 풀에 있는 총 쓰레드 수 = %d", CPU_CORES, threadPoolExecutor.getPoolSize()));


            // Task Queue에 작업 전송, task는 Callable이기 때문에, 반환값을 가질 수 있고, 이 데이터를 확인하기 위해서는 Future 인터페이스를 사용하여야 한다.
            // 왜냐하면 다른 실행 흐름인 멀티 쓰레드의 task는 언제 끝날지, 예측 할 수 없기 때문에,
            // Future가 Callable.call() 메소드의 실행이 완료될 때 까지 블록킹되며, Callable.call() 메소드의 실행이 완료되면 그 결과값을 리턴 할 수 있다.
            Future<String> future = executorService.submit(task);
//            executorService.submit(task);

            // blocking 테스트
//            try {
//                String taskResult = future.get();
//                System.out.println(String.format("Task의 결과 = [%s]", taskResult));
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }

        }

        // 스레드풀 종료
        executorService.shutdown();
        System.out.println("스레드풀 종료~~~");
    }
}
