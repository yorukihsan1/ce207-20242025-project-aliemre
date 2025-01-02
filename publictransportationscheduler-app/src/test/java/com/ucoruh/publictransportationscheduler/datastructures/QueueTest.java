package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Queue class.
 */
public class QueueTest {

    private Queue queue;

    /**
     * @brief Sets up the Queue instance before each test.
     */
    @Before
    public void setUp() {
        queue = new Queue(5); // Initialize a queue with a capacity of 5
    }

    /**
     * @brief Tests enqueue operation.
     */
    @Test
    public void testEnqueue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals("The front of the queue should be 10", 10, queue.peek());
    }

    /**
     * @brief Tests enqueue operation when the queue is full.
     */
    @Test
    public void testEnqueueFullQueue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6); // This should not be enqueued as the queue is full
        assertEquals("The front of the queue should still be 1", 1, queue.peek());
    }

    /**
     * @brief Tests dequeue operation.
     */
    @Test
    public void testDequeue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals("The dequeued value should be 10", 10, queue.dequeue());
        assertEquals("The front of the queue should now be 20", 20, queue.peek());
    }

    /**
     * @brief Tests dequeue operation when the queue is empty.
     */
    @Test
    public void testDequeueEmptyQueue() {
        int result = queue.dequeue();
        assertEquals("Dequeue on an empty queue should return -1", -1, result);
    }

    /**
     * @brief Tests peek operation on the queue.
     */
    @Test
    public void testPeek() {
        queue.enqueue(15);
        queue.enqueue(25);
        assertEquals("The front of the queue should be 15", 15, queue.peek());
        queue.dequeue();
        assertEquals("The front of the queue should now be 25", 25, queue.peek());
    }

    /**
     * @brief Tests peek operation on an empty queue.
     */
    @Test
    public void testPeekEmptyQueue() {
        assertEquals("Peek on an empty queue should return -1", -1, queue.peek());
    }

    /**
     * @brief Tests if the queue is empty.
     */
    @Test
    public void testIsEmpty() {
        assertTrue("The queue should be empty initially", queue.isEmpty());
        queue.enqueue(5);
        assertFalse("The queue should not be empty after enqueueing an element", queue.isEmpty());
    }

    /**
     * @brief Tests displaying the queue elements.
     */
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
    }

    /**
     * @brief Tests displaying the queue when it is empty.
     */
    @Test
    public void testDisplayEmptyQueue() {
        // Capture the output of the display method
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        queue.display();
        String expectedOutput = "Queue is empty.\n";
    }
}
