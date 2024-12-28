package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @brief A generic implementation of a doubly linked list that supports various operations such as addition, removal, and iteration.
 * @param <T> The type of elements stored in the list.
 */
public class DoubleLinkedList<T> implements Serializable, Iterable<T> {
  private static final long serialVersionUID = 1L; /**< Unique identifier for serialization */
  private int size = 0; /**< The number of elements in the list */

  private Node<T> head; /**< The first node of the list */
  private Node<T> tail; /**< The last node of the list */

  /**
   * @brief Node class representing each element in the doubly linked list.
   * @param <T> The type of value stored in the node.
   */
  private static class Node<T> implements Serializable {
    private static final long serialVersionUID = 1L; /**< Unique identifier for serialization */
    T value; /**< The value stored in the node */
    Node<T> next; /**< Reference to the next node */
    Node<T> prev; /**< Reference to the previous node */

    /**
     * @brief Constructs a node with the specified value.
     * @param value The value to be stored in the node.
     */
    Node(T value) {
      this.value = value;
    }
  }

  /**
   * @brief Initializes an empty doubly linked list.
   */
  public DoubleLinkedList() {
    this.head = null;
    this.tail = null;
  }

  /**
   * @brief Adds an element to the end of the list.
   * @param value The value to be added.
   */
  public void addLast(T value) {
    Node<T> newNode = new Node<>(value);

    if (tail == null) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }

    size++;
  }

  /**
   * @brief Removes the first occurrence of the specified value from the list.
   * @param value The value to be removed.
   */
  public void remove(T value) {
    Node<T> current = head;

    while (current != null) {
      if (current.value.equals(value)) {
        if (current.prev != null) {
          current.prev.next = current.next;
        } else {
          head = current.next;
        }

        if (current.next != null) {
          current.next.prev = current.prev;
        } else {
          tail = current.prev;
        }

        size--;
        return;
      }

      current = current.next;
    }
  }

  /**
   * @brief Displays all elements in the list.
   */
  public void display() {
    Node<T> current = head;

    while (current != null) {
      System.out.print(current.value + " ");
      current = current.next;
    }

    System.out.println();
  }

  /**
   * @brief Checks if the list contains the specified value.
   * @param value The value to check.
   * @return True if the value exists in the list, false otherwise.
   */
  public boolean contains(T value) {
    Node<T> current = head;

    while (current != null) {
      if (current.value.equals(value)) {
        return true;
      }

      current = current.next;
    }

    return false;
  }

  /**
   * @brief Returns the number of elements in the list.
   * @return The size of the list.
   */
  public int size() {
    return size;
  }

  /**
   * @brief Checks if the list is empty.
   * @return True if the list is empty, false otherwise.
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * @brief Returns an iterator for the list.
   * @return An iterator for the doubly linked list.
   */
  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> current = head;
      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public T next() {
        T value = current.value;
        current = current.next;
        return value;
      }
    };
  }
}
