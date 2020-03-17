package huffman;

import java.util.List;
import java.util.Map;

public class Compressor {

  public static final String ROMEO_AND_JULIET = "./resources/RomeoAndJuliet.clean.txt";

  public static void main(String[] args) {
    List<String> words = Utility.getWords(Compressor.ROMEO_AND_JULIET);

    Map<String, Integer> wordCounts = Utility.countWords(words);

    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);

    String compressed = huffmanEncoder.compress(words);
    List<String> decompressed = huffmanEncoder.decompress(compressed);

    System.out.println("The compression is correct: " + words.equals(decompressed));

    String asNumber = Utility.sequenceOfBitsAsNumber(compressed);
    System.out.println("\nYour text can be represented as the integer number:\n" + asNumber + "\n");

    long initialLength = Utility.totalLength(words);
    long compressedLength = asNumber.length();
    double compressionRate = initialLength * 1d / compressedLength;

    System.out.println("The initial length is: " + initialLength);
    System.out.println("The final length is:   " + compressedLength);
    System.out.println("Compression ratio:     " + compressionRate * 100 + " times");
  }

}
