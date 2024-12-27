package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.*;

public class BPlusTree implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final int ORDER = 3; // B+ ağacının sırası (derece)

  private BPlusTreeNode root; // Ağacın kök düğümü
  private int t; // Minimum derece

  public BPlusTree(int degree) {
    this.t = degree; // Dereceyi belirliyoruz
    this.root = new BPlusTreeNode(true); // Kök düğüm başlangıçta yaprak düğüm
  }

  // B+ Ağacında anahtar ekleme
  public void insert(int key) {
    BPlusTreeNode rootNode = root;

    // Kök düğümün anahtarları tam doluysa, yeni bir kök oluşturulur
    if (rootNode.keys.size() == 2 * t - 1) {
      BPlusTreeNode newNode = new BPlusTreeNode(false); // Yeni bir düğüm oluştur
      root = newNode;
      newNode.children.add(rootNode); // Kök düğümü yeni düğüme bağla
      splitChild(newNode, 0, rootNode); // Kök düğümünü ayır
      insertNonFull(newNode, key); // Yeni düğümde anahtar ekle
    } else {
      insertNonFull(rootNode, key); // Düğüm tam değilse direkt ekle
    }
  }

  // Düğümde anahtar eklemek için kullanılan yardımcı fonksiyon
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

      // Eğer çocuk düğümünün anahtarları doluysa, onu ayırıyoruz
      if (child.keys.size() == 2 * t - 1) {
        splitChild(node, i, child);

        if (key > node.keys.get(i)) {
          i++;
        }
      }

      insertNonFull(node.children.get(i), key); // Alt düğümde anahtar ekle
    }
  }

  // Çocuk düğümü ayırma işlemi
  private void splitChild(BPlusTreeNode parent, int index, BPlusTreeNode child) {
    BPlusTreeNode newNode = new BPlusTreeNode(child.isLeaf); // Yeni bir düğüm oluştur
    parent.children.add(index + 1, newNode); // Yeni düğümü ana düğümün children listesine ekle
    parent.keys.add(index, child.keys.get(t - 1)); // Anahtarları ayır
    // Yeni düğüme eski düğümün anahtarlarını aktar
    newNode.keys.addAll(child.keys.subList(t, child.keys.size()));
    child.keys = child.keys.subList(0, t - 1);

    // Eğer çocuk düğüm bir yaprak değilse, alt düğümleri de ayır
    if (!child.isLeaf) {
      newNode.children.addAll(child.children.subList(t, child.children.size()));
      child.children = child.children.subList(0, t);
    }
  }

  // B+ Ağacını görüntüleme
  public void display() {
    display(root, 0);
  }

  private void display(BPlusTreeNode node, int level) {
    System.out.println("Level " + level + " " + node.keys); // Düğümün seviyesini ve anahtarlarını yazdır

    // Eğer düğüm yaprak değilse, alt düğümleri de göster
    if (!node.isLeaf) {
      for (BPlusTreeNode child : node.children) {
        display(child, level + 1);
      }
    }
  }

  // İç düğüm sınıfı
  private static class BPlusTreeNode implements Serializable {
    private static final long serialVersionUID = 1L;
    boolean isLeaf; // Yaprak mı değil mi
    List<Integer> keys; // Anahtarlar
    List<BPlusTreeNode> children; // Çocuk düğümler

    BPlusTreeNode(boolean isLeaf) {
      this.isLeaf = isLeaf;
      this.keys = new ArrayList<>();
      this.children = new ArrayList<>();
    }
  }
}
