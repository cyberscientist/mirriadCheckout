package discount;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TwoForSpacialPriceTest {
    private Multiset<Item> itemsPurchased;

    @Before
    public void setUp() {
        itemsPurchased = HashMultiset.create();
        itemsPurchased.add(new Item("testItem1", 10), 3);
    }

    @Test
        public void getAmountToDeduct()throws Exception {
            int amountToDeduct = 5;
        Map<Item, Integer> itemWithDiscount = Maps.newHashMap();
        itemWithDiscount.put(new Item("testItem1", 10), 1);

        Discount twoForSpecialPrice = new TwoForSpacialPrice(itemWithDiscount, "Two For Spacial Price", amountToDeduct);
            assertEquals(5, twoForSpecialPrice.getAmountToDeduct(itemsPurchased));
        }

}