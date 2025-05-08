import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    @Test
    void everyStatementTest() {
        // 1. allItems is null → should throw exception
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "1234567890123456"));

        // 2. Item with null name → should throw exception
        Item item2 = new Item(null, 1, 100, 0.0);
        List<Item> list2 = List.of(item2);
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(list2, "1234567890123456"));

        // 3. Item with price > 300 and has discount → subtract 30 and apply discount
        Item item3 = new Item("Laptop", 1, 400, 0.1);
        List<Item> list3 = List.of(item3); // expected = -30 + 400 * (1 - 0.1) * 1 = 330.0
        assertEquals(330.0, SILab2.checkCart(list3, "1234567890123456"));

        // 4. Valid item, no discount, invalid card length -> should throw exception
        Item item4 = new Item("Phone", 1, 100, 0.0);
        List<Item> list4 = List.of(item4);
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(list4, "1234"));

        // 5. Invalid character in cardNumber → should throw exception
        Item item5 = new Item("Book", 1, 100, 0.0);
        List<Item> list5 = List.of(item5);
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(list5, "1234abcd9012#$%6"));

    }

    @Test
    void multipleConditionTest() {
        // if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)

        // 1. F || F || F → F -> no -30
        Item mc1 = new Item("Item1", 1, 100, 0.0);
        assertEquals(100.0, SILab2.checkCart(List.of(mc1), "1234567890123456"));

        // 2. F || F || T → T -> -30 applied
        Item mc2 = new Item("Item2", 11, 100, 0.0);
        // expected = -30 + 100 * 11 = -30 + 1100 = 1070
        assertEquals(1070, SILab2.checkCart(List.of(mc2), "1234567890123456"));

        // 3. F || T || F → T -> -30 applied, discount applied
        Item mc3 = new Item("Item3", 1, 100, 0.2);
        // expected = -30 + 100 * (1 - 0.2) * 1 = -30 + 80 = 50
        assertEquals(50, SILab2.checkCart(List.of(mc3), "1234567890123456"));

        // 4. F || T || T → T -> -30, discount applied
        Item mc4 = new Item("Item4", 11, 100, 0.1);
        // expected = -30 + 100 * (1 - 0.1) * 11 = -30 + 990 = 960
        assertEquals(960, SILab2.checkCart(List.of(mc4), "1234567890123456"));

        // 5. T || F || F → T -> -30
        Item mc5 = new Item("Item5", 1, 400, 0.0);
        // expected = -30 + 400 = -30 + 400 = 370
        assertEquals(370, SILab2.checkCart(List.of(mc5), "1234567890123456"));

        // 6. T || F || T → T -> -30
        Item mc6 = new Item("Item6", 11, 400, 0.0);
        // expected = -30 + 400 * 11 = -30 + 4400 = 4370
        assertEquals(4370, SILab2.checkCart(List.of(mc6), "1234567890123456"));

        // 7. T || T || F → T -> -30, discount applied
        Item mc7 = new Item("Item7", 1, 400, 0.2);
        // expected7 = -30 + 400 * (1 - 0.2) * 1 = -30 + 320 = 290
        assertEquals(290, SILab2.checkCart(List.of(mc7), "1234567890123456"));

        // 8. T || T || T → T -> -30, discount applied
        Item mc8 = new Item("Item8", 20, 500, 0.5);
        // expected = -30 + 500 * (1 - 0.5) * 20 = -30 + 5000 = 4970
        assertEquals(4970, SILab2.checkCart(List.of(mc8), "1234567890123456"));
    }

}