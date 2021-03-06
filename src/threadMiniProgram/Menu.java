package threadMiniProgram;

import java.util.*;

public class Menu {

    private int price;

    private Map<Integer, String> menuMap = new HashMap();


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Map<Integer, String> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(Map<Integer, String> menuMap) {
        this.menuMap = menuMap;
    }

    //TODO: list로 바꾸기
    public Menu() {
        int i =1;
        for(MenuBoard menuName: MenuBoard.values()){
            menuMap.put(i++, menuName.name());
        }
    }

    public void showingMenu(){
        System.out.println("어떤 것을 주문하시겠습니까?");
        for(Integer menuNumber : this.getMenuMap().keySet()){
            System.out.println(menuNumber +" : "+ this.getMenuMap().get(menuNumber));
        }
    }

    public List<String> orderingMenu(Scanner scanner, int numberOfCustomer){
        List<String> orderedMenu = new ArrayList<>();
        for(int numberOfMenu =0; numberOfMenu <numberOfCustomer ; numberOfMenu++){
            int menuNumber = scanner.nextInt();
            orderedMenu.add(this.getMenuMap().get(menuNumber));
        }
        return orderedMenu;
    }

}
