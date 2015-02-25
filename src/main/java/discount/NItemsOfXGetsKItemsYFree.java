package discount;

import com.google.common.collect.Multiset;
import com.google.common.collect.Table;
import item.Item;
import org.apache.commons.lang3.Validate;

public class NItemsOfXGetsKItemsYFree extends TwoForOneDiscount {
    Item freebieItem;
    int numberOfFreebies;

    public NItemsOfXGetsKItemsYFree(Table<String, Item, Integer> itemsForDiscount, String name) {
        super(itemsForDiscount.row("DISCOUNTED-ITEMS"), name);
        this.freebieItem = itemsForDiscount.row("FREEBIE").keySet().stream().findFirst().get();
        this.numberOfFreebies = itemsForDiscount.row("FREEBIE").values().stream().findFirst().get();
    }

    @Override
    public boolean isValidForDiscount(Multiset<Item> purchasedItems) {
        Validate.notNull(purchasedItems);
        return getNumberOccurrence(purchasedItems) >= numberItemNeeded && freebieItem != null && getNumberFreebiesPurchased(purchasedItems) >= numberOfFreebies;
    }

    @Override
    public int getAmountToDeduct(Multiset<Item> purchasedItems) {
        int setOfFreebies =  castLongToIntSafely(getNumberFreebiesPurchased(purchasedItems) / numberOfFreebies) ;
        int numberOfDiscountedItemsPurchased = castLongToIntSafely(getNumberOccurrence(purchasedItems));
        int numberOfTimesQualified = numberOfDiscountedItemsPurchased / castLongToIntSafely(numberItemNeeded);

        return setOfFreebies * numberOfFreebies  * numberOfTimesQualified * freebieItem.getPrice();

    }

    private int castLongToIntSafely(long longNumber) {
        if (longNumber < Integer.MIN_VALUE || longNumber > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (longNumber + " cannot be cast to int without changing its value.");
        }
        return (int) longNumber;
    }

    private long getNumberFreebiesPurchased(Multiset<Item> purchasedItems) {
        return  purchasedItems.stream().filter( i -> i.equals(freebieItem)).count();
    }


}
