package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {
  private DoubleLinkedList<Integer> list;

  @BeforeEach
  public void setUp() {
    list = new DoubleLinkedList<>();
  }

  @Test
  public void testAddLast() {
    list.addLast(1);
    list.addLast(2);
    list.addLast(3);
    assertFalse(list.isEmpty(), "List should not be empty after adding elements");
    list.display(); // Optional: You can capture the output to verify
  }

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

  @Test
  public void testDisplay() {
    list.addLast(5);
    list.addLast(10);
    list.addLast(15);
    list.display();
    // Use TestOutputStream or a similar utility to capture and assert console output
  }

  @Test
  public void testContains() {
    list.addLast(10);
    list.addLast(20);
    assertTrue(list.contains(10), "List should contain the element 10");
    assertFalse(list.contains(30), "List should not contain the element 30");
  }

  @Test
  public void testSize() {
    assertEquals(0, list.size(), "Initial size of the list should be 0");
    list.addLast(1);
    list.addLast(2);
    assertEquals(2, list.size(), "Size should be 2 after adding 2 elements");
  }

  @Test
  public void testIsEmpty() {
    assertTrue(list.isEmpty(), "List should initially be empty");
    list.addLast(10);
    assertFalse(list.isEmpty(), "List should not be empty after adding an element");
  }

  @Test
  public void testIterator() {
    list.addLast(1);
    list.addLast(2);
    list.addLast(3);
    Iterator<Integer> iterator = list.iterator();
    assertTrue(iterator.hasNext(), "Iterator should have elements");
    assertEquals(1, iterator.next(), "First element should be 1");
    assertEquals(2, iterator.next(), "Second element should be 2");
    assertEquals(3, iterator.next(), "Third element should be 3");
    assertFalse(iterator.hasNext(), "Iterator should not have more elements");
  }

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

  @Test
  public void testRemoveNonExistentElement() {
    list.addLast(1);
    list.addLast(2);
    list.remove(3); // Remove an element not in the list
    assertTrue(list.contains(1), "List should still contain element 1");
    assertTrue(list.contains(2), "List should still contain element 2");
  }
}
