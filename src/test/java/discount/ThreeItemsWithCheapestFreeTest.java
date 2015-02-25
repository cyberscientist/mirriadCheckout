package discount;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThreeItemsWithCheapestFreeTest {
    Multiset<Item> purchasedItems;
    @Before
    public void setUp() throws Exception {
        purchasedItems = HashMultiset.create();
        purchasedItems.add(new Item("Item-x", 100));
        purchasedItems.add(new Item("Item-Y", 50));
        purchasedItems.add(new Item("Item-z", 20));
    }

    @Test
    public void testValidations() {
        Map<Item, Integer> itemWithDiscount = new HashMap<>();
        itemWithDiscount.put(new Item("Item-x", 100), 1);
        itemWithDiscount.put(new Item("Item-Y", 50), 1);
        itemWithDiscount.put(new Item("Item-z", 20), 1);


        Discount buyThreeCheapestFree = new ThreeItemsWithCheapestFree(itemWithDiscount, "Cheapest Free");
        assertTrue(buyThreeCheapestFree.isValidForDiscount(purchasedItems));

    }

    @Test
    public void testGetAmountToDeduct() throws Exception {
        Map<Item, Integer> itemWithDiscount = new HashMap<>();
        itemWithDiscount.put(new Item("testItem1", 100), 1);
        itemWithDiscount.put(new Item("testItem2", 50), 1);
        itemWithDiscount.put(new Item("testItem3", 20), 1);

        Discount buyThreeCheapestFree = new ThreeItemsWithCheapestFree(itemWithDiscount, "Cheapest Free");
        assertEquals(20, buyThreeCheapestFree.getAmountToDeduct(purchasedItems));
    }
}