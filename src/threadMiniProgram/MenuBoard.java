package threadMiniProgram;

public enum MenuBoard {

    PASTA(1), SALAD(2), PIZZA(3), CHILI_FRIES(4);

    private int menuConverter;

    MenuBoard(int menuConverter) {
        this.menuConverter = menuConverter;
    }
}
