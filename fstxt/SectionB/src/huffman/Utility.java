package huffman;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utility {

  public static List<String> getWords(String filePath) {
    List<String> words = null;
    try (Stream<String> linesStream = Files.lines(Paths.get(filePath))) {
      words = linesStream.flatMap(line -> Arrays.stream(line.split(" "))).map(word -> word.trim())
          .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return words;
  }

  public static String sequenceOfBitsAsNumber(String binaryEncoding) {
    final String binaryEncodingWithHeading1 =
        "1" + binaryEncoding; // Prepending 1 not to lose heading zeroes
    BigInteger result = new BigInteger(binaryEncodingWithHeading1, 2);
    return result.toString();
  }

  public static String numberAsSequenceOfBits(String numberRepresentation) {
    BigInteger number = new BigInteger(numberRepresentation);
    String binaryRepresentation = number.toString(2);
    return binaryRepresentation.substring(1); // Removing previously prepended 1
  }

  public static long totalLength(List<String> words) {
    long length = words.size() - 1; // White spaces
    length += words.stream().mapToLong(w -> w.length()).sum();
    return length;
  }

  public static Map<String, Integer> countWords(List<String> words) {

    List<String> w1 = new ArrayList<>();
    List<String> w2 = new ArrayList<>();
    List<String> w3 = new ArrayList<>();

    for (int i = 0; i < words.size(); i++) {
      if (i % 3 == 0) {
        System.out.println("1: " + words.get(i));
        w1.add(words.get(i));
      } else if (i % 3 == 1) {
        System.out.println("2: " + words.get(i));
        w2.add(words.get(i));
      } else {
        System.out.println("3: " + words.get(i));
        w3.add(words.get(i));
      }
    }

    Map<String, Integer> wordCount = new ConcurrentHashMap<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    Thread t1 = new WordThread(w1, wordCount, lock);
    Thread t2 = new WordThread(w2, wordCount, lock);
    Thread t3 = new WordThread(w3, wordCount, lock);

    t1.start();
    t2.start();
    t3.start();

    try {
      t1.join();
      t2.join();
      t3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return wordCount;
  }

  private static class WordThread extends Thread {

    private List<String> words;
    private Map<String, Integer> wordCount;
    private ReentrantReadWriteLock lock;

    public WordThread(List<String> words, Map<String, Integer> wordCount, ReentrantReadWriteLock lock) {
      this.words = words;
      this.wordCount = wordCount;
      this.lock = lock;
    }

    @Override
    public void run() {
      Map <String, Integer> newMap = words.stream().collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));
      /*
       for (String k : newMap.keySet()) {
        if (wordCount.containsKey(k)) {
          wordCount.put(k, wordCount.get(k) + 1);
        } else {
          wordCount.put(k, newMap.get(k));
        }
      }*/

      for (String k : newMap.keySet()) {
        boolean contains;
        lock.readLock().lock();
        try {
          contains = wordCount.containsKey(k);
        } finally {
          lock.readLock().unlock();
        }

        lock.writeLock().lock();
        if (contains) {
          try {
            wordCount.put(k, wordCount.get(k) + 1);
          } finally {
            lock.writeLock().unlock();
          }
        } else {
          try {
            wordCount.put(k, newMap.get(k));
          } finally {
            lock.writeLock().unlock();
          }
        }
        }
    }

  }

}
