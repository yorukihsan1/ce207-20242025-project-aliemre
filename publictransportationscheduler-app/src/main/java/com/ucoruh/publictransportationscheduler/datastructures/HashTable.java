package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;

/**
 * @brief A simple implementation of a hash table using chaining to handle collisions.
 * @param <K> The type of the keys in the hash table.
 * @param <V> The type of the values in the hash table.
 */
public class HashTable<K, V> implements Serializable {
  private static final long serialVersionUID = 1L;
  private final int SIZE = 16; /**< The fixed size of the hash table. */
  private Entry<K, V>[] table; /**< Array of linked list entries for hash table storage. */

  /**
   * @brief Constructor to initialize the hash table.
   */
  public HashTable() {
    table = new Entry[SIZE];
  }

  /**
   * @brief Inner class representing an entry in the hash table.
   * @param <K> The type of the key in the entry.
   * @param <V> The type of the value in the entry.
   */
  private static class Entry<K, V> implements Serializable {
    private static final long serialVersionUID = 1L;
    K key; /**< The key of the entry. */
    V value; /**< The value of the entry. */
    Entry<K, V> next; /**< The reference to the next entry in the chain. */

    /**
     * @brief Constructor for an entry in the hash table.
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
   * @param key The key of the entry.
   * @param value The value associated with the key.
   */
  public void put(K key, V value) {
    int index = key.hashCode() % SIZE;
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
   * @param key The key to look for.
   * @return The value associated with the key, or null if the key is not found.
   */
  public V get(K key) {
    int index = key.hashCode() % SIZE;
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
   * @param key The key of the entry to remove.
   */
  public void remove(K key) {
    int index = key.hashCode() % SIZE;
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
