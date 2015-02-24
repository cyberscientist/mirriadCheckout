package discount;

import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Map;

public abstract class Discount {
    protected Map<Item, Integer> itemsForDiscount;
    protected String name;

    public abstract boolean isValidForDiscount( List<Item> purchasedItems);

    public abstract int getAmountToDeduct(List<Item> purchasedItems);

    //FIXME - SHOULD THIS NOT BE THIS.PURCHASEDITEMS??
    protected Long getNumberOccurrence(List<Item> purchasedItems) {
        Validate.notNull(purchasedItems);

        return purchasedItems.stream()
                .filter(i -> itemsForDiscount.containsKey(i))
                .count();
    }

    public Map<Item, Integer> getItemsForDiscount() {
        return itemsForDiscount;
    }

}
