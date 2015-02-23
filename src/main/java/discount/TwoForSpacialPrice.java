package discount;

import item.Item;

import java.util.List;
import java.util.Set;

public class TwoForSpacialPrice extends TwoForOneDiscount {
    private int amountToDeductForOffer;
    numberItemNeeded = 2;

    public TwoForSpacialPrice(Set<Item> itemsForDiscount,int amountToDeductForOffer ) {
        super(itemsForDiscount);
        this.amountToDeductForOffer = amountToDeductForOffer;
    }


    @Override
    public int getAmountToDeduct(List<Item> purchasedItems) {
        return amountToDeductForOffer;
    }
}
