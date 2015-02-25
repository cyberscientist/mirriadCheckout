package discount;

import com.google.common.collect.Multiset;
import item.Item;

import java.util.Map;

public class TwoForSpacialPrice extends TwoForOneDiscount {
    private int amountToDeductForOffer;

    public TwoForSpacialPrice(Map<Item, Integer> itemsForDiscount, String name, int amountToDeductForOffer) {
        super(itemsForDiscount, name);
        this.amountToDeductForOffer = amountToDeductForOffer;
    }

    @Override
    public int getAmountToDeduct(Multiset<Item> purchasedItems) {
        return amountToDeductForOffer;
    }
}
