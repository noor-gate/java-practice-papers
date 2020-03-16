package cells;

import java.util.ArrayList;
import java.util.Comparator;

public class BackedUpCellComparator<U> implements Comparator<BackedUpCell<U>> {

    private Comparator<U> valueComparator;

    public BackedUpCellComparator(Comparator<U> valueComparator) {
        this.valueComparator = valueComparator;
    }

    @Override
    public int compare(BackedUpCell<U> a, BackedUpCell<U> b) {
        if (!a.isSet() && b.isSet()) {
            return -1;
        }
        if (!b.isSet() && a.isSet()) {
            return 1;
        }
        if (!a.isSet() && !b.isSet()) {
            return 0;
        }
        U aVal = a.get();
        U bVal = b.get();
        ArrayList<U> aPrev = new ArrayList<>();
        ArrayList<U> bPrev = new ArrayList<>();
        while(valueComparator.compare(aVal, bVal) == 0) {
            if (a.hasBackup() && b.hasBackup()) {
                aPrev.add(aVal);
                a.revertToPrevious();
                bPrev.add(bVal);
                b.revertToPrevious();
                aVal = a.get();
                bVal = b.get();
            } else {
                break;
            }
        }
        int ret = 0;
        if (!a.hasBackup()) {
            ret = -1;
        }
        if (!b.hasBackup()) {
            ret = 1;
        }
        if (!a.hasBackup() && !b.hasBackup()) {
            ret = 0;
        }
        if (a.hasBackup() && b.hasBackup()) {
            ret = valueComparator.compare(aVal, bVal);
        }
        restore(a, b, aPrev, bPrev);
        return ret;

    }

    public void restore(BackedUpCell<U> a, BackedUpCell<U> b, ArrayList<U> aPrev, ArrayList<U> bPrev) {
        for (int i = aPrev.size() - 1; i >= 0; i--) {
            a.set(aPrev.get(i));
        }

        for (int i = bPrev.size() - 1; i >= 0; i--) {
            b.set(bPrev.get(i));
        }
    }
}
