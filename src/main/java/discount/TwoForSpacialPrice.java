package discount;

import item.Item;

import java.util.List;
import java.util.Set;

/**
 * Created by ali on 18/02/15.
 */
public class TwoForSpacialPrice extends TwoForOneDiscount {
    private int amountToDeduct;

    public TwoForSpacialPrice(Set<Item> itemsForDiscount, List<Item> purchasedItems, long numberItemNeeded, int amountToDeduct ) {
        super(itemsForDiscount, purchasedItems, numberItemNeeded);
        this.amountToDeduct = amountToDeduct;
    }


    @Override
    public int getAmountToDeduct() {
        return amountToDeduct;
    }
}
