package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;

/**
 * @class HashTable
 * @brief A simple implementation of a hash table using chaining to handle collisions.
 * 
 * This hash table stores key-value pairs and uses linked lists to resolve hash collisions.
 * @tparam <K> The type of the keys in the hash table.
 * @tparam <V> The type of the values in the hash table.
 */
public class HashTable<K, V> implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * @brief The fixed size of the hash table.
   */
  private final int SIZE = 16;

  /**
   * @brief Array of linked list entries for hash table storage.
   */
  private Entry<K, V>[] table;

  /**
   * @brief Constructor to initialize the hash table.
   */
  public HashTable() {
    table = new Entry[SIZE];
  }

  /**
   * @class Entry
   * @brief Represents an entry in the hash table.
   * 
   * This inner class encapsulates a key-value pair and the reference to the next entry in the chain.
   * @tparam <K> The type of the key in the entry.
   * @tparam <V> The type of the value in the entry.
   */
  private static class Entry<K, V> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @brief The key of the entry.
     */
    K key;

    /**
     * @brief The value of the entry.
     */
    V value;

    /**
     * @brief The reference to the next entry in the chain.
     */
    Entry<K, V> next;

    /**
     * @brief Constructor for an entry in the hash table.
     * 
     * @param key The key for the entry.
     * @param value The value associated with the key.
     */
    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  /**
   * @brief Inserts a key-value pair into the hash table.
   * 
   * If a collision occurs, the key-value pair is added to the linked list at the corresponding index.
   * @param key The key of the entry.
   * @param value The value associated with the key.
   */
  public void put(K key, V value) {
    int index = Math.abs(key.hashCode() % SIZE);
    Entry<K, V> newEntry = new Entry<>(key, value);

    if (table[index] == null) {
      table[index] = newEntry;
    } else {
      Entry<K, V> current = table[index];

      while (current.next != null) {
        current = current.next;
      }

      current.next = newEntry;
    }
  }

  /**
   * @brief Retrieves a value from the hash table based on the key.
   * 
   * Searches the linked list at the index corresponding to the key's hash code.
   * @param key The key to look for.
   * @return The value associated with the key, or null if the key is not found.
   */
  public V get(K key) {
    int index = Math.abs(key.hashCode() % SIZE);
    Entry<K, V> current = table[index];

    while (current != null) {
      if (current.key.equals(key)) {
        return current.value;
      }

      current = current.next;
    }

    return null;
  }

  /**
   * @brief Removes a key-value pair from the hash table.
   * 
   * If the key is found, the corresponding entry is removed from the linked list.
   * @param key The key of the entry to remove.
   */
  public void remove(K key) {
    int index = Math.abs(key.hashCode() % SIZE);
    Entry<K, V> current = table[index];
    Entry<K, V> previous = null;

    while (current != null) {
      if (current.key.equals(key)) {
        if (previous == null) {
          table[index] = current.next;
        } else {
          previous.next = current.next;
        }

        return;
      }

      previous = current;
      current = current.next;
    }
  }

  /**
   * @brief Displays all key-value pairs in the hash table.
   * 
   * Iterates through all indices of the hash table and prints all key-value pairs stored in the linked lists.
   */
  public void display() {
    for (int i = 0; i < SIZE; i++) {
      Entry<K, V> current = table[i];

      while (current != null) {
        System.out.println("Key: " + current.key + ", Value: " + current.value);
        current = current.next;
      }
    }
  }
}
