package discount;

import com.google.common.collect.Lists;
import item.Item;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by ali on 18/02/15.
 */
public class ThreeItemsWithCheapestFree extends TwoForOneDiscount{
    private long numberItemNeeded = 3;
    public ThreeItemsWithCheapestFree(Set<Item> itemsForDiscount) {
        super(itemsForDiscount);

    }

    @Override
    public boolean isValidForDiscount(List<Item> purchasedItems) {
        long numberOfQualifiedItems = purchasedItems.stream().filter(p -> itemsForDiscount.contains(p)).count();
        return ( numberOfQualifiedItems / 3L ) > 0;
    }

    @Override
    public int getAmountToDeduct(List<Item> purchasedItems) throws notValidForDiscountException {
        if ( !isValidForDiscount(purchasedItems) ) {
            throw new notValidForDiscountException( "Not Valid For This Discount." );
        }
        return itemsForDiscount.stream()
                .min(Comparator.comparingInt(Item::getPrice))
                .get()
                .getPrice();
    }
}
