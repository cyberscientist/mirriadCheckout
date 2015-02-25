package discount;

import com.google.common.collect.Multiset;
import item.Item;

import java.util.Map;


public class ThreeItemsWithCheapestFree extends TwoForOneDiscount{
    public ThreeItemsWithCheapestFree(Map<Item, Integer> itemsForDiscount, String name) {
        super(itemsForDiscount, name);

    }

    @Override
    public boolean isValidForDiscount(Multiset<Item> purchasedItems) {
        long numberOfQualifiedItems = purchasedItems.stream().filter(p -> itemsForDiscount.containsKey(p)).count();
        return ( numberOfQualifiedItems / 3L ) > 0;
    }

    @Override
    public int getAmountToDeduct(Multiset<Item> purchasedItems) {
        return itemsForDiscount.keySet().stream().min((item1, item2) ->
                Integer.compare(item1.getPrice(), item2.getPrice()))
                .get()
                .getPrice();
    }
}
