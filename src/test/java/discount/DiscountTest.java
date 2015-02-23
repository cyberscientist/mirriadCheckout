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
            Set<Item> itemWithDiscount= Sets.newHashSet( new Item("testItem1", 10) );
            TwoForOneDiscount twoForOne = new TwoForOneDiscount(itemWithDiscount);
            assertTrue(twoForOne.isValidForDiscount(itemsPurchased));
    }


    @Test
    public void getAmountToDeduct()throws Exception {
        Set<Item> itemWithDiscount= Sets.newHashSet( new Item("testItem1", 10) );
        TwoForOneDiscount twoForOne = new TwoForOneDiscount(itemWithDiscount);
        assertEquals(10, twoForOne.getAmountToDeduct(itemsPurchased));
    }
}