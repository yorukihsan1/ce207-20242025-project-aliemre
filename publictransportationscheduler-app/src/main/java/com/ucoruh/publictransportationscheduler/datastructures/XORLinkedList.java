package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class XORLinkedList<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  private Node<T> head;
  private Node<T> tail;

  private static class Node<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    T value;
    Node<T> both; // XOR adresi tutulacak

    Node(T value) {
      this.value = value;
      this.both = null;
    }
  }

  public XORLinkedList() {
    this.head = null;
    this.tail = null;
  }

  // XOR işlemini yapan yardımcı fonksiyon
  private Node<T> xor(Node<T> a, Node<T> b) {
    return a == null ? b : b == null ? a : new Node<>((T) ((Object) (System.identityHashCode(a) ^ System.identityHashCode(b))));
  }

  // Listeye eleman ekleme
  public void add(T value) {
    Node<T> newNode = new Node<>(value);

    if (head == null) {
      head = tail = newNode;
    } else {
      newNode.both = xor(tail, null);
      tail.both = xor(tail.both, newNode);
      tail = newNode;
    }
  }

  // Baştaki elemanı döndür
  public T getFirst() {
    if (head == null) {
      throw new NoSuchElementException("List is empty");
    }

    return head.value;
  }

  // Sondaki elemanı döndür
  public T getLast() {
    if (tail == null) {
      throw new NoSuchElementException("List is empty");
    }

    return tail.value;
  }

  // Listeyi yazdır
  public void display() {
    Node<T> current = head;
    Node<T> prev = null;
    System.out.print("XOR Linked List: ");

    while (current != null) {
      System.out.print(current.value + " ");
      Node<T> next = xor(prev, current.both);
      prev = current;
      current = next;
    }

    System.out.println();
  }

  // Listeyi kontrol etme
  public boolean isEmpty() {
    return head == null;
  }
}
