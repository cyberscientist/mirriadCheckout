package discount;

import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Map;

public class NItemsOfXGetsKItemsYFree extends TwoForOneDiscount {
    Item freebieItem;
    int numberOfFreebies;

    //FIXME maybe a builderMethod here
    public NItemsOfXGetsKItemsYFree(Map<String, Map<Item, Integer>> itemsForDiscount) {
        super(itemsForDiscount.get("DISCOUNTED-ITEMS"));
        this.freebieItem = itemsForDiscount.get("FREEBIE").keySet().stream().findFirst().get();
        this.numberOfFreebies = itemsForDiscount.get("FREEBIE").values().stream().findFirst().get();
    }

    @Override
    public  boolean isValidForDiscount(List<Item> purchasedItems) {
        Validate.notNull(purchasedItems);
        return getNumberOccurrence(purchasedItems) >= numberItemNeeded && freebieItem != null && getNumberFreebiesPurchased(purchasedItems) >= numberOfFreebies;
    }

    @Override
    public int getAmountToDeduct(List<Item> purchasedItems) {
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

    private long getNumberFreebiesPurchased(List<Item> purchasedItems) {
        return  purchasedItems.stream().filter( i -> i.equals(freebieItem)).count();
    }


}
