package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the DoubleLinkedList class.
 */
public class DoubleLinkedListTest {

    private DoubleLinkedList<Integer> list;

    /**
     * @brief Sets up the DoubleLinkedList instance before each test.
     */
    @Before
    public void setUp() {
        list = new DoubleLinkedList<>();
    }

    /**
     * @brief Tests adding elements to the end of the list.
     */
    @Test
    public void testAddLast() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertFalse(list.isEmpty(), "List should not be empty after adding elements");
        list.display(); // Optional: You can capture the output to verify
    }

    /**
     * @brief Tests removing elements from the list.
     */
    @Test
    public void testRemove() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.remove(2);
        assertFalse(list.contains(2), "List should not contain the removed element");
        list.remove(1);
        list.remove(3);
        assertTrue(list.isEmpty(), "List should be empty after removing all elements");
    }

    /**
     * @brief Tests displaying the list.
     */
    @Test
    public void testDisplay() {
        list.addLast(5);
        list.addLast(10);
        list.addLast(15);
        list.display();
        // Use TestOutputStream or a similar utility to capture and assert console output
    }

    /**
     * @brief Tests if the list contains specific elements.
     */
    @Test
    public void testContains() {
        list.addLast(10);
        list.addLast(20);
        assertTrue(list.contains(10), "List should contain the element 10");
        assertFalse(list.contains(30), "List should not contain the element 30");
    }

    /**
     * @brief Tests the size of the list.
     */
    @Test
    public void testSize() {
        assertEquals(0, list.size(), "Initial size of the list should be 0");
        list.addLast(1);
        list.addLast(2);
        assertEquals(2, list.size(), "Size should be 2 after adding 2 elements");
    }

    /**
     * @brief Tests if the list is empty.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty(), "List should initially be empty");
        list.addLast(10);
        assertFalse(list.isEmpty(), "List should not be empty after adding an element");
    }

    /**
     * @brief Tests the iterator functionality of the list.
     */
    @Test
    public void testIterator() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext(), "Iterator should have elements");
        assertEquals(Integer.valueOf(1), iterator.next(), "First element should be 1");
        assertEquals(Integer.valueOf(2), iterator.next(), "Second element should be 2");
        assertEquals(Integer.valueOf(3), iterator.next(), "Third element should be 3");
        assertFalse(iterator.hasNext(), "Iterator should not have more elements");
    }

    /**
     * @brief Tests removing the head and tail elements of the list.
     */
    @Test
    public void testRemoveHeadAndTail() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.remove(1); // Remove head
        assertFalse(list.contains(1), "Head element should be removed");
        list.remove(3); // Remove tail
        assertFalse(list.contains(3), "Tail element should be removed");
        assertTrue(list.contains(2), "Middle element should remain in the list");
    }

    /**
     * @brief Tests removing a non-existent element from the list.
     */
    @Test
    public void testRemoveNonExistentElement() {
        list.addLast(1);
        list.addLast(2);
        list.remove(3); // Remove an element not in the list
        assertTrue(list.contains(1), "List should still contain element 1");
        assertTrue(list.contains(2), "List should still contain element 2");
    }
}
