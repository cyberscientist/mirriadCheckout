package discount;

import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Set;

public class NItemsOfXGetsKItemsYFree extends TwoForOneDiscount {
    private Item freebieItem;
    private int numberOfFreebies;

    //FIXME maybe a builderMethod here
    public NItemsOfXGetsKItemsYFree(Set<Item> itemsForDiscount, Item freebieItem, int numberOfFreebies) {
        super(itemsForDiscount);
        this.freebieItem = freebieItem;
        this.numberOfFreebies = numberOfFreebies;
    }

    @Override
    public  boolean isValidForDiscount(List<Item> purchasedItems) {
        Validate.notNull(purchasedItems);

        return getNumberOccurrence(purchasedItems) >= numberItemNeeded && freebieItem != null && getNumberFreebiesPurchased(purchasedItems) >= numberOfFreebies;

    }

    @Override
    public int getAmountToDeduct(List<Item> purchasedItems) throws notValidForDiscountException {
        if (!isValidForDiscount(purchasedItems)) {
            throw new notValidForDiscountException("Not Valid For NItemsOfXGetsKItemsYFree Discount");
        }

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
