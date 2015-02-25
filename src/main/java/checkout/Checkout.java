package checkout;

import com.google.common.collect.Multiset;
import discount.Discount;
import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.Set;

public class Checkout {

    private int totalAfterDiscount;
    private int totalBeforeDiscount;

    public int getTotalBeforeDiscount() {
        return totalBeforeDiscount;
    }

    public int getTotalAfterDiscount() {
        return totalAfterDiscount;
    }

    private int calculateTotalBeforeDiscount(Multiset<Item> purchasedItems) {
        Validate.notNull(purchasedItems);

        final int[] totalBeforeDiscount = {0};
        purchasedItems.stream().forEach((item) -> {
            totalBeforeDiscount[0] += item.getPrice();
            System.out.println(item.getName() + "     " + item.getPrice());
        });
        return totalBeforeDiscount[0];
    }

    private int calculateTotalAfterDiscount(Multiset<Item> purchasedItems, Set<Discount> discounts) {
        totalBeforeDiscount = calculateTotalBeforeDiscount(purchasedItems);
        final int[] totalDeduction = {0};
        discounts.stream().forEach(discount -> {
            if (discount.isValidForDiscount(purchasedItems)) {
                System.out.println(discount.getName() + discount.getAmountToDeduct(purchasedItems));
                totalDeduction[0] += discount.getAmountToDeduct(purchasedItems);
                discount.getItemsForDiscount().entrySet().stream().forEach(e ->
                                purchasedItems.remove(e.getKey(), e.getValue())
                );
            }

        });
        return totalBeforeDiscount - totalDeduction[0];
    }

    public void calculateBill(Multiset<Item> purchasedItems, Set<Discount> discounts) {
        this.totalBeforeDiscount = calculateTotalBeforeDiscount(purchasedItems);
        this.totalAfterDiscount = calculateTotalAfterDiscount(purchasedItems, discounts);
    }
}