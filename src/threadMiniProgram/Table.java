package threadMiniProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Table extends  Thread {
    private int numberOfCustomer;
    private String tableName;
    private List<String> orderedMenu = new ArrayList<>();
    private String dishesStatus;

    public int getNumberOfCustomer() {
        return numberOfCustomer;
    }

    public void setNumberOfCustomer(int numberOfCustomer) {
        this.numberOfCustomer = numberOfCustomer;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableNumber(int tableNumber) {
        tableName = this.getName();
    }

    public List<String> getOrderedMenu() {
        return orderedMenu;
    }

    public void setOrderedMenu(List<String> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public String getDishesStatus() {
        return dishesStatus;
    }

    public void setDishesStatus(String dishesStatus) {
        this.dishesStatus = dishesStatus;
    }

    public void enterCustomer(Scanner scanner){

        System.out.println("테이블 이름 : "+ this.getName() + "의 손님들이 입장하였습니다. 손님들은 몇명이 들어왔습니까?");
        int numberOfCustomer = scanner.nextInt();
        if(numberOfCustomer > 4){
            System.out.println("단체 테이블이 없어요.");
            this.enterCustomer(scanner);
        }
        this.setNumberOfCustomer(numberOfCustomer);
    }

    public void waitingDishes(){
        System.out.println("메뉴 확인 완료. 요리중...");
        this.setDishesStatus(DishStatus.COOKING.name());
    }

    public void servingDishesCompleted(){
        System.out.println(this.getName() + "테이블의 요리 나왔습니다~");
        System.out.print("주문하신 요리 : ");
        for(String nameOfMenu : orderedMenu){
            System.out.print(nameOfMenu + " / ");
        }
        System.out.println();
        this.setDishesStatus(DishStatus.SERVERED.name());
    }


    public void finishedEating() throws InterruptedException {
        this.wait(2000);
        System.out.println("잘먹었습니다! 안녕히 계세요.");
        this.interrupt();
    }

}
