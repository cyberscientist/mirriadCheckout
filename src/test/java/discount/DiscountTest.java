package discount;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import discount.TwoForOneDiscount;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

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
            long numberOfItemsNeeded = 3;
            Set<Item> itemWithDiscount= Sets.newHashSet( new Item("testItem1", 10) );
            TwoForOneDiscount twoForOne = new TwoForOneDiscount(itemWithDiscount, itemsPurchased, numberOfItemsNeeded);
            assertTrue(twoForOne.isValidForDiscount());
    }

    @Test
    public void testIsValidForDiscountWrongNoItems() throws Exception {
        itemsPurchased.remove(0);
        long numberOfItemsNeeded = 3;
        Set<Item> itemWithDiscount= Sets.newHashSet( new Item("testItem1", 10) );
        TwoForOneDiscount twoForOne = new TwoForOneDiscount(itemWithDiscount,itemsPurchased,numberOfItemsNeeded);
        assertFalse(twoForOne.isValidForDiscount());
    }

    @Test
    public void getAmountToDeduct()throws Exception {
        long numberOfItemsNeeded = 3;
        Set<Item> itemWithDiscount= Sets.newHashSet( new Item("testItem1", 10) );
        TwoForOneDiscount twoForOne = new TwoForOneDiscount(itemWithDiscount,itemsPurchased,numberOfItemsNeeded);
        assertEquals(10, twoForOne.getAmountToDeduct());
    }
}