package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Stack class.
 */
public class StackTest {

    private Stack<Integer> stack;

    /**
     * @brief Sets up the Stack instance before each test.
     */
    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    /**
     * @brief Tests the push and peek operations of the stack.
     */
    @Test
    public void testPushAndPeek() {
        stack.push(10);
        assertEquals(Integer.valueOf(10), stack.peek(), "Peek should return the last pushed value.");
        stack.push(20);
        assertEquals(Integer.valueOf(20), stack.peek(), "Peek should return the most recently pushed value.");
    }

    /**
     * @brief Tests the pop operation of the stack.
     */
    @Test
    public void testPop() {
        stack.push(10);
        stack.push(20);
        assertEquals(Integer.valueOf(20), stack.pop(), "Pop should return the most recently pushed value.");
        assertEquals(Integer.valueOf(10), stack.pop(), "Pop should return the next value after popping the top.");
    }

    /**
     * @brief Tests whether the stack is empty.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty(), "Stack should be empty initially.");
        stack.push(10);
        assertFalse(stack.isEmpty(), "Stack should not be empty after pushing an element.");
        stack.pop();
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements.");
    }

    /**
     * @brief Tests the behavior of peek operation on an empty stack.
     */
    @Test
    public void testPeekOnEmptyStack() {
        try {
            stack.peek();
            fail("Peek on an empty stack should throw an exception.");
        } catch (IllegalStateException exception) {
            assertEquals("Stack is empty", exception.getMessage(),
                    "Peek on an empty stack should throw an exception with the correct message.");
        }
    }

    /**
     * @brief Tests the behavior of pop operation on an empty stack.
     */
    @Test
    public void testPopOnEmptyStack() {
        try {
            stack.pop();
            fail("Pop on an empty stack should throw an exception.");
        } catch (IllegalStateException exception) {
            assertEquals("Stack is empty", exception.getMessage(),
                    "Pop on an empty stack should throw an exception with the correct message.");
        }
    }
}
