import static org.junit.Assert.assertEquals;

import collections.CompactWordsSet;
import collections.SimpleCompactWordTree;
import collections.exceptions.InvalidWordException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;
import org.junit.Test;

public class StressTest {

  private static final int NUM_TEST_STRINGS = 100000;
  private static final int MAX_STRING_LENGTH = 10;
  private static final int NUM_THREADS = 8;

  private static final ArrayList<String> manyValidStrings =
      generateRandomStrings(NUM_TEST_STRINGS, MAX_STRING_LENGTH);
  private static final Set<String> uniqueTestWords = new HashSet<>(manyValidStrings);

  {
    // Check consistency of static configuration parameters
    assert NUM_TEST_STRINGS > 0;
    assert MAX_STRING_LENGTH > 0;
    assert NUM_THREADS > 0 && NUM_THREADS <= NUM_TEST_STRINGS;
  }

  private static String randomStringOfGivenLength(int maxStringLength, Random random) {
    final int randomLength = random.nextInt(maxStringLength) + 1;
    final char buffer[] = new char[randomLength];
    for (int i = 0; i < buffer.length; i++) {
      buffer[i] = (char) (random.nextInt('z' - 'a' + 1) + 'a');
    }
    return new String(buffer);
  }

  private static ArrayList<String> generateRandomStrings(int numTestStrings, int maxStringLength) {
    Random random = new Random(123);
    ArrayList<String> result = new ArrayList<>(numTestStrings);

    IntStream.range(0, numTestStrings).forEach(w -> {
      result.add(w, randomStringOfGivenLength(maxStringLength, random));
    });

    return result;
  }

  @Test
  public void stressTestWithManyOperations() {
    // This test *may* help spotting race conditions
    final CompactWordsSet wordsSet = new SimpleCompactWordTree();
    final Thread[] threads = new Thread[NUM_THREADS];

    final long startTime = System.currentTimeMillis();

    Arrays.parallelSetAll(threads, t -> {
      Thread newThread = new Thread(() -> {
        List<String> wordsToBeInsertedByThreadI = new ArrayList<>(manyValidStrings);
        Collections.shuffle(wordsToBeInsertedByThreadI);

        wordsToBeInsertedByThreadI.forEach(w -> {
          try {
            wordsSet.add(w);
            wordsSet.contains(w);
            wordsSet.remove(w);
            wordsSet.add(w);
            wordsSet.contains(w);
          } catch (InvalidWordException e) {
            throw new RuntimeException(e);
          }
        });
      });

      newThread.start();
      return newThread;
    });

    Arrays.stream(threads).forEach(t -> {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    final long endTime = System.currentTimeMillis();

    checkResult(wordsSet);

    System.out.println("Stress test completed in " + (endTime - startTime) + " ms");

  }

  @Test
  public void performanceTest() {
    // You can use this method to see how the performance change with the number of threads.
    // Rememeber that too many threads may increase contention and, in turn, slow down performance
    IntStream.range(1, NUM_THREADS + 1).forEach(nt ->
        performanceTestWithNThreads(nt)
    );
  }

  private void performanceTestWithNThreads(int numThreads) {
    final CompactWordsSet wordsSet = new SimpleCompactWordTree();
    final Thread[] threads = new Thread[numThreads];

    final long startTime = System.currentTimeMillis();

    Arrays.parallelSetAll(threads, t -> {
      Thread newThread = new Thread(() -> {
        final int segmentLength = manyValidStrings.size() / numThreads;
        final int indexFromInclusive = t * (segmentLength + 1);
        final int indexToExclusive = Math
            .min((t + 1) * (segmentLength + 1), manyValidStrings.size());

        IntStream.range(indexFromInclusive, indexToExclusive).forEach(i -> {
          try {
            wordsSet.add(manyValidStrings.get(i));
            wordsSet.contains(manyValidStrings.get(i));
            wordsSet.remove(manyValidStrings.get(i));
            wordsSet.add(manyValidStrings.get(i));
          } catch (InvalidWordException e) {
            throw new RuntimeException(e);
          }
        });
      });

      newThread.start();
      return newThread;
    });

    Arrays.stream(threads).forEach(t -> {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    final long endTime = System.currentTimeMillis();

    checkResult(wordsSet);

    System.out.println(
        "Operation with " + numThreads + " threads completed in " + (endTime - startTime) + " ms");
  }

  private void checkResult(CompactWordsSet wordsSet) {
    List<String> uniqueCollectedWords = wordsSet.uniqueWordsInAlphabeticOrder();
    Set<String> uniqueCollectedWordsAsSet = new HashSet<>(uniqueCollectedWords);

    assertEquals("The CompactWordSet contains duplicates", uniqueCollectedWords.size(),
        uniqueCollectedWordsAsSet.size());
    assertEquals("The CompactWordSet contains an unexpected set of words", uniqueTestWords,
        uniqueCollectedWordsAsSet);
  }

}
