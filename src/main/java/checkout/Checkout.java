package checkout;

import discount.Discount;
import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;

public class Checkout {

    private int totalAfterDiscount;
    private int totalBeforeDiscount;

    public int calculateTotalBeforeDiscount(List<Item> purchasedItems) {
        Validate.notNull(purchasedItems);

        final int[] totalBeforeDiscount = {0};
        purchasedItems.stream().forEach((item) -> {
            totalBeforeDiscount[0] += item.getPrice();
            System.out.println(item.getName() + "     " + item.getPrice());
        });
        return totalBeforeDiscount[0];
    }

    public int calculateTotalAfterDiscount(List<Item> purchasedItems, List<Discount> discounts) {
        totalBeforeDiscount = calculateTotalBeforeDiscount(purchasedItems);

        discounts.stream().forEach(discount -> {
            if (discount.isValidForDiscount(purchasedItems)) {
                System.out.println(discount.getName() + discount.getAmountToDeduct(purchasedItems));
                totalAfterDiscount -= discount.getAmountToDeduct(purchasedItems);
            }

        });


    }

}
