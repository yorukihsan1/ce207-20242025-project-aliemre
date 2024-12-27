package com.ucoruh.publictransportationscheduler.datastructures;

public class Queue {
  private int[] queue;
  private int front;
  private int rear;
  private int capacity;
  private int size;

  public Queue(int capacity) {
    this.capacity = capacity;
    queue = new int[capacity];
    front = 0;
    rear = -1;
    size = 0;
  }

  public void enqueue(int value) {
    if (size == capacity) {
      System.out.println("Queue is full. Cannot enqueue " + value);
      return;
    }

    rear = (rear + 1) % capacity;
    queue[rear] = value;
    size++;
  }

  public int dequeue() {
    if (size == 0) {
      System.out.println("Queue is empty. Nothing to dequeue.");
      return -1;
    }

    int value = queue[front];
    front = (front + 1) % capacity;
    size--;
    return value;
  }

  public int peek() {
    if (size == 0) {
      System.out.println("Queue is empty.");
      return -1;
    }

    return queue[front];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void display() {
    if (size == 0) {
      System.out.println("Queue is empty.");
      return;
    }

    int i = front;

    for (int count = 0; count < size; count++) {
      System.out.print(queue[i] + " ");
      i = (i + 1) % capacity;
    }

    System.out.println();
  }
}
