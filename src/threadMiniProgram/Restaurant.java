package threadMiniProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Restaurant{



    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        // ThreadPool이 1개
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable runnable = new Runnable() {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
            @Override
            public void run() {
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



    }

}
