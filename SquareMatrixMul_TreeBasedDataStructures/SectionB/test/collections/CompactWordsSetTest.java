package collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import collections.exceptions.InvalidWordException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class CompactWordsSetTest {

  final String validWords[] = {"a", "aa", "aaa", "b", "ab", "aab", "bba"};
  final String validWordsShuffled[] = {"aab", "bba", "ab", "aaa", "b", "a", "aa"};

  {
    {
      // checking the consistency between validWords and validWordsShuffled. If violated, the
      // entire test suite fails
      assert validWords.length == validWordsShuffled.length;
      assert Arrays.stream(validWords).collect(Collectors.toSet()).equals(
          Arrays.stream(validWordsShuffled).collect(Collectors.toSet())
      );
    }
  }


  @Test
  public void checkIfWordIsValidValidWords() {
    try {
      CompactWordsSet.checkIfWordIsValid("a");
    } catch (InvalidWordException e) {
      fail("Word \"a\" should be valid");
    }
    try {
      CompactWordsSet.checkIfWordIsValid("z");
    } catch (InvalidWordException e) {
      fail("Word \"z\" should be valid");
    }
    try {
      CompactWordsSet.checkIfWordIsValid("abc");
    } catch (InvalidWordException e) {
      fail("Word \"abc\" should be valid");
    }
    try {
      CompactWordsSet.checkIfWordIsValid("java");
    } catch (InvalidWordException e) {
      fail("Word \"java\" should be valid");
    }
  }

  @Test(expected = InvalidWordException.class)
  public void anEmptyWordIsNotValid() throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid("");
  }

  @Test(expected = InvalidWordException.class)
  public void aNullWordIsNotValid() throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(null);
  }

  @Test(expected = InvalidWordException.class)
  public void onlyLowercaseEnglishAlphabetAllowed_capitalLetters() throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid("abC");
  }

  @Test(expected = InvalidWordException.class)
  public void onlyLowercaseEnglishAlphabetAllowed_specialSymbols() throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid("-true+");
  }

  @Test(expected = InvalidWordException.class)
  public void onlyLowercaseEnglishAlphabetAllowed_numbers() throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid("42");
  }

  @Test(expected = InvalidWordException.class)
  public void addingInvalidWordsThrowsExceptions() throws InvalidWordException {
    CompactWordsSet wordsSet = new SimpleCompactWordTree();
    wordsSet.add("abC");
  }

  @Test
  public void addedWordsAreContainedInTheSet() throws InvalidWordException {
    CompactWordsSet wordsSet = new SimpleCompactWordTree();

    Arrays.stream(validWords).forEach(w -> {
      try {
        boolean theCollectionChanged = wordsSet.add(w);
        Assert.assertTrue("Add should return true when adding the new word: " + w,
            theCollectionChanged);
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for a valid word: " + w);
      }
    });

    Arrays.stream(validWordsShuffled).forEach(w -> {
      try {
        boolean wIsInTheSet = wordsSet.contains(w);
        Assert.assertTrue("Word " + w + " should be in the set", wIsInTheSet);
      } catch (InvalidWordException e) {
        fail("Contains thrown an exception for the valid word: " + w);
      }
    });
  }

  @Test
  public void addDoesNotAllowDuplicates() throws InvalidWordException {
    CompactWordsSet wordsSet = new SimpleCompactWordTree();

    Arrays.stream(validWords).forEach(w -> {
      try {
        boolean theCollectionChanged = wordsSet.add(w);
        Assert.assertTrue("Add should return true when adding the new word: " + w,
            theCollectionChanged);
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for a valid word: " + w);
      }
    });

    Arrays.stream(validWords).forEach(w -> {
      try {
        boolean theCollectionChanged = wordsSet.add(w);
        Assert.assertFalse("Add should return false when adding for the second time the word: " + w,
            theCollectionChanged);
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for the valid word: " + w);
      }
    });
  }

  @Test(expected = InvalidWordException.class)
  public void removingInvalidWordsThrowsExceptions() throws InvalidWordException {
    CompactWordsSet wordsSet = new SimpleCompactWordTree();
    wordsSet.add("");
  }

  @Test
  public void removedWordsAreNotContainedInTheSet() throws InvalidWordException {
    CompactWordsSet wordsSet = new SimpleCompactWordTree();

    Arrays.stream(validWords).forEach(w -> {
      try {
        wordsSet.add(w);
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for a valid word: " + w);
      }
    });

    Arrays.stream(validWordsShuffled).forEach(w -> {
      try {
        boolean theCollectionChanged = wordsSet.remove(w);
        Assert.assertTrue("Remove should return true when removing the word: " + w,
            theCollectionChanged);
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for the valid word: " + w);
      }
    });
  }

  @Test
  public void removingWordsNotInTheSetDoesNotChangeTheCollection() throws InvalidWordException {
    CompactWordsSet wordsSet = new SimpleCompactWordTree();

    Arrays.stream(validWordsShuffled).forEach(w -> {
      try {
        boolean theCollectionChanged = wordsSet.remove(w);
        Assert.assertFalse("Remove should return false when removing the word: " + w,
            theCollectionChanged);
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for the valid word: " + w);
      }
    });

    Arrays.stream(validWords).forEach(w -> {
      try {
        wordsSet.add(w);
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for a valid word: " + w);
      }
    });

    boolean theCollectionChanged = wordsSet.remove("zzz");
    Assert.assertFalse("Remove should return false when removing the word: zzz",
        theCollectionChanged);
  }

  @Test
  public void aNewlyCreatedSetHasSize0() {
    CompactWordsSet wordsSet = new SimpleCompactWordTree();
    assertEquals("A newly created set should have size 0", 0, wordsSet.size());
  }

  @Test
  public void addAndRemoveChangeSizeConsistently() {
    CompactWordsSet wordsSet = new SimpleCompactWordTree();
    assertEquals("A newly created set should have size 0", 0, wordsSet.size());

    Arrays.stream(validWords).forEach(w -> {
      try {
        int previousSize = wordsSet.size();
        wordsSet.add(w);
        assertEquals("Wrong size after adding word: " + w, previousSize + 1, wordsSet.size());
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for a valid word: " + w);
      }
    });

    Arrays.stream(validWordsShuffled).forEach(w -> {
      try {
        int previousSize = wordsSet.size();
        wordsSet.remove(w);
        assertEquals("Wrong size after removing word: " + w, previousSize - 1, wordsSet.size());
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for a valid word: " + w);
      }
    });
  }

  @Test
  public void uniqueWordsInAlphabeticOrder() {
    CompactWordsSet wordsSet = new SimpleCompactWordTree();

    Arrays.stream(validWordsShuffled).forEach(w -> {
      try {
        wordsSet.add(w);
        wordsSet.add(w); //second add should have no effect
      } catch (InvalidWordException e) {
        fail("Add thrown an exception for a valid word: " + w);
      }
    });

    assertEquals("Inconsistent size after adding all the test words.", validWordsShuffled.length,
        wordsSet.size());

    List<String> uniqueWords = wordsSet.uniqueWordsInAlphabeticOrder();
    Set<String> uniqueWordsAsSet = new HashSet<String>(uniqueWords);
    assertEquals("Not all the words are unique.", uniqueWords.size(), uniqueWordsAsSet.size());

    Set<String> validWordsAsSet = Arrays.stream(validWordsShuffled).collect(Collectors.toSet());
    assertEquals("Not all the added words are in the list.", uniqueWordsAsSet, validWordsAsSet);

    List<String> orderedWords = new ArrayList<>(validWordsAsSet);
    Collections.sort(orderedWords);
    assertEquals("The words are not in alphabetic order.", orderedWords, uniqueWords);

    try {
      wordsSet.remove(validWordsShuffled[0]);
    } catch (InvalidWordException e) {
      fail("Remove thrown an exception for a valid word: " + validWordsShuffled[0]);
    }
    assertEquals("Inconsistent size after removing word: " + validWordsShuffled[0],
        validWordsShuffled.length - 1, wordsSet.size());

    uniqueWords = wordsSet.uniqueWordsInAlphabeticOrder();
    uniqueWordsAsSet = new HashSet<String>(uniqueWords);

    assertEquals("All the words are unique.", uniqueWords.size(), uniqueWordsAsSet.size());

    validWordsAsSet.remove(validWordsShuffled[0]);
    assertEquals("All the added words are in the list and the removed one is not.",
        uniqueWordsAsSet, validWordsAsSet);
  }
}
