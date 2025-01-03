package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.*;

/**
 * @class BPlusTree
 * @brief Implementation of a B+ Tree data structure.
 *
 * A B+ Tree is a self-balancing tree data structure that maintains sorted data
 * and allows for efficient insertion, deletion, and search operations.
 */
public class BPlusTree implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * @brief The degree of the B+ Tree.
   */
  private static final int ORDER = 3; // Degree of the B+ Tree

  /**
   * @brief Root node of the B+ Tree.
   */
  private BPlusTreeNode root; // Root node of the tree

  /**
   * @brief Minimum degree of the B+ Tree.
   */
  private int t; // Minimum degree

  /**
   * @brief Constructor to initialize the B+ Tree with a specified degree.
   * @param degree The minimum degree of the B+ Tree.
   */
  public BPlusTree(int degree) {
    this.t = degree; // Set degree
    this.root = new BPlusTreeNode(true); // Initialize root as a leaf
  }

  /**
   * @brief Inserts a key into the B+ Tree.
   * 
   * If the root is full, a new root is created, and the tree is split.
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

  /**
   * @brief Helper method to insert a key into a non-full node.
   * 
   * Determines the correct position and inserts the key into the appropriate node.
   * @param node The current node being processed.
   * @param key The key to be inserted.
   */
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

  /**
   * @brief Helper method to split a child node.
   * 
   * Splits the specified child node, redistributing keys and children to maintain the B+ Tree properties.
   * @param parent The parent node of the child to be split.
   * @param index The index of the child node in the parent's children list.
   * @param child The child node to be split.
   */
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
   * 
   * Searches the tree for the specified key and returns true if found.
   * @param key The key to check.
   * @return True if the key exists, otherwise false.
   */
  public boolean contains(int key) {
    return containsRecursive(root, key);
  }

  /**
   * @brief Recursive helper method for checking key existence.
   * 
   * Traverses the tree recursively to find the key.
   * @param node The current node being processed.
   * @param key The key to search for.
   * @return True if the key exists, otherwise false.
   */
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
   * 
   * Prints the keys and structure of the tree for debugging or visualization purposes.
   */
  public void display() {
    display(root, 0);
  }

  /**
   * @brief Recursive helper method to display the tree structure.
   * 
   * Prints the keys at the current node and recursively displays child nodes.
   * @param node The current node being processed.
   * @param level The level of the current node in the tree.
   */
  public void display(BPlusTreeNode node, int level) {
    System.out.println("Level " + level + " " + node.keys);

    if (!node.isLeaf) {
      for (BPlusTreeNode child : node.children) {
        display(child, level + 1);
      }
    }
  }

  /**
   * @class BPlusTreeNode
   * @brief Represents a node in the B+ Tree.
   *
   * A node in the B+ Tree can either be a leaf or an internal node.
   */
  public static class BPlusTreeNode implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @brief Indicates whether the node is a leaf.
     */
    boolean isLeaf;

    /**
     * @brief List of keys stored in the node.
     */
    List<Integer> keys;

    /**
     * @brief List of children nodes.
     */
    List<BPlusTreeNode> children;

    /**
     * @brief Constructor to initialize a node.
     * @param isLeaf True if the node is a leaf, false otherwise.
     */
    BPlusTreeNode(boolean isLeaf) {
      this.isLeaf = isLeaf;
      this.keys = new ArrayList<>();
      this.children = new ArrayList<>();
    }
  }
}
