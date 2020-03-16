package collections;

import collections.exceptions.InvalidWordException;
import java.util.List;

public interface CompactWordsSet {

  static void checkIfWordIsValid(String word) throws InvalidWordException {
    // TO BE IMPLEMENTED
  }

  boolean add(String word) throws InvalidWordException;

  boolean remove(String word) throws InvalidWordException;

  boolean contains(String word) throws InvalidWordException;

  int size();

  List<String> uniqueWordsInAlphabeticOrder();

}
