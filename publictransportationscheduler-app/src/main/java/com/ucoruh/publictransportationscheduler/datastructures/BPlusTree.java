package com.ucoruh.publictransportationscheduler.datastructures;

import java.util.ArrayList;
import java.util.List;

class BPlusTreeNode {
  boolean isLeaf;
  List<Integer> keys;
  List<BPlusTreeNode> children;

  public BPlusTreeNode(boolean isLeaf) {
    this.isLeaf = isLeaf;
    this.keys = new ArrayList<>();
    this.children = new ArrayList<>();
  }
}

public class BPlusTree {
  private BPlusTreeNode root;
  private int t; // Minimum derece

  public BPlusTree(int degree) {
    this.t = degree;
    this.root = new BPlusTreeNode(true);
  }

  public void insert(int key) {
    BPlusTreeNode rootNode = root;

    if (rootNode.keys.size() == 2 * t - 1) {
      BPlusTreeNode newNode = new BPlusTreeNode(false);
      root = newNode;
      newNode.children.add(rootNode);
      splitChild(newNode, 0, rootNode);
      insertNonFull(newNode, key);
    } else {
      insertNonFull(rootNode, key);
    }
  }

  private void insertNonFull(BPlusTreeNode node, int key) {
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

  private void splitChild(BPlusTreeNode parent, int index, BPlusTreeNode child) {
    BPlusTreeNode newNode = new BPlusTreeNode(child.isLeaf);
    parent.children.add(index + 1, newNode);
    parent.keys.add(index, child.keys.get(t - 1));
    newNode.keys.addAll(child.keys.subList(t, child.keys.size()));
    child.keys = child.keys.subList(0, t - 1);

    if (!child.isLeaf) {
      newNode.children.addAll(child.children.subList(t, child.children.size()));
      child.children = child.children.subList(0, t);
    }
  }

  public void display() {
    display(root, 0);
  }

  private void display(BPlusTreeNode node, int level) {
    System.out.println("Level " + level + " " + node.keys);

    if (!node.isLeaf) {
      for (BPlusTreeNode child : node.children) {
        display(child, level + 1);
      }
    }
  }
}
