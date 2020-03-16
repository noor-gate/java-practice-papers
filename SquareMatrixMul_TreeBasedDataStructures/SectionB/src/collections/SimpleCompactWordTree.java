package collections;

import collections.exceptions.InvalidWordException;
import java.util.List;

public class SimpleCompactWordTree implements CompactWordsSet {

  @Override
  public boolean add(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    // TO BE IMPLEMENTED
    return false;
  }

  @Override
  public boolean remove(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    // TO BE IMPLEMENTED
    return false;
  }

  @Override
  public boolean contains(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    // TO BE IMPLEMENTED
    return false;
  }

  @Override
  public int size() {
    // TO BE IMPLEMENTED
    return -1;
  }

  @Override
  public List<String> uniqueWordsInAlphabeticOrder() {
    // TO BE IMPLEMENTED
    return null;
  }
}
