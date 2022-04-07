package budget;

import java.util.Comparator;

public class PurchaseComparator  implements Comparator<Purchase> {

    @Override
    public int compare(Purchase o1, Purchase o2) {
        int ret = Double.compare(o2.getPrice(), o1.getPrice());
        if (ret == 0) {
            ret = o2.getName().compareToIgnoreCase(o1.getName());
        }
        return ret;
    }
}
