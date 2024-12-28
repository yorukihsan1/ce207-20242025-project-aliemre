package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BPlusTreeTest {
  private BPlusTree bPlusTree;

  @BeforeEach
  public void setUp() {
    bPlusTree = new BPlusTree(3); // Initialize BPlusTree with a degree of 3
  }

  @Test
  public void testInsertSingleKey() {
    bPlusTree.insert(10);
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    bPlusTree.display();
    assertTrue(output.toString().contains("Level 0 [10]"), "Tree should contain the key 10 at level 0");
  }

  @Test
  public void testInsertMultipleKeys() {
    bPlusTree.insert(10);
    bPlusTree.insert(20);
    bPlusTree.insert(5);
    bPlusTree.insert(6);
    bPlusTree.insert(12);
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    bPlusTree.display();
    String displayOutput = output.toString();
    assertTrue(displayOutput.contains("Level 0 [10]"), "Root should split correctly");
    assertTrue(displayOutput.contains("Level 1 [5, 6]"), "Left child should have keys 5, 6");
    assertTrue(displayOutput.contains("Level 1 [12, 20]"), "Right child should have keys 12, 20");
  }

  @Test
  public void testInsertTriggersSplit() {
    bPlusTree.insert(10);
    bPlusTree.insert(20);
    bPlusTree.insert(5);
    bPlusTree.insert(6);
    bPlusTree.insert(15);
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    bPlusTree.display();
    String displayOutput = output.toString();
    assertTrue(displayOutput.contains("Level 0 [10]"), "Root should split and contain 10");
    assertTrue(displayOutput.contains("Level 1 [5, 6]"), "Left child should contain 5 and 6");
    assertTrue(displayOutput.contains("Level 1 [15, 20]"), "Right child should contain 15 and 20");
  }

  @Test
  public void testInsertMoreKeys() {
    bPlusTree.insert(1);
    bPlusTree.insert(2);
    bPlusTree.insert(3);
    bPlusTree.insert(4);
    bPlusTree.insert(5);
    bPlusTree.insert(6);
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    bPlusTree.display();
    String displayOutput = output.toString();
    assertTrue(displayOutput.contains("Level 0 [3]"), "Root should split correctly");
    assertTrue(displayOutput.contains("Level 1 [1, 2]"), "Left child should contain 1, 2");
    assertTrue(displayOutput.contains("Level 1 [4, 5, 6]"), "Right child should contain 4, 5, 6");
  }

  @Test
  public void testDisplayEmptyTree() {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    bPlusTree.display();
    assertTrue(output.toString().isEmpty(), "Display output should be empty for an empty tree");
  }

  @Test
  public void testInsertDuplicateKeys() {
    bPlusTree.insert(10);
    bPlusTree.insert(20);
    bPlusTree.insert(10); // Insert duplicate
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    bPlusTree.display();
    String displayOutput = output.toString();
    assertTrue(displayOutput.contains("Level 0 [10, 20]"), "Tree should not duplicate keys");
  }
}
