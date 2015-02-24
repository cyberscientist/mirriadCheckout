package discount;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiscountTest {

    private List<Item> itemsPurchased;

    @Before
    public void setUp() {
        itemsPurchased = Lists.newArrayList(
                new Item("testItem1", 10),
                new Item("testItem1", 10),
                new Item("testItem1", 10)
        );
    }

    @Test
    public void testIsValidForDiscount() throws Exception {
        Map<Item, Integer> itemWithDiscount = Maps.newHashMap();
        itemWithDiscount.put(new Item("testItem1", 10), 1);
        TwoForOneDiscount twoForOne = new TwoForOneDiscount(itemWithDiscount, "2 for 1");
            assertTrue(twoForOne.isValidForDiscount(itemsPurchased));
    }


    @Test
    public void getAmountToDeduct()throws Exception {
        Map<Item, Integer> itemWithDiscount = Maps.newHashMap();
        itemWithDiscount.put(new Item("testItem1", 10), 1);
        TwoForOneDiscount twoForOne = new TwoForOneDiscount(itemWithDiscount, "2 for 1");
        assertEquals(10, twoForOne.getAmountToDeduct(itemsPurchased));
    }
}