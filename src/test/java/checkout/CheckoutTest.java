package checkout;

import com.google.common.collect.*;
import discount.Discount;
import discount.NItemsOfXGetsKItemsYFree;
import discount.TwoForOneDiscount;
import discount.TwoForSpacialPrice;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {
    private Multiset<Item> itemsPurchased;
    private Set<Discount> discountsAvailable;

    @Before
    public void setUp() {
        itemsPurchased = HashMultiset.create();
        itemsPurchased.add(new Item("testItem1", 10), 3);
        itemsPurchased.add(new Item("testItem2", 10), 3);
        itemsPurchased.add(new Item("testItem3", 20));
        itemsPurchased.add(new Item("testItem4", 30));
        itemsPurchased.add(new Item("freebie", 3), 4);

    }


    @Test
    public void getTotalBeforeDiscountTest() {
        Checkout checkout = new Checkout();
        discountsAvailable = Sets.newHashSet();
        checkout.calculateBill(itemsPurchased, discountsAvailable);
        assertEquals(122, checkout.getTotalBeforeDiscount());
    }

    @Test
    public void getTotalAfterDiscountTest() {
        Checkout checkout = new Checkout();
        Map<Item, Integer> twoForOneDiscountItems = Maps.newHashMap();
        twoForOneDiscountItems.put(new Item("testItem1", 10), 3);
        discountsAvailable = Sets.newHashSet(
                new TwoForSpacialPrice(twoForOneDiscountItems, "Two For Spacial Price", 5)
        );
        checkout.calculateBill(itemsPurchased, discountsAvailable);
        assertEquals(117, checkout.getTotalAfterDiscount());
    }

    @Test
    public void noneOverLappingDiscounts() {
        Checkout checkout = new Checkout();

        Map<Item, Integer> twoForOneDiscountItems = Maps.newHashMap();
        twoForOneDiscountItems.put(new Item("testItem1", 10), 3);

        Table<String, Item, Integer> buy3GetFreebie = HashBasedTable.create();
        buy3GetFreebie.put("DISCOUNTED-ITEMS", new Item("testItem2", 10), 3);
        buy3GetFreebie.put("FREEBIE", new Item("freebie", 3), 3);
        discountsAvailable = Sets.newHashSet(
                new TwoForSpacialPrice(twoForOneDiscountItems, "Two For Spacial Price", 5),
                new NItemsOfXGetsKItemsYFree(buy3GetFreebie, "Buy 3 Get 3 Freebies")
        );
        checkout.calculateBill(itemsPurchased, discountsAvailable);
        assertEquals(108, checkout.getTotalAfterDiscount());
    }

    @Test
    public void overLappingDiscounts() {
        Checkout checkout = new Checkout();

        Map<Item, Integer> twoForSpecialDiscountItems = Maps.newHashMap();
        twoForSpecialDiscountItems.put(new Item("testItem1", 10), 3);

        Map<Item, Integer> twoForOneDiscountItems = Maps.newHashMap();
        twoForOneDiscountItems.put(new Item("testItem2", 10), 3);

        Table<String, Item, Integer> buy3GetFreebie = HashBasedTable.create();
        buy3GetFreebie.put("DISCOUNTED-ITEMS", new Item("testItem2", 10), 3);
        buy3GetFreebie.put("FREEBIE", new Item("freebie", 3), 3);
        discountsAvailable = Sets.newHashSet(
                new TwoForSpacialPrice(twoForSpecialDiscountItems, "Two For Spacial Price", 5),
                new NItemsOfXGetsKItemsYFree(buy3GetFreebie, "Buy 3 Get 3 Freebies"),
                new TwoForOneDiscount(twoForOneDiscountItems, "Two for one")
        );
        checkout.calculateBill(itemsPurchased, discountsAvailable);
        assertEquals(108, checkout.getTotalAfterDiscount());
    }
}