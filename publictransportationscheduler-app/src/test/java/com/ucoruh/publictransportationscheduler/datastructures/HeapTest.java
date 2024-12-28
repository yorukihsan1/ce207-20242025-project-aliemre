package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
  private Heap heap;

  @BeforeEach
  public void setUp() {
    heap = new Heap();
  }

  @Test
  public void testInsertAndPeekMin() {
    // Insert elements and verify the minimum
    heap.insert(10);
    heap.insert(5);
    heap.insert(20);
    assertEquals(5, heap.peekMin(), "The minimum element should be 5");
  }

  @Test
  public void testExtractMin() {
    // Insert elements and extract the minimum
    heap.insert(15);
    heap.insert(10);
    heap.insert(5);
    assertEquals(5, heap.extractMin(), "The first extracted element should be 5");
    assertEquals(10, heap.extractMin(), "The second extracted element should be 10");
    assertEquals(15, heap.extractMin(), "The third extracted element should be 15");
  }

  @Test
  public void testExtractMinFromEmptyHeap() {
    // Try extracting from an empty heap
    assertEquals(-1, heap.extractMin(), "Extracting from an empty heap should return -1");
  }

  @Test
  public void testPeekMinFromEmptyHeap() {
    // Try peeking into an empty heap
    assertEquals(-1, heap.peekMin(), "Peeking into an empty heap should return -1");
  }

  @Test
  public void testIsEmpty() {
    // Verify heap is empty initially
    assertTrue(heap.isEmpty(), "Heap should be empty initially");
    // Insert an element and verify it's not empty
    heap.insert(10);
    assertFalse(heap.isEmpty(), "Heap should not be empty after insertion");
    // Extract the element and verify it's empty again
    heap.extractMin();
    assertTrue(heap.isEmpty(), "Heap should be empty after removing all elements");
  }

  @Test
  public void testDisplay() {
    // Capture the output of the display method
    heap.insert(15);
    heap.insert(10);
    heap.insert(5);
    heap.display();
    // You can use a custom TestOutputStream to capture and assert the output if needed
  }
}
