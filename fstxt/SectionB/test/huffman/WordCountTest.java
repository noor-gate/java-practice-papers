package huffman;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

public class WordCountTest {

  @Test(expected = NullPointerException.class)
  public void wordCountNull() {
    Utility.countWords(null);
  }

  @Test
  public void wordCountEmptyList() {
    final List<String> words = Collections.emptyList();
    final Map<String, Integer> expectedWordCounts = new HashMap<>();
    assertEquals(expectedWordCounts, Utility.countWords(words));
  }

  @Test
  public void wordCountRomeoAndJuliet() {
    final List<String> romeoAndJuliet = Collections
        .unmodifiableList(Arrays.asList("romeo", "romeo", "wherefore", "art", "thou", "romeo"));
    final Map<String, Integer> expectedWordCounts = romeoAndJuliet.stream()
        .collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));
    assertEquals(expectedWordCounts, Utility.countWords(romeoAndJuliet));
  }

  @Test
  public void wordCountRichard3() {
    final List<String> richard3 = Collections.unmodifiableList(
        Arrays.asList("a", "horse", "a", "horse", "my", "kingdom", "for", "a", "horse"));
    final Map<String, Integer> expectedWordCounts = richard3.stream()
        .collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));
    assertEquals(expectedWordCounts, Utility.countWords(richard3));
  }

  @Test
  public void wordCountHamlet() {
    final List<String> hamlet = Collections.unmodifiableList(
        Arrays.asList("we", "know", "what", "we", "are", "but", "know", "not", "what", "we", "may",
            "be"));
    final Map<String, Integer> expectedWordCounts = hamlet.stream()
        .collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));
    assertEquals(expectedWordCounts, Utility.countWords(hamlet));
  }

  @Test
  public void wordCountMerchantOfVenice() {
    final List<String> merchantOfVenice = Collections.unmodifiableList(
        Arrays.asList("if", "you", "prick", "us", "do", "we", "not", "bleed", "if", "you", "tickle",
            "us", "do", "we", "not", "laugh", "if", "you", "poison", "us", "do", "we", "not", "die",
            "and", "if", "you", "wrong", "us", "shall", "we", "not", "revenge"));
    final Map<String, Integer> expectedWordCounts = merchantOfVenice.stream()
        .collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));
    assertEquals(expectedWordCounts, Utility.countWords(merchantOfVenice));
  }

  @Test
  public void wordCountTempest() {
    final List<String> tempest = Collections.unmodifiableList(
        Arrays
            .asList("full", "fathom", "five", "thy", "father", "lies", "of", "his", "bones", "are",
                "coral", "made", "those", "are", "pearls", "that", "were", "his", "eyes", "nothing",
                "of", "him", "that", "doth", "fade", "but", "doth", "suffer", "a", "sea", "change",
                "into", "something", "rich", "and", "strange"));
    final Map<String, Integer> expectedWordCounts = tempest.stream()
        .collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));
    assertEquals(expectedWordCounts, Utility.countWords(tempest));
  }
}