package cells;

public class ImmutableCell<T> implements Cell<T> {

    private T value;

    public ImmutableCell(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    @Override
    public void set(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean isSet() {
        return value != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImmutableCell)) {
            return false;
        }
        ImmutableCell<T> imm = (ImmutableCell<T>) obj;
        return imm.get().equals(get()) && imm.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
