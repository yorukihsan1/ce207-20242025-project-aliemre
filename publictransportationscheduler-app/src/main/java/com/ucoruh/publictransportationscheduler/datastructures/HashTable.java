package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.*;

public class HashTable<K, V> implements Serializable {
  private static final long serialVersionUID = 1L;
  private final int SIZE = 16;
  private Entry<K, V>[] table;

  public HashTable() {
    table = new Entry[SIZE];
  }

  // Entry sınıfını Serializable yapalım
  private static class Entry<K, V> implements Serializable {
    private static final long serialVersionUID = 1L;
    K key;
    V value;
    Entry<K, V> next;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  // Put method to add a key-value pair
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

  // Get method to retrieve a value by key
  public V get(K key) {
    int index = key.hashCode() % SIZE;
    Entry<K, V> current = table[index];

    while (current != null) {
      if (current.key.equals(key)) {
        return current.value;
      }

      current = current.next;
    }

    return null; // Return null if key not found
  }

  // Remove method to delete a key-value pair
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

  // Display method to print all entries
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
