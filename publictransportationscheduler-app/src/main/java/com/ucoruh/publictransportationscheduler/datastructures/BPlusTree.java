package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.*;

public class BPlusTree implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final int ORDER = 3; // Degree of the B+ Tree
  private BPlusTreeNode root; // Root node of the tree
  private int t; // Minimum degree

  public BPlusTree(int degree) {
    this.t = degree; // Set degree
    this.root = new BPlusTreeNode(true); // Initialize root as a leaf
  }

  /**
   * @brief Inserts a key into the B+ Tree.
   * @param key The key to be inserted.
   */
  public void insert(int key) {
    BPlusTreeNode rootNode = root;

    // If the root node is full, create a new root and split
    if (rootNode.keys.size() == 2 * t - 1) {
      BPlusTreeNode newRoot = new BPlusTreeNode(false);
      root = newRoot;
      newRoot.children.add(rootNode);
      splitChild(newRoot, 0, rootNode);
      insertNonFull(newRoot, key);
    } else {
      insertNonFull(rootNode, key);
    }
  }

  // Helper method to insert a key into a non-full node
  public void insertNonFull(BPlusTreeNode node, int key) {
    int i = node.keys.size() - 1;

    if (node.isLeaf) {
      node.keys.add(0);

      while (i >= 0 && key < node.keys.get(i)) {
        node.keys.set(i + 1, node.keys.get(i));
        i--;
      }

      node.keys.set(i + 1, key);
    } else {
      while (i >= 0 && key < node.keys.get(i)) {
        i--;
      }

      i++;
      BPlusTreeNode child = node.children.get(i);

      if (child.keys.size() == 2 * t - 1) {
        splitChild(node, i, child);

        if (key > node.keys.get(i)) {
          i++;
        }
      }

      insertNonFull(node.children.get(i), key);
    }
  }

  // Helper method to split a child node
  private void splitChild(BPlusTreeNode parent, int index, BPlusTreeNode child) {
    BPlusTreeNode newNode = new BPlusTreeNode(child.isLeaf);
    parent.children.add(index + 1, newNode);
    parent.keys.add(index, child.keys.get(t - 1));
    // Distribute keys
    newNode.keys.addAll(child.keys.subList(t, child.keys.size()));
    child.keys = new ArrayList<>(child.keys.subList(0, t - 1));

    // Distribute children if not a leaf
    if (!child.isLeaf) {
      newNode.children.addAll(child.children.subList(t, child.children.size()));
      child.children = new ArrayList<>(child.children.subList(0, t));
    }
  }

  /**
   * @brief Checks if a key exists in the B+ Tree.
   * @param key The key to check.
   * @return True if the key exists, otherwise false.
   */
  public boolean contains(int key) {
    return containsRecursive(root, key);
  }

  // Recursive helper method for contains
  public boolean containsRecursive(BPlusTreeNode node, int key) {
    int i = 0;

    while (i < node.keys.size() && key > node.keys.get(i)) {
      i++;
    }

    if (i < node.keys.size() && key == node.keys.get(i)) {
      return true;
    }

    if (node.isLeaf) {
      return false;
    }

    return containsRecursive(node.children.get(i), key);
  }

  /**
   * @brief Displays the structure of the B+ Tree.
   */
  public void display() {
    display(root, 0);
  }

  public void display(BPlusTreeNode node, int level) {
    System.out.println("Level " + level + " " + node.keys);

    if (!node.isLeaf) {
      for (BPlusTreeNode child : node.children) {
        display(child, level + 1);
      }
    }
  }

  // Inner class for B+ Tree Node
  public static class BPlusTreeNode implements Serializable {
    private static final long serialVersionUID = 1L;
    boolean isLeaf;
    List<Integer> keys;
    List<BPlusTreeNode> children;

    BPlusTreeNode(boolean isLeaf) {
      this.isLeaf = isLeaf;
      this.keys = new ArrayList<>();
      this.children = new ArrayList<>();
    }
  }
}
