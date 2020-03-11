public interface ListInterface<T> {


  /**
   * Returns true if the list is empty, otherwise returns false
   */
  public boolean isEmpty();

  /**
   * Returns the number of elements in the list.
   */
  public int size();

  /**
   * Returns the element in the list at the given position
   *
   * @param givenPosition the position in the list for which a element is required
   * @throws an exception if position is less than 1 or greater that the size of the list
   */
  public T get(int givenPosition) throws ListIndexOutOfBoundsException;

  /**
   * Adds the given new elment at the given position.
   *
   * @param newItem the new element to add to the list
   * @param givenPosition the position in the list where the new element has to be added
   * @throws an exception if position is less than 1 or greater than the size+1 of the list
   */
  public void add(int givenPosition, T newItem) throws ListIndexOutOfBoundsException;

  /**
   * Removes the element in the list at a given position.
   *
   * @param givenPosition the position in the list where the element to be removed is
   * @throws an exception if position is less than 1 or greater that the size of the list
   */
  public void remove(int givenPosition) throws ListIndexOutOfBoundsException;

  /**
   * Returns trues is the given element is in the list. False otherwise
   *
   * @param item the element to search for in the list
   */
  public boolean contains(T item);
}
