package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.*;
import java.util.Queue;

public class GraphSearch implements Serializable {
  private static final long serialVersionUID = 1L;
  private Map<String, List<String>> adjacencyList = new HashMap<>();

  // Düğüm ekleme metodu
  public void addNode(String node) {
    adjacencyList.putIfAbsent(node, new ArrayList<>());
  }

  // Kenar ekleme metodu
  public void addEdge(String source, String destination) {
    adjacencyList.putIfAbsent(source, new ArrayList<>());
    adjacencyList.putIfAbsent(destination, new ArrayList<>());
    adjacencyList.get(source).add(destination);
    adjacencyList.get(destination).add(source); // İki yönlü bir grafik için
  }

  // BFS algoritması (Breadth-First Search)
  public void bfs(String start) {
    if (!adjacencyList.containsKey(start)) {
      System.out.println("Start node not found.");
      return;
    }

    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      String current = queue.poll();
      System.out.println("Visited: " + current);

      for (String neighbor : adjacencyList.get(current)) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
        }
      }
    }
  }

  // DFS algoritması (Depth-First Search)
  public void dfs(String start) {
    if (!adjacencyList.containsKey(start)) {
      System.out.println("Start node not found.");
      return;
    }

    Set<String> visited = new HashSet<>();
    dfsRecursive(start, visited);
  }

  public void dfsRecursive(String node, Set<String> visited) {
    visited.add(node);
    System.out.println("Visited: " + node);

    for (String neighbor : adjacencyList.get(node)) {
      if (!visited.contains(neighbor)) {
        dfsRecursive(neighbor, visited);
      }
    }
  }

  // Getter metodları (List döndürmek için)
  public List<String> getNodes() {
    return new ArrayList<>(adjacencyList.keySet()); // Anahtarları bir liste olarak döndür
  }

  // Setter metodları (List alıyoruz ve her bir öğeyi ekliyoruz)
  public void setNodes(List<String> nodes) {
    for (String node : nodes) {
      addNode(node);
    }
  }

  // Kenarları almak için
  public List<String[]> getEdges() {
    List<String[]> edges = new ArrayList<>();

    for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
      String source = entry.getKey();

      for (String destination : entry.getValue()) {
        edges.add(new String[] {source, destination});
      }
    }

    return edges;
  }

  // Kenarları eklemek için
  public void setEdges(List<String[]> edges) {
    for (String[] edge : edges) {
      if (edge.length == 2) {
        addEdge(edge[0], edge[1]);
      }
    }
  }
}
