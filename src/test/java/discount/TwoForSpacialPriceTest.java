package discount;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TwoForSpacialPriceTest {
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
        public void getAmountToDeduct()throws Exception {
            int amountToDeduct = 5;
            Set<Item> itemWithDiscount= Sets.newHashSet( new Item("testItem1", 10) );
            Discount twoForSpecialPrice = new TwoForSpacialPrice(itemWithDiscount,amountToDeduct);
            assertEquals(5, twoForSpecialPrice.getAmountToDeduct(itemsPurchased));
        }

}