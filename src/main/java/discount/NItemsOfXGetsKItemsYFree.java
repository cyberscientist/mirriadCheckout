package discount;

import item.Item;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Set;

public class NItemsOfXGetsKItemsYFree extends TwoForOneDiscount {
    private Item freebieItem;
    private int numberOfFreebies;

    //FIXME maybe a builderMethod here
    public NItemsOfXGetsKItemsYFree(Set<Item> itemsForDiscount, List<Item> purchasedItems, long numberItemNeeded, Item freebieItem, int numberOfFreebies) {
        super(itemsForDiscount, purchasedItems, numberItemNeeded);
        this.freebieItem = freebieItem;
        this.numberOfFreebies = numberOfFreebies;
    }

    @Override
    public  boolean isValidForDiscount() {
        Validate.notNull(purchasedItems);

        if (getNumberOccurrence(purchasedItems) >= numberItemNeeded && freebieItem !=null && getNumberFreebiesPurchased() >= numberOfFreebies ) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int getAmountToDeduct() throws notValidForDiscountException {
        if (!isValidForDiscount()) {
            throw new notValidForDiscountException("Not Valid For NItemsOfXGetsKItemsYFree Discount");
        }
        //FIXME long to int --- make sure its  safe casting
        int setOfFreebies =  castLongToIntSafely(getNumberFreebiesPurchased() / numberOfFreebies) ;
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

    private long getNumberFreebiesPurchased() {
        return  purchasedItems.stream().filter( i -> i.equals(freebieItem)).count();
    }
}
