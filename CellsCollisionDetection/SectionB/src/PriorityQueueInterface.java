public interface PriorityQueueInterface<T extends Comparable<T>> extends Iterable<Object> {

  /**
   * Adds a new entry to the priority queue according to the priority value.
   *
   * @param newEntry the new element to add to the priority queue
   * @throws an exception if the priority queue is full
   */
  public void add(T newEntry) throws PQException;


  /**
   * Returns the element with highest priority, or returns null if the priority.
   * queue is empty. The priority queue is left unchanged
   */
  public T peek();


  /**
   * Removes the element with highest priority.
   */
  public void remove();


  /**
   * Returns true if the priority queue is empty. False otherwise.
   */
  public boolean isEmpty();


  /**
   * Returns the size of the priority queue.
   */
  public int getSize();


  /**
   * Returns a priority queue that is a clone of the current priority queue.
   */
  public PriorityQueue<T> clone();
}
