package checkout;

import discount.Discount;
import discount.notValidForDiscountException;
import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ali on 17/02/15.
 */
public class Checkout {

    private int totalAfterDiscount;


    //find totalBeforeDiscount
    public int calculateTotalBeforeDiscount( List<Item> purchasedItems) {
        Validate.notNull(purchasedItems);

        final int[] totalBeforeDiscount = {0};
        purchasedItems.stream().forEach((item) -> {
            totalBeforeDiscount[0] += item.getPrice();
        });
        return totalBeforeDiscount[0];
    }

    //find which Discounts to apply
    public Set<Discount> findAllQualifiedDiscounts(List<Item> itemsPurchased, Set<Discount> discountsAvaible) throws notValidForDiscountException {
        int totalBeforeDiscount = calculateTotalBeforeDiscount(itemsPurchased);

        discountsAvaible.stream().forEach( d -> {
            d.isValidForDiscount(itemsPurchased);
            try {
                totalAfterDiscount = totalBeforeDiscount - d.getAmountToDeduct(itemsPurchased);
            } catch (notValidForDiscountException e) {
                e.printStackTrace();
            }
            d.getNumberItemNeeded();
            itemsPurchased.

        });

        return getCollect(itemsPurchased, discountsAvaible);
    }

    private Set<Discount> getCollect(List<Item> itemsPurchased, Set<Discount> discountsAvaible) {
        return discountsAvaible.stream().filter(d ->
                        d.isValidForDiscount(itemsPurchased)
        ).collect(Collectors.toSet());
    }

    //deduct the discounts from total

}
