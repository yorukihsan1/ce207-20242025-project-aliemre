package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Heap class.
 */
public class HeapTest {

    private Heap heap;

    /**
     * @brief Sets up the Heap instance before each test.
     */
    @Before
    public void setUp() {
        heap = new Heap();
    }

    /**
     * @brief Tests inserting elements into the heap and peeking the minimum value.
     */
    @Test
    public void testInsertAndPeekMin() {
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        assertEquals(5, heap.peekMin(), 0, "The minimum element should be 5");
    }

    /**
     * @brief Tests extracting the minimum value from the heap.
     */
    @Test
    public void testExtractMin() {
        heap.insert(15);
        heap.insert(10);
        heap.insert(5);
        assertEquals(5, heap.extractMin(), 0, "The first extracted element should be 5");
        assertEquals(10, heap.extractMin(), 0, "The second extracted element should be 10");
        assertEquals(15, heap.extractMin(), 0, "The third extracted element should be 15");
    }

    /**
     * @brief Tests extracting the minimum value from an empty heap.
     */
    @Test
    public void testExtractMinFromEmptyHeap() {
        assertEquals(-1, heap.extractMin(), 0, "Extracting from an empty heap should return -1");
    }

    /**
     * @brief Tests peeking into an empty heap.
     */
    @Test
    public void testPeekMinFromEmptyHeap() {
        assertEquals(-1, heap.peekMin(), 0, "Peeking into an empty heap should return -1");
    }

    /**
     * @brief Tests whether the heap is empty.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(heap.isEmpty(), "Heap should be empty initially");
        heap.insert(10);
        assertFalse(heap.isEmpty(), "Heap should not be empty after insertion");
        heap.extractMin();
        assertTrue(heap.isEmpty(), "Heap should be empty after removing all elements");
    }

    /**
     * @brief Tests displaying the heap elements.
     */
    @Test
    public void testDisplay() {
        heap.insert(15);
        heap.insert(10);
        heap.insert(5);
        heap.display();
        // You can use a custom TestOutputStream to capture and assert the output if needed
    }
}
