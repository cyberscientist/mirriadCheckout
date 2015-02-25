package discount;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiscountTest {

    private Multiset<Item> itemsPurchased;

    @Before
    public void setUp() {
        itemsPurchased = HashMultiset.create();
        itemsPurchased.add(new Item("testItem1", 10), 3);
    }

    @Test
    public void testIsValidForDiscount() throws Exception {
        Map<Item, Integer> itemWithDiscount = Maps.newHashMap();
        itemWithDiscount.put(new Item("testItem1", 10), 3);
        TwoForOneDiscount twoForOne = new TwoForOneDiscount(itemWithDiscount, "2 for 1");
            assertTrue(twoForOne.isValidForDiscount(itemsPurchased));
    }


    @Test
    public void getAmountToDeduct()throws Exception {
        Map<Item, Integer> itemWithDiscount = Maps.newHashMap();
        itemWithDiscount.put(new Item("testItem1", 10), 3);
        TwoForOneDiscount twoForOne = new TwoForOneDiscount(itemWithDiscount, "2 for 1");
        assertEquals(10, twoForOne.getAmountToDeduct(itemsPurchased));
    }
}