package threadMiniProgram;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Restaurant{

    public static void main(String[] args) throws InterruptedException {

        // ThreadPool안에 쓰레드 2
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // TODO: bufferedreader로 바꿔주기
                ThreadPoolExcutor threadPoolExcutor = (ThreadPoolExecutor) executorService;
                Scanner scanner = new Scanner(System.in);
                Table table = new Table();
                table.enterCustomer(scanner);

                Menu menu = new Menu();
                menu.showingMenu();

                List<String> orderedMenu = menu.orderingMenu(scanner, table.getNumberOfCustomer());
                table.setOrderedMenu(orderedMenu);

                table.waitingDishes();
                table.servingDishesCompleted();

                try {
                    table.finishedEating();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };

        for(int i =0; i<10 ; i++){
            executorService.submit(runnable);
        }

        executorService.shutdown();

    }

}
