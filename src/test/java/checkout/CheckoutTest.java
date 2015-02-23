package checkout;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import discount.Discount;
import discount.ThreeItemsWithCheapestFree;
import discount.TwoForSpacialPrice;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CheckoutTest {
    private List<Item> itemsPurchased;

    @Before
    public void setUp() {
        itemsPurchased = Lists.newArrayList(
                new Item("testItem1", 10),
                new Item("testItem1", 10),
                new Item("testItem1", 10),
                new Item("testItem2", 10),
                new Item("testItem3", 20),
                new Item("testItem4", 30),
                new Item("freebie", 3),
                new Item("freebie", 3),
                new Item("freebie", 3),
                new Item("freebie", 3)
        );
    }

    @Test
    public void addAllItemsPrices() {
        Checkout checkout = new Checkout();
        assertEquals(102, checkout.calculateTotalBeforeDiscount(itemsPurchased));
    }

    @Test
    public void findAllQualifiedDiscountsTest() {
        Set<Item> itemsForCheapestFreeDiscount = Sets.newHashSet(
                new Item("testItem2", 10),
                new Item("testItem3", 20),
                new Item("testItem4", 30)
        );


       Set<Discount> discountsAvaible = Sets.newHashSet(
               new TwoForSpacialPrice(Sets.newHashSet(new Item("testItem1", 10)), 15 ),
               new ThreeItemsWithCheapestFree(itemsForCheapestFreeDiscount)
       );

       Checkout checkout = new Checkout();

       assertEquals(2,checkout.findAllQualifiedDiscounts(itemsPurchased, discountsAvaible).size() );
    }

}