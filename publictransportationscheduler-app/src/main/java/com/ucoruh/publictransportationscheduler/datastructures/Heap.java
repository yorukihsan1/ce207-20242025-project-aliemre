package com.ucoruh.publictransportationscheduler.datastructures;

import java.util.PriorityQueue;

public class Heap {
  private PriorityQueue<Integer> minHeap;

  public Heap() {
    minHeap = new PriorityQueue<>();
  }

  public void insert(int value) {
    minHeap.add(value);
  }

  public int extractMin() {
    if (minHeap.isEmpty()) {
      System.out.println("Heap is empty.");
      return -1;
    }

    return minHeap.poll();
  }

  public int peekMin() {
    if (minHeap.isEmpty()) {
      System.out.println("Heap is empty.");
      return -1;
    }

    return minHeap.peek();
  }

  public boolean isEmpty() {
    return minHeap.isEmpty();
  }

  public void display() {
    System.out.println("Heap elements: " + minHeap);
  }
}
