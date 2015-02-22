package discount;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class NItemsOfXGetsKItemsYFreeTest {

    private List<Item> itemsPurchased;
    private NItemsOfXGetsKItemsYFree  nItemsOfxGetsKItemsYFree;
    private Set<Item> itemWithDiscount;
    private long numberOfItemsNeeded;

    @Before
    public void setUp() {
        itemsPurchased = Lists.newArrayList(
                new Item("testItem1", 10),
                new Item("testItem1", 10),
                new Item("testItem1", 10),
                new Item("testItem2", 10),
                new Item("testItem3", 10),
                new Item("freebie", 3),
                new Item("freebie", 3),
                new Item("freebie", 3),
                new Item("freebie", 3)
        );

        numberOfItemsNeeded = 2;
        itemWithDiscount = Sets.newHashSet( new Item("testItem1", 10) );
        nItemsOfxGetsKItemsYFree = new NItemsOfXGetsKItemsYFree(itemWithDiscount, itemsPurchased, numberOfItemsNeeded, new Item("freebie", 3), 3);

    }

    @Test
    public void testIsValidForDiscount() throws Exception {
         assertTrue(nItemsOfxGetsKItemsYFree.isValidForDiscount());
    }


    @Test
    public void getAmountToDeduct()throws Exception {
        assertEquals(9, nItemsOfxGetsKItemsYFree.getAmountToDeduct());
    }
}