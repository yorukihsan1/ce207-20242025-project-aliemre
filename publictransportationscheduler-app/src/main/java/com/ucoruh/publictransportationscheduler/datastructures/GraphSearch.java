package com.ucoruh.publictransportationscheduler.datastructures;

import java.util.*;
import java.util.Queue;



public class GraphSearch {
  private Map<String, List<String>> graph = new HashMap<>();

  public void addEdge(String start, String end) {
    graph.putIfAbsent(start, new ArrayList<>());
    graph.putIfAbsent(end, new ArrayList<>());
    graph.get(start).add(end);
    graph.get(end).add(start); // İki yönlü grafik
  }

  public void bfs(String start) {
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      String node = queue.poll();
      System.out.print(node + " ");

      for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
        }
      }
    }

    System.out.println();
  }

  public void dfs(String start) {
    Set<String> visited = new HashSet<>();
    dfsHelper(start, visited);
    System.out.println();
  }

  private void dfsHelper(String node, Set<String> visited) {
    if (visited.contains(node)) return;

    visited.add(node);
    System.out.print(node + " ");

    for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
      dfsHelper(neighbor, visited);
    }
  }
}
