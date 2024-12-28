package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

  private Stack<Integer> stack;

  @BeforeEach
  public void setUp() {
    stack = new Stack<>();
  }

  @Test
  public void testPushAndPeek() {
    stack.push(10);
    assertEquals(10, stack.peek(), "Peek should return the last pushed value.");
    stack.push(20);
    assertEquals(20, stack.peek(), "Peek should return the most recently pushed value.");
  }

  @Test
  public void testPop() {
    stack.push(10);
    stack.push(20);
    assertEquals(20, stack.pop(), "Pop should return the most recently pushed value.");
    assertEquals(10, stack.pop(), "Pop should return the next value after popping the top.");
  }

  @Test
  public void testIsEmpty() {
    assertTrue(stack.isEmpty(), "Stack should be empty initially.");
    stack.push(10);
    assertFalse(stack.isEmpty(), "Stack should not be empty after pushing an element.");
    stack.pop();
    assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements.");
  }

  @Test
  public void testPeekOnEmptyStack() {
    Exception exception = assertThrows(IllegalStateException.class, stack::peek);
    assertEquals("Stack is empty", exception.getMessage(), "Peek on an empty stack should throw an exception with the correct message.");
  }

  @Test
  public void testPopOnEmptyStack() {
    Exception exception = assertThrows(IllegalStateException.class, stack::pop);
    assertEquals("Stack is empty", exception.getMessage(), "Pop on an empty stack should throw an exception with the correct message.");
  }
}
