package com.ucoruh.publictransportationscheduler.datastructures;

import java.util.PriorityQueue;

/**
 * @brief A simple implementation of a min-heap using Java's PriorityQueue.
 * This class provides methods to insert values, extract the minimum value,
 * peek at the minimum value, check if the heap is empty, and display all elements in the heap.
 */
public class Heap {
  private PriorityQueue<Integer> minHeap; /**< Priority queue to store heap elements in ascending order. */

  /**
   * @brief Constructor to initialize the heap.
   * Creates an empty priority queue to manage the heap elements.
   */
  public Heap() {
    minHeap = new PriorityQueue<>();
  }

  /**
   * @brief Inserts a value into the heap.
   * The value is added to the priority queue and positioned according to the heap property.
   * @param value The integer value to be inserted into the heap.
   */
  public void insert(int value) {
    minHeap.add(value);
  }

  /**
   * @brief Extracts and removes the minimum value from the heap.
   * If the heap is empty, the method returns -1 and displays a message indicating that the heap is empty.
   * @return The minimum value in the heap, or -1 if the heap is empty.
   */
  public int extractMin() {
    if (minHeap.isEmpty()) {
      System.out.println("Heap is empty.");
      return -1;
    }

    return minHeap.poll();
  }

  /**
   * @brief Retrieves the minimum value in the heap without removing it.
   * If the heap is empty, the method returns -1 and displays a message indicating that the heap is empty.
   * @return The minimum value in the heap, or -1 if the heap is empty.
   */
  public int peekMin() {
    if (minHeap.isEmpty()) {
      System.out.println("Heap is empty.");
      return -1;
    }

    return minHeap.peek();
  }

  /**
   * @brief Checks if the heap is empty.
   * This method verifies whether there are any elements in the heap.
   * @return True if the heap is empty, false otherwise.
   */
  public boolean isEmpty() {
    return minHeap.isEmpty();
  }

  /**
   * @brief Displays all the elements in the heap.
   * Prints the contents of the heap as stored in the priority queue.
   */
  public void display() {
    System.out.println("Heap elements: " + minHeap);
  }
}
