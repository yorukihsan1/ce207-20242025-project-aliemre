package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class XORLinkedList<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  private Node<T> head;
  private Node<T> tail;

  // Node class that stores data and XOR of the previous and next nodes
  private static class Node<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    T value;
    Node<T> both; // XOR address to the previous and next node

    Node(T value) {
      this.value = value;
      this.both = null;
    }
  }

  public XORLinkedList() {
    this.head = null;
    this.tail = null;
  }

  // XOR operation
  private Node<T> xor(Node<T> a, Node<T> b) {
    return a == null ? b : b == null ? a : new Node<>((T) ((Object) (System.identityHashCode(a) ^ System.identityHashCode(b))));
  }

  // Add a new node to the list
  public void add(T value) {
    Node<T> newNode = new Node<>(value);

    if (head == null) {
      head = tail = newNode;
    } else {
      newNode.both = xor(tail, null); // XOR the tail with null (next of the new node is null)
      tail.both = xor(tail.both, newNode); // XOR the tail with the new node
      tail = newNode;
    }
  }

  // Get the first element of the list
  public T getFirst() {
    if (head == null) {
      throw new NoSuchElementException("List is empty");
    }

    return head.value;
  }

  // Get the last element of the list
  public T getLast() {
    if (tail == null) {
      throw new NoSuchElementException("List is empty");
    }

    return tail.value;
  }

  // Display the list
  public void display() {
    Node<T> current = head;
    Node<T> prev = null;
    System.out.print("XOR Linked List: ");

    while (current != null) {
      System.out.print(current.value + " ");
      Node<T> next = xor(prev, current.both); // Calculate the next node by XORing the previous node and current node's both pointer
      prev = current;
      current = next;
    }

    System.out.println();
  }

  // Check if the list is empty
  public boolean isEmpty() {
    return head == null;
  }

  // Get the size of the list
  public int size() {
    int count = 0;
    Node<T> current = head;
    Node<T> prev = null;

    while (current != null) {
      count++;
      Node<T> next = xor(prev, current.both);
      prev = current;
      current = next;
    }

    return count;
  }
}
