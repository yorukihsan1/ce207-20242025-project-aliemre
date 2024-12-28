package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class XORLinkedListTest {

  private XORLinkedList<Integer> list;

  @BeforeEach
  public void setUp() {
    list = new XORLinkedList<>();
  }

  @Test
  public void testAddAndGetFirst() {
    list.add(10);
    list.add(20);
    list.add(30);
    assertEquals(10, list.getFirst(), "First element should be 10.");
  }

  @Test
  public void testAddAndGetLast() {
    list.add(10);
    list.add(20);
    list.add(30);
    assertEquals(30, list.getLast(), "Last element should be 30.");
  }

  @Test
  public void testAddAndSize() {
    assertEquals(0, list.size(), "Initial size should be 0.");
    list.add(10);
    list.add(20);
    list.add(30);
    assertEquals(3, list.size(), "Size should be 3 after adding 3 elements.");
  }

  @Test
  public void testGetFirstFromEmptyList() {
    Exception exception = assertThrows(NoSuchElementException.class, () -> list.getFirst());
    assertEquals("List is empty", exception.getMessage(), "Exception message should match.");
  }

  @Test
  public void testGetLastFromEmptyList() {
    Exception exception = assertThrows(NoSuchElementException.class, () -> list.getLast());
    assertEquals("List is empty", exception.getMessage(), "Exception message should match.");
  }

  @Test
  public void testIsEmpty() {
    assertTrue(list.isEmpty(), "List should be empty initially.");
    list.add(10);
    assertFalse(list.isEmpty(), "List should not be empty after adding an element.");
  }

  @Test
  public void testDisplay() {
    list.add(10);
    list.add(20);
    list.add(30);
    // Capture output
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    list.display();
    String expectedOutput = "XOR Linked List: 10 20 30 \n";
    assertEquals(expectedOutput, output.toString(), "Display output should match expected.");
    System.setOut(System.out); // Restore original System.out
  }

  @Test
  public void testSizeWithTraversal() {
    list.add(10);
    list.add(20);
    list.add(30);
    assertEquals(3, list.size(), "Size should match the number of added elements.");
  }

  @Test
  public void testGetHead() {
    list.add(10);
    list.add(20);
    list.add(30);
    XORLinkedList.Node<Integer> head = list.getHead();
    assertNotNull(head, "Head node should not be null.");
    assertEquals(10, head.value, "Head node value should be 10.");
  }

  @Test
  public void testGetTail() {
    list.add(10);
    list.add(20);
    list.add(30);
    XORLinkedList.Node<Integer> tail = list.getTail();
    assertNotNull(tail, "Tail node should not be null.");
    assertEquals(30, tail.value, "Tail node value should be 30.");
  }
}
