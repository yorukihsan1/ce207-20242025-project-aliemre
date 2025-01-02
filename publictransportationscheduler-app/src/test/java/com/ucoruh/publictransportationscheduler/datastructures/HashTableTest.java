package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the HashTable class.
 */
public class HashTableTest {

    private HashTable<String, Integer> hashTable;

    /**
     * @brief Sets up the HashTable instance before each test.
     */
    @Before
    public void setUp() {
        hashTable = new HashTable<>();
    }

    /**
     * @brief Tests adding key-value pairs and retrieving them.
     */
    @Test
    public void testPutAndGet() {
        hashTable.put("Key1", 100);
        hashTable.put("Key2", 200);
        assertEquals(Integer.valueOf(100), hashTable.get("Key1"));
        assertEquals(Integer.valueOf(200), hashTable.get("Key2"));
    }

    /**
     * @brief Tests retrieving a non-existent key.
     */
    @Test
    public void testGetNonExistentKey() {
        assertNull(hashTable.get("NonExistentKey"));
    }

    /**
     * @brief Tests removing an existing key.
     */
    @Test
    public void testRemoveExistingKey() {
        hashTable.put("Key1", 100);
        hashTable.put("Key2", 200);
        hashTable.remove("Key1");
        assertNull(hashTable.get("Key1"));
        assertEquals(Integer.valueOf(200), hashTable.get("Key2"));
    }

    /**
     * @brief Tests removing a key that doesn't exist.
     */
    @Test
    public void testRemoveNonExistentKey() {
        hashTable.put("Key1", 100);
        hashTable.remove("NonExistentKey");
        assertEquals(Integer.valueOf(100), hashTable.get("Key1"));
    }

    /**
     * @brief Tests handling of hash collisions.
     */
    @Test
    public void testCollisionHandling() {
        hashTable.put("Key1", 100);
        hashTable.put("Key17", 1700); // Assuming both hash to the same index
        assertEquals(Integer.valueOf(100), hashTable.get("Key1"));
        assertEquals(Integer.valueOf(1700), hashTable.get("Key17"));
    }

    /**
     * @brief Tests displaying the hash table contents.
     */
    @Test
    public void testDisplay() {
        hashTable.put("Key1", 100);
        hashTable.put("Key2", 200);
        hashTable.display();
        // Use a utility like TestOutputStream to capture and assert output if required
    }

    /**
     * @brief Tests behavior with an empty hash table.
     */
    @Test
    public void testEmptyHashTable() {
        assertNull(hashTable.get("Key1"));
        hashTable.remove("Key1"); // Ensure no exception is thrown
    }

    /**
     * @brief Tests overwriting an existing key.
     */
    @Test
    public void testOverwriteExistingKey() {
        hashTable.put("Key1", 100);
        hashTable.put("Key1", 200);
        assertEquals(Integer.valueOf(100), hashTable.get("Key1"));
    }
}
