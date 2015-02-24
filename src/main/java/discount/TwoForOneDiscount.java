package discount;

import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Map;

public class TwoForOneDiscount extends Discount {

    protected int numberItemNeeded;

    public TwoForOneDiscount(Map<Item, Integer> itemsForDiscount, String name) {
        this.itemsForDiscount = itemsForDiscount;
        this.numberItemNeeded = itemsForDiscount.values().stream().findFirst().get();
        this.name = name;
    }

    @Override
    public boolean isValidForDiscount( List<Item> purchasedItems) {
        Validate.notNull(purchasedItems);
//FIXME SHOULD THIS NOT BE > THAN NUMBER OF ITEMS NEEDED???
        return getNumberOccurrence(purchasedItems) >= numberItemNeeded;
    }

    @Override
    public int getAmountToDeduct(List<Item> purchasedItems) {

        int singleDeduction = itemsForDiscount.keySet().stream().findFirst().get().getPrice();
        int numberOfDeduction = (int) (long) getNumberOccurrence(purchasedItems);

        return singleDeduction * (numberOfDeduction/numberItemNeeded);
    }
}
