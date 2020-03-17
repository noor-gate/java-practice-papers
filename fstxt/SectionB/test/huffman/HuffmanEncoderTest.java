package huffman;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class HuffmanEncoderTest {

  @Test(expected = HuffmanEncoderException.class)
  public void thereShouldBeAtLeast2differentwords1() {
    final List<String> words = Collections.emptyList();

    Map<String, Integer> wordCounts = Utility.countWords(words);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);
  }

  @Test(expected = HuffmanEncoderException.class)
  public void thereShouldBeAtLeast2differentwords2() {
    final List<String> words = Arrays.asList("a");

    Map<String, Integer> wordCounts = Utility.countWords(words);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);
  }

  @Test(expected = HuffmanEncoderException.class)
  public void thereShouldBeAtLeast2differentwords3() {
    final List<String> words = Arrays.asList("a", "a");

    Map<String, Integer> wordCounts = Utility.countWords(words);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);
  }

  @Test(expected = HuffmanEncoderException.class)
  public void wordCountsCannotBeNull() {
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(null);
  }

  @Test
  public void twoWordsSameCounts() {
    final List<String> words = Arrays
        .asList("a", "b");

    Map<String, Integer> wordCounts = Utility.countWords(words);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);

    final String compressed = huffmanEncoder.compress(words);
    assertEquals("01", compressed);

    final List<String> decompressed = huffmanEncoder.decompress(compressed);
    assertEquals(words, decompressed);

    final List<String> newTextSameWords = Arrays
        .asList("a", "b", "a", "b");

    final String newTextSameWordsCompressed = huffmanEncoder.compress(newTextSameWords);
    assertEquals("0101", newTextSameWordsCompressed);

    final List<String> newTextSameWordsDecompressed = huffmanEncoder
        .decompress(newTextSameWordsCompressed);
    assertEquals(newTextSameWords, newTextSameWordsDecompressed);
  }

  @Test
  public void threeWordsDifferentCounts() {
    final List<String> words = Arrays
        .asList("shakespeare", "dickens", "tolkien", "shakespeare", "shakespeare", "dickens");

    Map<String, Integer> wordCounts = Utility.countWords(words);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);

    final String compressed = huffmanEncoder.compress(words);
    assertEquals("011100011", compressed);

    final List<String> decompressed = huffmanEncoder.decompress(compressed);
    assertEquals(words, decompressed);

    final String shakespeareCompressed = huffmanEncoder.compress(Arrays.asList("shakespeare"));
    assertEquals("0", shakespeareCompressed);

    final String dickensCompressed = huffmanEncoder.compress(Arrays.asList("dickens"));
    assertEquals("11", dickensCompressed);

    final String tolkienCompressed = huffmanEncoder.compress(Arrays.asList("tolkien"));
    assertEquals("10", tolkienCompressed);
  }

  @Test(expected = HuffmanEncoderException.class)
  public void invalidCompressedTextShouldThrowAnException() {
    final List<String> words = Arrays
        .asList("shakespeare", "dickens", "tolkien", "shakespeare", "shakespeare", "dickens");

    Map<String, Integer> wordCounts = Utility.countWords(words);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);

    huffmanEncoder.decompress("01110001");
  }

  @Test(expected = HuffmanEncoderException.class)
  public void compressWithUnknownWordsShouldThrowAnException() {
    final List<String> words = Arrays
        .asList("shakespeare", "dickens", "tolkien", "shakespeare", "shakespeare", "dickens");

    Map<String, Integer> wordCounts = Utility.countWords(words);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);

    huffmanEncoder.compress(Arrays.asList("shakespeare", "hemingway"));
  }

  @Test
  public void RomeoAndJuliet() {
    final List<String> romeoAndJuliet = Arrays
        .asList("romeo", "romeo", "wherefore", "art", "thou", "romeo");

    Map<String, Integer> wordCounts = Utility.countWords(romeoAndJuliet);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);

    final String compressed = huffmanEncoder.compress(romeoAndJuliet);
    assertEquals("00111110100", compressed);

    final List<String> decompressed = huffmanEncoder.decompress(compressed);
    assertEquals(romeoAndJuliet, decompressed);
  }

  @Test
  public void Richard3() {
    final List<String> richard3 = Arrays
        .asList("a", "horse", "a", "horse", "my", "kingdom", "for", "a", "horse");

    Map<String, Integer> wordCounts = Utility.countWords(richard3);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);

    final String compressed = huffmanEncoder.compress(richard3);
    assertEquals("01001011111101110010", compressed);

    final List<String> decompressed = huffmanEncoder.decompress(compressed);
    assertEquals(richard3, decompressed);
  }

  @Test
  public void Hamlet() {
    final List<String> hamlet = Arrays
        .asList("we", "know", "what", "we", "are", "but", "know", "not", "what", "we", "may", "be");

    Map<String, Integer> wordCounts = Utility.countWords(hamlet);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);

    final String compressed = huffmanEncoder.compress(hamlet);
    assertEquals("10111110100000111111010110100110001", compressed);

    final List<String> decompressed = huffmanEncoder.decompress(compressed);
    assertEquals(hamlet, decompressed);
  }


  @Test
  public void MerchantOfVenice() {
    final List<String> merchantOfVenice = Arrays
        .asList("if", "you", "prick", "us", "do", "we", "not", "bleed", "if", "you", "tickle", "us",
            "do", "we", "not", "laugh", "if", "you", "poison", "us", "do", "we", "not", "die",
            "and", "if", "you", "wrong", "us", "shall", "we", "not", "revenge");

    Map<String, Integer> wordCounts = Utility.countWords(merchantOfVenice);
    HuffmanEncoder huffmanEncoder = HuffmanEncoder.buildEncoder(wordCounts);

    final String compressed = huffmanEncoder.compress(merchantOfVenice);
    assertEquals(
        "00111001001000111101110001011001110101000001111011100101110011100100000011110111001011001010001110101010001110101110011100",
        compressed);

    final List<String> decompressed = huffmanEncoder.decompress(compressed);
    assertEquals(merchantOfVenice, decompressed);
  }
}