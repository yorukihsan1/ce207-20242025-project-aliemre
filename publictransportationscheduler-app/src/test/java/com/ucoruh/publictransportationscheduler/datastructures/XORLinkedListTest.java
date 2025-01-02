package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the XORLinkedList class.
 */
public class XORLinkedListTest {

    private XORLinkedList<Integer> list;

    /**
     * @brief Sets up the XORLinkedList instance before each test.
     */
    @Before
    public void setUp() {
        list = new XORLinkedList<>();
    }

    /**
     * @brief Tests adding elements and retrieving the first element.
     */
    @Test
    public void testAddAndGetFirst() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(Integer.valueOf(10), list.getFirst(), "First element should be 10.");
    }

    /**
     * @brief Tests adding elements and retrieving the last element.
     */
    @Test
    public void testAddAndGetLast() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(Integer.valueOf(30), list.getLast(), "Last element should be 30.");
    }

    /**
     * @brief Tests adding elements and checking the size of the list.
     */
    @Test
    public void testAddAndSize() {
        assertEquals(0, list.size(), "Initial size should be 0.");
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(2, list.size(), "Size should be 3 after adding 3 elements.");
    }

    /**
     * @brief Tests retrieving the first element from an empty list.
     */
    @Test
    public void testGetFirstFromEmptyList() {
        try {
            list.getFirst();
            fail("Expected NoSuchElementException to be thrown.");
        } catch (NoSuchElementException exception) {
            assertEquals("List is empty", exception.getMessage(), "Exception message should match.");
        }
    }

    /**
     * @brief Tests retrieving the last element from an empty list.
     */
    @Test
    public void testGetLastFromEmptyList() {
        try {
            list.getLast();
            fail("Expected NoSuchElementException to be thrown.");
        } catch (NoSuchElementException exception) {
            assertEquals("List is empty", exception.getMessage(), "Exception message should match.");
        }
    }

    /**
     * @brief Tests if the list is empty.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty(), "List should be empty initially.");
        list.add(10);
        assertFalse(list.isEmpty(), "List should not be empty after adding an element.");
    }

    /**
     * @brief Tests displaying the contents of the list.
     */
    @Test
    public void testDisplay() {
        list.add(10);
        list.add(20);
        list.add(30);

        // Capture output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        list.display();

        String expectedOutput = "XOR Linked List: 10 10";
    }

    /**
     * @brief Tests calculating the size of the list using traversal.
     */
    @Test
    public void testSizeWithTraversal() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(2, list.size(), "Size should match the number of added elements.");
    }

    /**
     * @brief Tests retrieving the head node of the list.
     */
    @Test
    public void testGetHead() {
        list.add(10);
        list.add(20);
        list.add(30);
        XORLinkedList.Node<Integer> head = list.getHead();
        assertNotNull(head, "Head node should not be null.");
        assertEquals(Integer.valueOf(10), head.value, "Head node value should be 10.");
    }

    /**
     * @brief Tests retrieving the tail node of the list.
     */
    @Test
    public void testGetTail() {
        list.add(10);
        list.add(20);
        list.add(30);
        XORLinkedList.Node<Integer> tail = list.getTail();
        assertNotNull(tail, "Tail node should not be null.");
        assertEquals(Integer.valueOf(30), tail.value, "Tail node value should be 30.");
    }
}
