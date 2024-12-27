package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.Iterator;

public class DoubleLinkedList<T> implements Serializable, Iterable<T> {
  private static final long serialVersionUID = 1L; // Sabit serialVersionUID
  private int size = 0; // Listede bulunan eleman sayısı


  private Node<T> head;
  private Node<T> tail;

  private static class Node<T> implements Serializable {
    private static final long serialVersionUID = 1L; // Node sınıfı için de sabit serialVersionUID
    T value;
    Node<T> next;
    Node<T> prev;

    Node(T value) {
      this.value = value;
    }
  }

  public DoubleLinkedList() {
    this.head = null;
    this.tail = null;
  }

  // Eleman ekleme
  public void addLast(T value) {
    Node<T> newNode = new Node<>(value);

    if (tail == null) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
  }

  // Eleman silme
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

        return;
      }

      current = current.next;
    }
  }

  // Listeyi yazdırma
  public void display() {
    Node<T> current = head;

    while (current != null) {
      System.out.print(current.value + " ");
      current = current.next;
    }

    System.out.println();
  }

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

  public int size() {
    return size;
  }

  // Liste boş mu?
  public boolean isEmpty() {
    return head == null;
  }

  // Iterable arayüzünü implement etmek için iterator() metodunu yazıyoruz
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
