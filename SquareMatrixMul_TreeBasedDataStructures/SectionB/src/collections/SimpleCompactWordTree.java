package collections;

import collections.exceptions.InvalidWordException;

import java.util.ArrayList;
import java.util.List;

public class SimpleCompactWordTree implements CompactWordsSet {

  private boolean isWord = false;
  private SimpleCompactWordTree[] children = new SimpleCompactWordTree[26];
  private int size;
  private List<String> words = new ArrayList<>();

  @Override
  public synchronized boolean add(String word) throws InvalidWordException {
    if (word.length() == 0) {
      boolean changed = !isWord;
      isWord = true;
      size = changed ? size++ : size;
      return changed;
    }
    CompactWordsSet.checkIfWordIsValid(word);
    int firstChar = word.charAt(0);
    if (children[firstChar - 'a'] == null) {
      children[firstChar - 'a'] = new SimpleCompactWordTree();
    }
    boolean gotChanged = children[firstChar - 'a'].add(word.substring(1));
    if (gotChanged) {
      size++;
    }
    return gotChanged;
  }

  @Override
  public synchronized boolean remove(String word) throws InvalidWordException {
    if (word.length() == 0) {
      boolean changed = isWord;
      isWord = false;
      size = changed ? size-- : size;
      return changed;
    }
    CompactWordsSet.checkIfWordIsValid(word);
    int firstChar = word.charAt(0);
    if (children[firstChar - 'a'] == null) {
      return false;
    }
    boolean gotChanged = children[firstChar - 'a'].remove(word.substring(1));
    if (gotChanged) {
      size--;
    }
    return gotChanged;
  }

  @Override
  public synchronized boolean contains(String word) throws InvalidWordException {
    if (word.length() == 0) {
      return isWord;
    }
    CompactWordsSet.checkIfWordIsValid(word);
    int firstChar = word.charAt(0);
    if (children[firstChar - 'a'] == null) {
      return false;
    }
    return children[firstChar - 'a'].contains(word.substring(1));
  }

  @Override
  public synchronized int size() {
    return size;
  }

  @Override
  public synchronized List<String> uniqueWordsInAlphabeticOrder() {
    return uniqueWordsHelper(this, "");
  }

  private synchronized List<String> uniqueWordsHelper(SimpleCompactWordTree tree, String str) {
    for (int i = 0; i < 26; i++) {
      if (tree.children[i] != null) {
        str += ((char) (i + 'a'));
        if (tree.children[i].isWord && !words.contains(str)) {
          System.out.println(str);
          words.add(str);
        }
        uniqueWordsHelper(tree.children[i], str);
        str = str.substring(0, str.length() - 1);
      }
    }
    return words;
  }
}
