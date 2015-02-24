package discount;

import item.Item;

import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class ThreeItemsWithCheapestFree extends TwoForOneDiscount{
    public ThreeItemsWithCheapestFree(Map<Item, Integer> itemsForDiscount, String name) {
        super(itemsForDiscount, name);

    }

    @Override
    public boolean isValidForDiscount(List<Item> purchasedItems) {
        long numberOfQualifiedItems = purchasedItems.stream().filter(p -> itemsForDiscount.containsKey(p)).count();
        return ( numberOfQualifiedItems / 3L ) > 0;
    }

    @Override
    public int getAmountToDeduct(List<Item> purchasedItems) {
        return itemsForDiscount.values().stream()
                .min(Comparator.comparingInt(Item::getPrice))
                .get()
                .getPrice();
    }
}
