package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;

public class Stack<T> implements Serializable {
  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
    }
  }

  private Node<T> top;

  public void push(T item) {
    Node<T> t = new Node<>(item);
    t.next = top;
    top = t;
  }

  public T pop() {
    if (top == null) throw new IllegalStateException("Stack is empty");

    T item = top.data;
    top = top.next;
    return item;
  }

  public T peek() {
    if (top == null) throw new IllegalStateException("Stack is empty");

    return top.data;
  }

  public boolean isEmpty() {
    return top == null;
  }
}
