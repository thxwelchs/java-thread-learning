package threadMiniProgram;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    private final Menu menu = new Menu();
    private final Table table = new Table();
    @Test
    void restaurantTest() {
        System.out.println(table.getName());
        assertEquals(table.getOrderedMenu(), "test");
    }
}