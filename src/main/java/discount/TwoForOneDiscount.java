package discount;

import discount.Discount;
import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Set;

/**
 * Created by ali on 17/02/15.
 */
public class TwoForOneDiscount extends Discount {
    long numberItemNeeded;
    List<Item> purchasedItems;

    public TwoForOneDiscount(Set<Item> itemsForDiscount, List<Item> purchasedItems, long numberItemNeeded) {
        this.itemsForDiscount = itemsForDiscount;
        this.numberItemNeeded = numberItemNeeded;
        this.purchasedItems = purchasedItems;
    }

    @Override
    public boolean isValidForDiscount() {
        Validate.notNull(purchasedItems);
//FIXME SHOULD THIS NOT BE > THAN NUMBER OF ITEMS NEEDED???
        if (getNumberOccurrence(purchasedItems) == numberItemNeeded) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getAmountToDeduct() throws notValidForDiscountException {
        int singleDeduction = itemsForDiscount.stream().findFirst().get().getPrice();
        int numberOfDeduction = (int) (long) getNumberOccurrence(purchasedItems);

        return singleDeduction * numberOfDeduction;
    }
}
