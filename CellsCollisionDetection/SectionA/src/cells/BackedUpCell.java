package cells;

public interface BackedUpCell<T> extends Cell<T> {

  boolean hasBackup();

  void revertToPrevious();

}
