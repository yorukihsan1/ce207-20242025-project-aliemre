package com.ucoruh.publictransportationscheduler.datastructures;

import java.util.HashMap;

public class HashTable<K, V> {
  private HashMap<K, V> table;

  public HashTable() {
    table = new HashMap<>();
  }

  public void put(K key, V value) {
    table.put(key, value);
  }

  public V get(K key) {
    return table.get(key);
  }

  public void remove(K key) {
    table.remove(key);
  }

  public void display() {
    for (K key : table.keySet()) {
      System.out.println("Key: " + key + ", Value: " + table.get(key));
    }
  }
}
