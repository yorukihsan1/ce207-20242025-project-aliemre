package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.Serializable;
import java.util.*;
import java.util.Queue;

/**
 * @brief A class representing a graph structure and implementing search algorithms such as BFS and DFS.
 */
public class GraphSearch implements Serializable {
  private static final long serialVersionUID = 1L; /**< Unique identifier for serialization */
  private Map<String, List<String>> adjacencyList = new HashMap<>(); /**< Represents the adjacency list of the graph */

  /**
   * @brief Adds a node to the graph.
   * @param node The node to be added.
   */
  public void addNode(String node) {
    adjacencyList.putIfAbsent(node, new ArrayList<>());
  }

  /**
   * @brief Adds an edge between two nodes in the graph.
   * @param source The starting node of the edge.
   * @param destination The ending node of the edge.
   */
  public void addEdge(String source, String destination) {
    adjacencyList.putIfAbsent(source, new ArrayList<>());
    adjacencyList.putIfAbsent(destination, new ArrayList<>());
    adjacencyList.get(source).add(destination);
    adjacencyList.get(destination).add(source); // For undirected graph
  }

  /**
   * @brief Performs Breadth-First Search (BFS) starting from a given node.
   * @param start The starting node for BFS.
   */
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

  /**
   * @brief Performs Depth-First Search (DFS) starting from a given node.
   * @param start The starting node for DFS.
   */
  public void dfs(String start) {
    if (!adjacencyList.containsKey(start)) {
      System.out.println("Start node not found.");
      return;
    }

    Set<String> visited = new HashSet<>();
    dfsRecursive(start, visited);
  }

  /**
   * @brief Helper method for recursive Depth-First Search (DFS).
   * @param node The current node being visited.
   * @param visited The set of visited nodes.
   */
  public void dfsRecursive(String node, Set<String> visited) {
    visited.add(node);
    System.out.println("Visited: " + node);

    for (String neighbor : adjacencyList.get(node)) {
      if (!visited.contains(neighbor)) {
        dfsRecursive(neighbor, visited);
      }
    }
  }

  /**
   * @brief Retrieves the list of nodes in the graph.
   * @return A list of nodes in the graph.
   */
  public List<String> getNodes() {
    return new ArrayList<>(adjacencyList.keySet());
  }

  /**
   * @brief Sets the list of nodes for the graph.
   * @param nodes A list of nodes to be added to the graph.
   */
  public void setNodes(List<String> nodes) {
    for (String node : nodes) {
      addNode(node);
    }
  }

  /**
   * @brief Retrieves the list of edges in the graph.
   * @return A list of edges, where each edge is represented as a string array of size 2.
   */
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

  /**
   * @brief Sets the edges for the graph.
   * @param edges A list of edges, where each edge is represented as a string array of size 2.
   */
  public void setEdges(List<String[]> edges) {
    for (String[] edge : edges) {
      if (edge.length == 2) {
        addEdge(edge[0], edge[1]);
      }
    }
  }
}
