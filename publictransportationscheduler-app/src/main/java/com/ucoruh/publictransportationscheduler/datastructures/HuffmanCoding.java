package com.ucoruh.publictransportationscheduler.datastructures;

import java.util.*;

public class HuffmanCoding {

  // Huffman sıkıştırması
  public String compress(String data) {
    Map<Character, Integer> frequencyMap = buildFrequencyMap(data);
    PriorityQueue<HuffmanNode> queue = buildMinHeap(frequencyMap);
    HuffmanNode root = buildHuffmanTree(queue);
    Map<Character, String> huffmanCodes = buildHuffmanCodes(root);
    return encode(data, huffmanCodes);
  }

  // Huffman deşifreleme
  public String decompress(String data) {
    return decode(data);
  }

  // Frekans haritası oluşturma
  private Map<Character, Integer> buildFrequencyMap(String data) {
    Map<Character, Integer> frequencyMap = new HashMap<>();

    for (char c : data.toCharArray()) {
      frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
    }

    return frequencyMap;
  }

  // Huffman ağacını oluşturma
  private PriorityQueue<HuffmanNode> buildMinHeap(Map<Character, Integer> frequencyMap) {
    PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));

    for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
      queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
    }

    return queue;
  }

  private HuffmanNode buildHuffmanTree(PriorityQueue<HuffmanNode> queue) {
    while (queue.size() > 1) {
      HuffmanNode left = queue.poll();
      HuffmanNode right = queue.poll();
      HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
      parent.left = left;
      parent.right = right;
      queue.add(parent);
    }

    return queue.poll();
  }

  // Huffman kodlarını oluşturma
  private Map<Character, String> buildHuffmanCodes(HuffmanNode root) {
    Map<Character, String> codes = new HashMap<>();
    buildHuffmanCodesRecursive(root, "", codes);
    return codes;
  }

  private void buildHuffmanCodesRecursive(HuffmanNode node, String code, Map<Character, String> codes) {
    if (node == null) return;

    if (node.left == null && node.right == null) {
      codes.put(node.character, code);
    }

    buildHuffmanCodesRecursive(node.left, code + "0", codes);
    buildHuffmanCodesRecursive(node.right, code + "1", codes);
  }

  private String encode(String data, Map<Character, String> huffmanCodes) {
    StringBuilder encoded = new StringBuilder();

    for (char c : data.toCharArray()) {
      encoded.append(huffmanCodes.get(c));
    }

    return encoded.toString();
  }

  private String decode(String data) {
    return data; // Deşifreleme işlemi burada yapılabilir
  }

  private static class HuffmanNode {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(char character, int frequency) {
      this.character = character;
      this.frequency = frequency;
      this.left = null;
      this.right = null;
    }
  }
}
