package com.ucoruh.publictransportationscheduler.datastructures;

import java.util.*;

/**
 * @brief Implementation of Huffman Coding for data compression and decompression.
 * This class provides methods to compress a string into its Huffman-coded form
 * and decompress it back to the original string.
 */
public class HuffmanCoding {

  /**
   * @brief Compresses a string using Huffman coding.
   * @param data The input string to be compressed.
   * @return The compressed string in Huffman-coded form.
   */
  public String compress(String data) {
    Map<Character, Integer> frequencyMap = buildFrequencyMap(data);
    PriorityQueue<HuffmanNode> queue = buildMinHeap(frequencyMap);
    HuffmanNode root = buildHuffmanTree(queue);
    Map<Character, String> huffmanCodes = buildHuffmanCodes(root);
    return encode(data, huffmanCodes);
  }

  /**
   * @brief Decompresses a Huffman-coded string.
   * @param data The compressed Huffman-coded string.
   * @return The original decompressed string.
   */
  public String decompress(String data) {
    return decode(data);
  }

  /**
   * @brief Builds a frequency map from the input string.
   * @param data The input string.
   * @return A map with characters as keys and their frequencies as values.
   */
  private Map<Character, Integer> buildFrequencyMap(String data) {
    Map<Character, Integer> frequencyMap = new HashMap<>();

    for (char c : data.toCharArray()) {
      frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
    }

    return frequencyMap;
  }

  /**
   * @brief Creates a priority queue (min-heap) of Huffman nodes from the frequency map.
   * @param frequencyMap A map of characters and their frequencies.
   * @return A priority queue of Huffman nodes.
   */
  private PriorityQueue<HuffmanNode> buildMinHeap(Map<Character, Integer> frequencyMap) {
    PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));

    for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
      queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
    }

    return queue;
  }

  /**
   * @brief Builds the Huffman tree from a priority queue of Huffman nodes.
   * @param queue A priority queue of Huffman nodes.
   * @return The root of the Huffman tree.
   */
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

  /**
   * @brief Generates Huffman codes for characters based on the Huffman tree.
   * @param root The root node of the Huffman tree.
   * @return A map of characters and their corresponding Huffman codes.
   */
  private Map<Character, String> buildHuffmanCodes(HuffmanNode root) {
    Map<Character, String> codes = new HashMap<>();
    buildHuffmanCodesRecursive(root, "", codes);
    return codes;
  }

  /**
   * @brief Recursively generates Huffman codes for each character in the tree.
   * @param node The current node in the Huffman tree.
   * @param code The Huffman code generated so far.
   * @param codes A map to store the Huffman codes for characters.
   */
  private void buildHuffmanCodesRecursive(HuffmanNode node, String code, Map<Character, String> codes) {
    if (node == null) return;

    if (node.left == null && node.right == null) {
      codes.put(node.character, code);
    }

    buildHuffmanCodesRecursive(node.left, code + "0", codes);
    buildHuffmanCodesRecursive(node.right, code + "1", codes);
  }

  /**
   * @brief Encodes a string using the generated Huffman codes.
   * @param data The input string to be encoded.
   * @param huffmanCodes A map of characters and their corresponding Huffman codes.
   * @return The encoded Huffman string.
   */
  private String encode(String data, Map<Character, String> huffmanCodes) {
    StringBuilder encoded = new StringBuilder();

    for (char c : data.toCharArray()) {
      encoded.append(huffmanCodes.get(c));
    }

    return encoded.toString();
  }

  /**
   * @brief Decodes a Huffman-coded string back to its original form.
   * @param data The Huffman-coded string.
   * @return The original string (currently returns the input as a placeholder).
   */
  private String decode(String data) {
    return data; // Decompression logic can be implemented here.
  }

  /**
   * @brief A node in the Huffman tree representing a character and its frequency.
   */
  private static class HuffmanNode {
    char character; /**< The character represented by the node. */
    int frequency; /**< The frequency of the character in the input data. */
    HuffmanNode left; /**< The left child node. */
    HuffmanNode right; /**< The right child node. */

    /**
     * @brief Constructor to initialize a Huffman node.
     * @param character The character represented by the node.
     * @param frequency The frequency of the character.
     */
    HuffmanNode(char character, int frequency) {
      this.character = character;
      this.frequency = frequency;
      this.left = null;
      this.right = null;
    }
  }
}
