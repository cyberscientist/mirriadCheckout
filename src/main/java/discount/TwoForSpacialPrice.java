package discount;

import item.Item;

import java.util.List;
import java.util.Map;

public class TwoForSpacialPrice extends TwoForOneDiscount {
    private int amountToDeductForOffer;

    public TwoForSpacialPrice(Map<Item, Integer> itemsForDiscount, String name, int amountToDeductForOffer) {
        super(itemsForDiscount, name);
        this.amountToDeductForOffer = amountToDeductForOffer;
    }

    @Override
    public int getAmountToDeduct(List<Item> purchasedItems) {
        return amountToDeductForOffer;
    }
}
