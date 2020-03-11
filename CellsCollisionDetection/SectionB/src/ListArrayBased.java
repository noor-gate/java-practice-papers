public class ListArrayBased<T> implements ListInterface<T> {

  private int MAX_LIST = 512;
  private T[] list;
  private int length;

  public ListArrayBased() {
    length = 0;
    list = (T[]) new Object[MAX_LIST];
  }

  /**
   * Returns true if the list is empty, otherwise returns false
   */
  public boolean isEmpty() {
    return (length == 0);
  }

  /**
   * Returns the number of elements in the list.
   */
  public int size() {
    return length;
  }

  /**
   * Returns the element in the list at the given position
   *
   * @param givenPosition the position in the list for which a element is required
   * @throws an exception if position is less than 1 or greater that the size of the list
   */
  public T get(int givenPosition) throws ListIndexOutOfBoundsException {
    if (givenPosition >= 1 && givenPosition <= length) {
      return list[translate(givenPosition)];
    } else {
      throw new ListIndexOutOfBoundsException("Position out of range");
    }
  }

  /**
   * Adds the given new element at the given position.
   *
   * @param newItem the new element to add to the list
   * @param givenPosition the position in the list where the new element has to be added
   * @throws an exception if position is less than 1 or greater that the size+1 of the list
   */
  public void add(int givenPosition, T newItem)
      throws ListIndexOutOfBoundsException {
    if (length == MAX_LIST) {
      T[] oldList = list;
      MAX_LIST = MAX_LIST * 2;
      list = (T[]) new Object[MAX_LIST];
      for (int i = 0; i < length; i++) {
        list[i] = oldList[i];
      }
    }
    if (givenPosition >= 1 && givenPosition <= length + 1) {
      makeRoom(givenPosition);
      list[translate(givenPosition)] = newItem;
      length++;
    } else {
      throw new ListIndexOutOfBoundsException("Position out of range");
    }
  }

  /**
   * Removes the element in the list at a given position.
   *
   * @param givenPosition the position in the list where the element to be removed is
   * @throws an exception if position is less than 1 or greater that the size of the list
   */
  public void remove(int givenPosition) throws ListIndexOutOfBoundsException {
    if (givenPosition >= 1 && givenPosition <= length) {
      if (givenPosition < length) {
        removeGap(givenPosition);
      }
      length--;
    } else {
      throw new ListIndexOutOfBoundsException("Position out of range");
    }
  }

  private void removeGap(int givenPosition) {
    for (int pos = givenPosition + 1; pos <= length; pos++) { // shift left
      // items at
      // position
      // >
      // givenPosition
      list[translate(pos - 1)] = list[translate(pos)];
    }
  }


  private void makeRoom(int givenPosition) {
    for (int pos = length; pos >= givenPosition; pos--) // shift right items
    // at position >=
    // givenPosition
    {
      list[translate(pos + 1)] = list[translate(pos)];
    } // end for
  }

  private int translate(int givenPosition) {
    return givenPosition - 1;
  }

  @Override
  /**
   * Returns trues is the given element is in the list. False otherwise
   *
   * @param item
   *     the element to search for in the list
   */
  public boolean contains(T item) {
    for (int i = 0; i < length; i++) {
      if (list[i].equals(item)) {
        return true;
      }
    }
    return false;
  }
}
