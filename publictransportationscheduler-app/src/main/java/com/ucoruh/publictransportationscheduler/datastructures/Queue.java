package com.ucoruh.publictransportationscheduler.datastructures;

/**
 * @brief A circular queue implementation for managing a fixed-size collection of integers.
 */
public class Queue {
  private int[] queue; /**< Array to store queue elements. */
  private int front; /**< Index of the front element in the queue. */
  private int rear; /**< Index of the rear element in the queue. */
  private int capacity; /**< Maximum capacity of the queue. */
  private int size; /**< Current number of elements in the queue. */

  /**
   * @brief Constructs a Queue with a specified capacity.
   * @param capacity The maximum number of elements the queue can hold.
   */
  public Queue(int capacity) {
    this.capacity = capacity;
    queue = new int[capacity];
    front = 0;
    rear = -1;
    size = 0;
  }

  /**
   * @brief Adds a value to the rear of the queue.
   * @param value The integer value to enqueue.
   */
  public void enqueue(int value) {
    if (size == capacity) {
      System.out.println("Queue is full. Cannot enqueue " + value);
      return;
    }

    rear = (rear + 1) % capacity;
    queue[rear] = value;
    size++;
  }

  /**
   * @brief Removes and returns the value at the front of the queue.
   * @return The value at the front of the queue, or -1 if the queue is empty.
   */
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

  /**
   * @brief Retrieves the value at the front of the queue without removing it.
   * @return The value at the front of the queue, or -1 if the queue is empty.
   */
  public int peek() {
    if (size == 0) {
      System.out.println("Queue is empty.");
      return -1;
    }

    return queue[front];
  }

  /**
   * @brief Checks if the queue is empty.
   * @return True if the queue is empty; false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * @brief Displays all elements in the queue in order from front to rear.
   */
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
