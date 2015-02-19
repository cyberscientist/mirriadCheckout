import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;

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
    //deduct the discounts from total

}
