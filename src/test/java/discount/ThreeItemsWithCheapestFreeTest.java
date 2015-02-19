package discount;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ThreeItemsWithCheapestFreeTest {
    List<Item> purchasedItems;
    @Before
    public void setUp() throws Exception {
        purchasedItems = Lists.newArrayList(
                new Item("Item-x", 100),
                new Item("Item-Y", 50),
                new Item("Item-z", 20)
        );

    }

    @Test
    public void testValidations() {
        long numberOfItemsNeeded = 3;
        Set<Item> itemWithDiscount= Sets.newHashSet(
                new Item("Item-x", 100),
                new Item("Item-Y", 50),
                new Item("Item-z", 20)
        );

        Discount buyThreeCheapestFree = new ThreeItemsWithCheapestFree(itemWithDiscount, purchasedItems, numberOfItemsNeeded);
        assertTrue(buyThreeCheapestFree.isValidForDiscount());

    }

    @Test
    public void testGetAmountToDeduct() throws Exception {
        long numberOfItemsNeeded = 3;
        Set<Item> itemWithDiscount= Sets.newHashSet(
                new Item("testItem1", 100),
                new Item("testItem2", 50),
                new Item("testItem3", 20)
        );
        Discount buyThreeCheapestFree = new ThreeItemsWithCheapestFree(itemWithDiscount, purchasedItems, numberOfItemsNeeded);
        try {
            assertEquals(20, buyThreeCheapestFree.getAmountToDeduct());
        } catch (notValidForDiscountException e) {
            e.printStackTrace();
        }
    }
}