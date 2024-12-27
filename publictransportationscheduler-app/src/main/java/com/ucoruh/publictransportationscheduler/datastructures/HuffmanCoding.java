package com.ucoruh.publictransportationscheduler.datastructures;

import java.util.PriorityQueue;

public class HuffmanCoding {
  static class Node {
    char ch;
    int frequency;
    Node left;
    Node right;

    Node(char ch, int frequency) {
      this.ch = ch;
      this.frequency = frequency;
    }
  }

  public void buildTree(String input) {
    int[] frequency = new int[256];

    for (char c : input.toCharArray()) {
      frequency[c]++;
    }

    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

    for (int i = 0; i < 256; i++) {
      if (frequency[i] > 0) {
        pq.add(new Node((char) i, frequency[i]));
      }
    }

    while (pq.size() > 1) {
      Node left = pq.poll();
      Node right = pq.poll();
      Node combined = new Node('\0', left.frequency + right.frequency);
      combined.left = left;
      combined.right = right;
      pq.add(combined);
    }

    printCodes(pq.poll(), "");
  }

  private void printCodes(Node root, String code) {
    if (root == null) {
      return;
    }

    if (root.ch != '\0') {
      System.out.println(root.ch + ": " + code);
    }

    printCodes(root.left, code + "0");
    printCodes(root.right, code + "1");
  }
}
