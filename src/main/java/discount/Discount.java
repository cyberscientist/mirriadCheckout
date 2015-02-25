package discount;

import com.google.common.collect.Multiset;
import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.Map;

public abstract class Discount {
    protected Map<Item, Integer> itemsForDiscount;
    protected String name;

    public String getName() {
        return name;
    }

    public abstract boolean isValidForDiscount(Multiset<Item> purchasedItems);

    public abstract int getAmountToDeduct(Multiset<Item> purchasedItems);

    //FIXME - SHOULD THIS NOT BE THIS.PURCHASEDITEMS??
    protected Long getNumberOccurrence(Multiset<Item> purchasedItems) {
        Validate.notNull(purchasedItems);

        return purchasedItems.stream()
                .filter(i -> itemsForDiscount.containsKey(i))
                .count();
    }

    public Map<Item, Integer> getItemsForDiscount() {
        return itemsForDiscount;
    }

}
