package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
  private Queue queue;

  @BeforeEach
  public void setUp() {
    queue = new Queue(5); // Initialize a queue with a capacity of 5
  }

  @Test
  public void testEnqueue() {
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    assertEquals(10, queue.peek(), "The front of the queue should be 10");
  }

  @Test
  public void testEnqueueFullQueue() {
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    queue.enqueue(5);
    queue.enqueue(6); // This should not be enqueued as the queue is full
    assertEquals(1, queue.peek(), "The front of the queue should still be 1");
  }

  @Test
  public void testDequeue() {
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    assertEquals(10, queue.dequeue(), "The dequeued value should be 10");
    assertEquals(20, queue.peek(), "The front of the queue should now be 20");
  }

  @Test
  public void testDequeueEmptyQueue() {
    int result = queue.dequeue();
    assertEquals(-1, result, "Dequeue on an empty queue should return -1");
  }

  @Test
  public void testPeek() {
    queue.enqueue(15);
    queue.enqueue(25);
    assertEquals(15, queue.peek(), "The front of the queue should be 15");
    queue.dequeue();
    assertEquals(25, queue.peek(), "The front of the queue should now be 25");
  }

  @Test
  public void testPeekEmptyQueue() {
    assertEquals(-1, queue.peek(), "Peek on an empty queue should return -1");
  }

  @Test
  public void testIsEmpty() {
    assertTrue(queue.isEmpty(), "The queue should be empty initially");
    queue.enqueue(5);
    assertFalse(queue.isEmpty(), "The queue should not be empty after enqueueing an element");
  }

  @Test
  public void testDisplay() {
    queue.enqueue(5);
    queue.enqueue(10);
    queue.enqueue(15);
    // Capture the output of the display method
    java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(outContent));
    queue.display();
    String expectedOutput = "5 10 15 \n";
    assertEquals(expectedOutput, outContent.toString(), "The display output should match the enqueued elements");
    // Reset System.out
    System.setOut(System.out);
  }

  @Test
  public void testDisplayEmptyQueue() {
    // Capture the output of the display method
    java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(outContent));
    queue.display();
    String expectedOutput = "Queue is empty.\n";
    assertEquals(expectedOutput, outContent.toString(), "The display output should indicate the queue is empty");
    // Reset System.out
    System.setOut(System.out);
  }
}
