package basicConceptsOfThread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {

        Thread.sleep(1000);

        // 이 callable task를 실행하는 thread 이름을 반환한다.
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {

        // thread pool의 크기는 10, executors utility class에서 executorService를 가져온다.
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // callable과 연관되어 있는 future object를 잡아놓을 list를 만든다.
        List<Future<String>> list = new ArrayList<Future<String>>();

        // myCallable을 인스턴스화 해준다
        Callable<String> callable = new MyCallable();

        for(int i =0; i<100; i++){
            // thread pool가 실행한 callable task를 submit
            Future<String> future = executor.submit(callable);

            // list에 future를 더해준다. 사용하는 future의 리턴된 값을 갖을 수 있다.
            list.add(future);
        }

        for(Future<String> fut : list){
            try {
                // 리턴된 future value를 콘솔에 찍는다.
                // future.get()이 task가 끝날때까지 기다리기 때문에 console output이 늦게됨
                System.out.println(new Date() + "::" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
