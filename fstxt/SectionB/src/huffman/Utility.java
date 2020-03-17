package huffman;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
    //TODO replace the current sequenctial implementation with a concurrent one (Q4)
    return words.stream().collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));
  }
}
