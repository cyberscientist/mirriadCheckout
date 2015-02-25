package discount;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NItemsOfXGetsKItemsYFreeTest {

    private Multiset<Item> itemsPurchased;
    private NItemsOfXGetsKItemsYFree  nItemsOfxGetsKItemsYFree;
    private Table<String, Item, Integer> itemWithDiscount;

    @Before
    public void setUp() {
        itemsPurchased = HashMultiset.create();
        itemsPurchased.add(new Item("testItem1", 10), 3);
        itemsPurchased.add(new Item("freebie", 3), 3);
        itemsPurchased.add(new Item("testItem2", 10), 1);
        itemsPurchased.add(new Item("testItem3", 10), 1);


        itemWithDiscount = HashBasedTable.create();
        itemWithDiscount.put("DISCOUNTED-ITEMS", new Item("testItem1", 10), 3);
        itemWithDiscount.put("FREEBIE", new Item("freebie", 3), 3);
        nItemsOfxGetsKItemsYFree = new NItemsOfXGetsKItemsYFree(itemWithDiscount, "Buy 3 Items Get 3 freebies");

    }

    @Test
    public void testIsValidForDiscount() throws Exception {
         assertTrue(nItemsOfxGetsKItemsYFree.isValidForDiscount(itemsPurchased));
    }


    @Test
    public void getAmountToDeduct()throws Exception {
        assertEquals(9, nItemsOfxGetsKItemsYFree.getAmountToDeduct(itemsPurchased));
    }
}