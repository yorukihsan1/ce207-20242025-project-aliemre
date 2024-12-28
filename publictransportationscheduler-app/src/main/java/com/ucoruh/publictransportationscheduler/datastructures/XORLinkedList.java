package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * @brief A memory-efficient doubly linked list implementation using XOR for node linking.
 * @param <T> The type of elements stored in the linked list.
 */
public class XORLinkedList<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  private Node<T> head; /**< The head of the XOR linked list. */
  private Node<T> tail; /**< The tail of the XOR linked list. */

  /**
   * @brief A node class representing an element in the XOR linked list.
   * @param <T> The type of the value stored in the node.
   */
  protected static class Node<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    T value; /**< The value stored in the node. */
    int both; /**< The XOR of the memory addresses of the previous and next nodes. */

    /**
     * @brief Constructs a new node with the given value.
     * @param value The value to store in the node.
     */
    Node(T value) {
      this.value = value;
      this.both = 0;
    }
  }

  /**
   * @brief Constructs an empty XOR linked list.
   */
  public XORLinkedList() {
    this.head = null;
    this.tail = null;
  }

  /**
   * @brief Computes the XOR of two node memory addresses.
   * @param a The first node.
   * @param b The second node.
   * @return The XOR of the memory addresses of the nodes.
   */
  private int xor(Node<T> a, Node<T> b) {
    int aHash = a == null ? 0 : System.identityHashCode(a);
    int bHash = b == null ? 0 : System.identityHashCode(b);
    return aHash ^ bHash;
  }

  /**
   * @brief Retrieves a node by its memory address.
   * @param address The memory address of the node to retrieve.
   * @return The node corresponding to the given address, or null if not found.
   */
  public Node<T> getNodeByAddress(int address) {
    for (Node<T> node : new Node[] {head, tail}) {
      if (node != null && System.identityHashCode(node) == address) {
        return node;
      }
    }

    return null;
  }

  /**
   * @brief Adds a new value to the end of the list.
   * @param value The value to add to the list.
   */
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

  /**
   * @brief Retrieves the first value in the list.
   * @return The first value in the list.
   * @throws NoSuchElementException If the list is empty.
   */
  public T getFirst() {
    if (head == null) {
      throw new NoSuchElementException("List is empty");
    }

    return head.value;
  }

  /**
   * @brief Retrieves the last value in the list.
   * @return The last value in the list.
   * @throws NoSuchElementException If the list is empty.
   */
  public T getLast() {
    if (tail == null) {
      throw new NoSuchElementException("List is empty");
    }

    return tail.value;
  }

  /**
   * @brief Displays the elements of the list in order.
   */
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

  /**
   * @brief Checks if the list is empty.
   * @return True if the list is empty, false otherwise.
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * @brief Calculates the size of the list.
   * @return The number of elements in the list.
   */
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

  /**
   * @brief Gets the head node of the list.
   * @return The head node.
   */
  protected Node<T> getHead() {
    return head;
  }

  /**
   * @brief Gets the tail node of the list.
   * @return The tail node.
   */
  protected Node<T> getTail() {
    return tail;
  }
}
