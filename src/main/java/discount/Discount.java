package discount;

import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Set;

/**
 * Created by ali on 18/02/15.
 */
public abstract class Discount {
    protected Set<Item> itemsForDiscount;
    protected List<Item> purchasedItems;

    public abstract boolean isValidForDiscount();
    public abstract int getAmountToDeduct() throws notValidForDiscountException;

    public Long getNumberOccurrence(List<Item> purchasedItems) {
        Validate.notNull(purchasedItems);

        return purchasedItems.stream()
                .filter(i -> itemsForDiscount.contains(i))
                .count();
    }
}