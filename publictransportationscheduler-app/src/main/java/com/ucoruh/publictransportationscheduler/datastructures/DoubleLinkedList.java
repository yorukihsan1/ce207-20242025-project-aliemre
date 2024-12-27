package com.ucoruh.publictransportationscheduler.datastructures;

public class DoubleLinkedList {
  private Node head;
  private Node tail;

  public void addFirst(int data) {
    Node newNode = new Node(data);

    if (head == null) {
      head = tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
  }

  public void addLast(int data) {
    Node newNode = new Node(data);

    if (tail == null) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
  }

  public void removeFirst() {
    if (head != null) {
      head = head.next;

      if (head != null) {
        head.prev = null;
      } else {
        tail = null;
      }
    }
  }

  public void removeLast() {
    if (tail != null) {
      tail = tail.prev;

      if (tail != null) {
        tail.next = null;
      } else {
        head = null;
      }
    }
  }

  public void display() {
    Node current = head;

    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }

    System.out.println();
  }

  private static class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
      this.data = data;
    }
  }
}
