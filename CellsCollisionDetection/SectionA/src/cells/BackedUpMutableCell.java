package cells;

import java.util.ArrayList;

public class BackedUpMutableCell<T> extends MutableCell<T> implements BackedUpCell<T> {

    private ArrayList<T> prevRefs;
    private boolean boundedBackup;
    private int limit;

    public BackedUpMutableCell() {
        value = null;
        prevRefs = new ArrayList<>();
        boundedBackup = false;
    }

    public BackedUpMutableCell(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException();
        }
        value = null;
        prevRefs = new ArrayList<>();
        boundedBackup = true;
        this.limit = limit;
    }

    @Override
    public boolean hasBackup() {
        return prevRefs.size() != 0;
    }

    @Override
    public void revertToPrevious() {
        if (!hasBackup()) {
            throw new UnsupportedOperationException();
        }
        int lastIndex = prevRefs.size() - 1;
        value = prevRefs.get(lastIndex);
        prevRefs.remove(lastIndex);
    }

    @Override
    public void set(T value) {
        if (isSet()) {
            if (boundedBackup && prevRefs.size() == limit && limit > 0) {
                prevRefs.remove(0);
            }
            if (!boundedBackup || limit != 0) {
                prevRefs.add(this.value);
            }
        }
        super.set(value);
    }
}
