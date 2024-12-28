package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
  private HashTable<String, Integer> hashTable;

  @BeforeEach
  public void setUp() {
    hashTable = new HashTable<>();
  }

  @Test
  public void testPutAndGet() {
    // Test adding key-value pairs and retrieving them
    hashTable.put("Key1", 100);
    hashTable.put("Key2", 200);
    assertEquals(100, hashTable.get("Key1"));
    assertEquals(200, hashTable.get("Key2"));
  }

  @Test
  public void testGetNonExistentKey() {
    // Test retrieving a non-existent key
    assertNull(hashTable.get("NonExistentKey"));
  }

  @Test
  public void testRemoveExistingKey() {
    // Test removing an existing key
    hashTable.put("Key1", 100);
    hashTable.put("Key2", 200);
    hashTable.remove("Key1");
    assertNull(hashTable.get("Key1"));
    assertEquals(200, hashTable.get("Key2"));
  }

  @Test
  public void testRemoveNonExistentKey() {
    // Test removing a key that doesn't exist
    hashTable.put("Key1", 100);
    hashTable.remove("NonExistentKey");
    assertEquals(100, hashTable.get("Key1"));
  }

  @Test
  public void testCollisionHandling() {
    // Simulate collisions and test linked list handling
    hashTable.put("Key1", 100);
    hashTable.put("Key17", 1700); // Assuming both hash to the same index
    assertEquals(100, hashTable.get("Key1"));
    assertEquals(1700, hashTable.get("Key17"));
  }

  @Test
  public void testDisplay() {
    // Capture output of the display method
    hashTable.put("Key1", 100);
    hashTable.put("Key2", 200);
    hashTable.display();
    // You can use a utility like TestOutputStream to capture and assert output if required
  }

  @Test
  public void testEmptyHashTable() {
    // Test behavior with an empty hash table
    assertNull(hashTable.get("Key1"));
    assertDoesNotThrow(() -> hashTable.remove("Key1"));
  }

  @Test
  public void testOverwriteExistingKey() {
    // Test overwriting an existing key
    hashTable.put("Key1", 100);
    hashTable.put("Key1", 200);
    assertEquals(200, hashTable.get("Key1"));
  }
}
