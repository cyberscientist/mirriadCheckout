package discount;

import discount.Discount;
import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Set;

public class TwoForOneDiscount extends Discount {
    int numberItemNeeded = 2;

    public TwoForOneDiscount(Set<Item> itemsForDiscount) {
        this.itemsForDiscount = itemsForDiscount;
    }

    @Override
    public boolean isValidForDiscount( List<Item> purchasedItems) {
        Validate.notNull(purchasedItems);
//FIXME SHOULD THIS NOT BE > THAN NUMBER OF ITEMS NEEDED???
        return getNumberOccurrence(purchasedItems) >= numberItemNeeded;
    }

    @Override
    public int getAmountToDeduct( List<Item> purchasedItems) throws notValidForDiscountException {
        if(!isValidForDiscount(purchasedItems)) {
            throw new notValidForDiscountException("This discount is not applicable.");
        }
        int singleDeduction = itemsForDiscount.stream().findFirst().get().getPrice();
        int numberOfDeduction = (int) (long) getNumberOccurrence(purchasedItems);

        return singleDeduction * (numberOfDeduction/numberItemNeeded);
    }
}
