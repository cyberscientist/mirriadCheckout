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

    public ThreeItemsWithCheapestFree(Set<Item> itemsForDiscount, List<Item> purchasedItems, long numberItemNeeded) {
        super(itemsForDiscount, purchasedItems, numberItemNeeded);
    }

    @Override
    public boolean isValidForDiscount() {
        long numberOfQualifiedItems = purchasedItems.stream().filter(p -> itemsForDiscount.contains(p)).count();
        return ( numberOfQualifiedItems / 3L ) > 0;
    }

    @Override
    public int getAmountToDeduct() throws notValidForDiscountException {
        if ( !isValidForDiscount() ) {
            throw new notValidForDiscountException( "Not Valid For This Discount." );
        }
        int cheapestPrice = itemsForDiscount.stream()
                .min(Comparator.comparingInt(Item::getPrice))
                .get()
                .getPrice();
        return cheapestPrice;
    }
}
