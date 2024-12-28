package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;

/**
 * @brief A generic stack implementation using a linked list structure.
 * @param <T> The type of elements held in this stack.
 */
public class Stack<T> implements Serializable {

  /**
   * @brief Represents a node in the linked list.
   * @param <T> The type of the node's data.
   */
  private static class Node<T> {
    T data; /**< The data stored in this node. */
    Node<T> next; /**< Reference to the next node in the stack. */

    /**
     * @brief Constructs a new node with the specified data.
     * @param data The data to store in the node.
     */
    Node(T data) {
      this.data = data;
    }
  }

  private Node<T> top; /**< The top node of the stack. */

  /**
   * @brief Pushes an item onto the top of the stack.
   * @param item The item to be pushed onto the stack.
   */
  public void push(T item) {
    Node<T> t = new Node<>(item);
    t.next = top;
    top = t;
  }

  /**
   * @brief Removes and returns the top item from the stack.
   * @return The item at the top of the stack.
   * @throws IllegalStateException If the stack is empty.
   */
  public T pop() {
    if (top == null) throw new IllegalStateException("Stack is empty");

    T item = top.data;
    top = top.next;
    return item;
  }

  /**
   * @brief Retrieves the top item of the stack without removing it.
   * @return The item at the top of the stack.
   * @throws IllegalStateException If the stack is empty.
   */
  public T peek() {
    if (top == null) throw new IllegalStateException("Stack is empty");

    return top.data;
  }

  /**
   * @brief Checks whether the stack is empty.
   * @return True if the stack is empty; false otherwise.
   */
  public boolean isEmpty() {
    return top == null;
  }
}
