package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.GraphSearch;

import java.io.*;
import java.util.Scanner;

public class RoutePlanning {
  public static final String NODES_FILE = "route_nodes.bin";
  public static final String EDGES_FILE = "route_edges.bin";

  GraphSearch graphSearch = new GraphSearch();

  public RoutePlanning() {
    loadGraph();
  }

  public void display(Scanner scanner) {
    int choice;

    do {
      ConsoleUtils.clearConsole();
      System.out.println("=== Route Planning ===");
      System.out.println("1. Add Starting Point");
      System.out.println("2. Add Ending Point");
      System.out.println("3. View Suggested Routes (BFS)");
      System.out.println("4. Explore Alternative Routes (DFS)");
      System.out.println("5. Save and Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          addNode(scanner, "Starting");
          break;

        case 2:
          addNode(scanner, "Ending");
          break;

        case 3:
          viewSuggestedRoutes(scanner);
          break;

        case 4:
          exploreAlternativeRoutes(scanner);
          break;

        case 5:
          saveGraph();
          System.out.println("Graph data saved. Returning to Main Menu...");
          break;

        default:
          System.out.println("Invalid choice. Please try again.");
      }
    } while (choice != 5);
  }

  public void addNode(Scanner scanner, String type) {
    System.out.print("Enter " + type + " Point: ");
    String node = scanner.nextLine();
    graphSearch.addNode(node);

    if (type.equals("Ending")) {
      System.out.print("Enter starting point to connect: ");
      String startPoint = scanner.nextLine();
      graphSearch.addEdge(startPoint, node);
      System.out.println("Connection added: " + startPoint + " -> " + node);
    }

    System.out.println(type + " Point Added: " + node);
  }

  public void viewSuggestedRoutes(Scanner scanner) {
    System.out.print("Enter starting point for BFS: ");
    String startPoint = scanner.nextLine();
    System.out.println("Performing BFS for suggested routes:");
    graphSearch.bfs(startPoint);
  }

  public void exploreAlternativeRoutes(Scanner scanner) {
    System.out.print("Enter starting point for DFS: ");
    String startPoint = scanner.nextLine();
    System.out.println("Exploring alternative routes using DFS:");
    graphSearch.dfs(startPoint);
  }

  public void saveGraph() {
    try (ObjectOutputStream oosNodes = new ObjectOutputStream(new FileOutputStream(NODES_FILE));
           ObjectOutputStream oosEdges = new ObjectOutputStream(new FileOutputStream(EDGES_FILE))) {
      oosNodes.writeObject(graphSearch.getNodes());
      oosEdges.writeObject(graphSearch.getEdges());
      System.out.println("Graph data successfully saved.");
    } catch (IOException e) {
      System.err.println("Error saving graph data: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked") void loadGraph() {
    File nodesFile = new File(NODES_FILE);
    File edgesFile = new File(EDGES_FILE);

    if (nodesFile.exists() && edgesFile.exists()) {
      try (ObjectInputStream oisNodes = new ObjectInputStream(new FileInputStream(nodesFile));
             ObjectInputStream oisEdges = new ObjectInputStream(new FileInputStream(edgesFile))) {
        graphSearch.setNodes((java.util.List<String>) oisNodes.readObject());
        graphSearch.setEdges((java.util.List<String[]>) oisEdges.readObject());
        System.out.println("Graph data successfully loaded.");
      } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error loading graph data: " + e.getMessage());
      }
    } else {
      System.out.println("No existing graph data found. Starting fresh.");
    }
  }
}
