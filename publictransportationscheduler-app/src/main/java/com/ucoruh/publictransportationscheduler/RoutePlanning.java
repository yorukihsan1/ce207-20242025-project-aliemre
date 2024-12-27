package com.ucoruh.publictransportationscheduler;

import java.util.*;

public class RoutePlanning {
  private Map<String, List<String>> graph = new HashMap<>();

  public void display(Scanner scanner) {
    int choice;

    do {
      System.out.println("=== Route Planning ===");
      System.out.println("1. Add Route");
      System.out.println("2. Get Route Suggestions");
      System.out.println("3. Back to Main Menu");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          addRoute(scanner);
          break;

        case 2:
          getRouteSuggestions(scanner);
          break;

        case 3:
          System.out.println("Returning to Main Menu...");
          break;

        default:
          System.out.println("Invalid choice. Try again.");
      }
    } while (choice != 3);
  }

  private void addRoute(Scanner scanner) {
    System.out.print("Enter starting point: ");
    String start = scanner.nextLine();
    System.out.print("Enter destination: ");
    String end = scanner.nextLine();
    graph.putIfAbsent(start, new ArrayList<>());
    graph.get(start).add(end);
    System.out.println("Route added: " + start + " -> " + end);
  }

  private void getRouteSuggestions(Scanner scanner) {
    System.out.print("Enter starting point: ");
    String start = scanner.nextLine();
    System.out.println("Routes from " + start + ":");
    bfs(start);
  }

  private void bfs(String start) {
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      String node = queue.poll();
      System.out.println(node);

      for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
        }
      }
    }
  }
}
