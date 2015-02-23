package discount;

import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Set;

public abstract class Discount {
    protected Set<Item> itemsForDiscount;

    public long getNumberItemNeeded() {
        return numberItemNeeded;
    }

    protected long numberItemNeeded;

    public abstract boolean isValidForDiscount( List<Item> purchasedItems);

    public abstract int getAmountToDeduct( List<Item> purchasedItems) throws notValidForDiscountException;

    //FIXME - SHOULD THIS NOT BE THIS.PURCHASEDITEMS??
    public Long getNumberOccurrence(List<Item> purchasedItems) {
        Validate.notNull(purchasedItems);

        return purchasedItems.stream()
                .filter(i -> itemsForDiscount.contains(i))
                .count();
    }

}
