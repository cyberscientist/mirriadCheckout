import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import item.Item;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void testEqualityAndHash(){
        //make two "equal" Items
        Item item1 = new Item("testItem", 10);
        Item item2 = new Item("testItem", 10);
        //check equals method
        assertTrue(item1.equals(item2));
        //put item1 in hashSet and check if set contains Item2
        HashSet<Item> items = Sets.newHashSet(item1);
        assertTrue(items.contains(item2));
    }

    //FIXME THIS TEST IS NOT TESTING ANYTHING

    @Test
    public void testAgain() {
        List<Item> purchasedItems = Lists.newArrayList(
                new Item("Item-x", 100),
                new Item("Item-Y", 50),
                new Item("Item-z", 20)
        );

        List<Item> discountItems = Lists.newArrayList(
                new Item("Item-x", 100),
                new Item("Item-Y", 50),
                new Item("Item-z", 20)
        );

        purchasedItems.forEach(i ->
                        System.out.println(discountItems.contains(i))
        );

        purchasedItems.stream().forEach( i -> System.out.println( discountItems.contains(i) + "****"));

    }

}