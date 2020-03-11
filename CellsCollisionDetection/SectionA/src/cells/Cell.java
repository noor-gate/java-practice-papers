package cells;

public interface Cell<T> {

  void set(T value);

  T get();

  boolean isSet();

}
