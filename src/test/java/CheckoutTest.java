import com.google.common.collect.Lists;
import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
                new Item("testItem2", 10)
        );
    }
    
    @Test
    public void calculateTotalBeforeDiscount() {
            assertEquals(50, new Checkout().calculateTotalBeforeDiscount(itemsPurchased));
    }

}