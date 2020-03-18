package huffman;

import java.util.*;

public class HuffmanEncoder {

  final HuffmanNode root;
  final Map<String, String> word2bitsequence;

  private HuffmanEncoder(HuffmanNode root,
      Map<String, String> word2bitSequence) {
    this.root = root;
    this.word2bitsequence = word2bitSequence;
  }

  public static HuffmanEncoder buildEncoder(Map<String, Integer> wordCounts) {
    //TODO: complete the implementation of this method (Q1)

    if (wordCounts == null) {
      throw new HuffmanEncoderException("wordCounts cannot be null");
    }
    if (wordCounts.size() < 2) {
      throw new HuffmanEncoderException("This encoder requires at least two different words");
    }

    // fixing the order in which words will be processed: this determinize the execution and makes
    // tests reproducible.
    TreeMap<String, Integer> sortedWords = new TreeMap<String,Integer>(wordCounts);
    PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(sortedWords.size());

    for (String s : sortedWords.keySet()) {
      queue.offer(new HuffmanLeaf(sortedWords.get(s), s));
    }

    while (queue.size() > 1) {
      HuffmanNode left = queue.poll();
      HuffmanNode right = queue.poll();
      queue.offer(new HuffmanInternalNode(left, right));
    }

    HuffmanNode root = queue.peek();

    Map<String, String> word2bitSequence = traverse(root, "", new TreeMap<>());


    return new HuffmanEncoder(root, word2bitSequence);
  }

  private static Map<String, String> traverse(HuffmanNode root, String bit, Map<String, String> word2bitsequence) {
    if (root instanceof HuffmanLeaf) {
      word2bitsequence.put(((HuffmanLeaf) root).word, bit);
    } else {
      word2bitsequence = traverse(((HuffmanInternalNode) root).getLeft(), bit += "0", word2bitsequence);
      word2bitsequence = traverse(((HuffmanInternalNode) root).getRight(), bit.substring(0, bit.length() - 1) + "1", word2bitsequence);
    }
    return word2bitsequence;
  }


  public String compress(List<String> text) {
    assert text != null && text.size() > 0;

    StringBuilder sb = new StringBuilder();

    for (String t : text) {
      if (!word2bitsequence.containsKey(t)) {
        throw new HuffmanEncoderException();
      }
      sb.append(word2bitsequence.get(t));
    }
    return sb.toString();
  }


  public List<String> decompress(String compressedText) {
    assert compressedText != null && compressedText.length() > 0;
    return decompressHelper(compressedText, root, new ArrayList<>());
  }

  private List<String> decompressHelper(String compressedText, HuffmanNode root, List<String> words) {
    if (root instanceof HuffmanLeaf) {
      words.add(((HuffmanLeaf) root).word);
      if (!compressedText.isEmpty()) {
        decompressHelper(compressedText, this.root, words);
      }
    } else {
      if (compressedText.isEmpty()) {
        throw new HuffmanEncoderException();
      }
      if (compressedText.charAt(0) == '0') {
        decompressHelper(compressedText.substring(1), ((HuffmanInternalNode) root).getLeft(), words);
      } else {
        decompressHelper(compressedText.substring(1), ((HuffmanInternalNode) root).getRight(), words);
      }
    }
    return words;
  }

  // Below the classes representing the tree's nodes. There should be no need to modify them, but
  // feel free to do it if you see it fit

  private static abstract class HuffmanNode implements Comparable<HuffmanNode> {

    private final int count;

    public HuffmanNode(int count) {
      this.count = count;
    }

    @Override
    public int compareTo(HuffmanNode otherNode) {
      return count - otherNode.count;
    }
  }


  private static class HuffmanLeaf extends HuffmanNode {

    private final String word;

    public HuffmanLeaf(int frequency, String word) {
      super(frequency);
      this.word = word;
    }
  }


  private static class HuffmanInternalNode extends HuffmanNode {

    private final HuffmanNode left;
    private final HuffmanNode right;

    public HuffmanInternalNode(HuffmanNode left, HuffmanNode right) {
      super(left.count + right.count);
      this.left = left;
      this.right = right;
    }

    public HuffmanNode getLeft() {
      return left;
    }

    public HuffmanNode getRight() {
      return right;
    }

    public HuffmanInternalNode asInternal() {
      return this;
    }

  }
}
