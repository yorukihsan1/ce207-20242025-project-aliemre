package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class XORLinkedList<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  private Node<T> head;
  private Node<T> tail;

  protected static class Node<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    T value;
    int both; // XOR address of the previous and next node

    Node(T value) {
      this.value = value;
      this.both = 0;
    }
  }

  public XORLinkedList() {
    this.head = null;
    this.tail = null;
  }

  private int xor(Node<T> a, Node<T> b) {
    int aHash = a == null ? 0 : System.identityHashCode(a);
    int bHash = b == null ? 0 : System.identityHashCode(b);
    return aHash ^ bHash;
  }

  public Node<T> getNodeByAddress(int address) {
    for (Node<T> node : new Node[] {head, tail}) {
      if (node != null && System.identityHashCode(node) == address) {
        return node;
      }
    }

    return null;
  }

  public void add(T value) {
    Node<T> newNode = new Node<>(value);

    if (head == null) {
      head = tail = newNode;
    } else {
      newNode.both = xor(tail, null);
      tail.both = xor(getNodeByAddress(tail.both), newNode);
      tail = newNode;
    }
  }

  public T getFirst() {
    if (head == null) {
      throw new NoSuchElementException("List is empty");
    }

    return head.value;
  }

  public T getLast() {
    if (tail == null) {
      throw new NoSuchElementException("List is empty");
    }

    return tail.value;
  }

  public void display() {
    Node<T> current = head;
    Node<T> prev = null;
    System.out.print("XOR Linked List: ");

    while (current != null) {
      System.out.print(current.value + " ");
      Node<T> next = getNodeByAddress(xor(prev, current));
      prev = current;
      current = next;
    }

    System.out.println();
  }

  public boolean isEmpty() {
    return head == null;
  }

  public int size() {
    int count = 0;
    Node<T> current = head;
    Node<T> prev = null;

    while (current != null) {
      count++;
      Node<T> next = getNodeByAddress(xor(prev, current));
      prev = current;
      current = next;
    }

    return count;
  }

  protected Node<T> getHead() {
    return head;
  }

  protected Node<T> getTail() {
    return tail;
  }

}
