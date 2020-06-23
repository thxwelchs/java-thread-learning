package threadMiniProgram;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Restaurant{



    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        // ThreadPool이 1개
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // TODO: 왜 ThreadPoolExecutor가 선언 되었을 때랑 아닐 때랑 출력 값이 다를까
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
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

        //System.out.println("===============영업완료===");
    }

}
