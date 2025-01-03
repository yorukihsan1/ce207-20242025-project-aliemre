package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.BPlusTree;
import com.ucoruh.publictransportationscheduler.datastructures.HashTable;
import com.ucoruh.publictransportationscheduler.datastructures.GraphSearch;

import java.io.*;
import java.util.Scanner;

/**
 * @class BusAndTrainSchedules
 * @brief Manages bus and train schedules, including routes, departure times, and connectivity checks.
 *
 * This class provides functionality for managing schedules and routes using various data structures
 * like BPlusTree, HashTable, and GraphSearch. It supports adding routes, viewing schedules, 
 * and checking connectivity through Depth-First Search (DFS).
 */
public class BusAndTrainSchedules {
  /**
   * @brief Name of the file where routes are stored.
   */
  public static final String ROUTES_FILE = "routes.bin";

  /**
   * @brief Name of the file where schedules are stored.
   */
  public static final String SCHEDULES_FILE = "schedules.bin";

  /**
   * @brief BPlusTree to store schedules.
   */
  public BPlusTree schedules = loadSchedules(); // Load schedules

  /**
   * @brief HashTable for route mapping.
   */
  public HashTable<Integer, String> routeMap = loadRoutes(); // Route mapping

  /**
   * @brief GraphSearch utility for connectivity checks.
   */
  public GraphSearch graphSearch = new GraphSearch(); // DFS for connectivity checks

  /**
   * @brief Displays the menu for managing bus and train schedules and handles user input.
   *
   * Provides options to search for routes, view departure times, check connectivity, add routes, or save and exit.
   * @param scanner The Scanner object to read user input.
   */
  public void display(Scanner scanner) {
    int choice;

    do {
      ConsoleUtils.clearConsole();
      System.out.println("=== Bus and Train Schedules ===");
      System.out.println("1. Search for Routes");
      System.out.println("2. View Departure Times");
      System.out.println("3. Check Connectivity (DFS)");
      System.out.println("4. Add New Route");
      System.out.println("5. Save and Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          searchForRoutes(scanner);
          break;

        case 2:
          viewDepartureTimes();
          break;

        case 3:
          checkConnectivity();
          break;

        case 4:
          System.out.print("Enter route key: ");
          int routeKey = scanner.nextInt();
          scanner.nextLine();
          System.out.print("Enter route description: ");
          String routeDescription = scanner.nextLine();
          addRoute(routeKey, routeDescription);
          break;

        case 5:
          saveRoutes();
          saveSchedules();
          System.out.println("Data saved. Returning to Main Menu...");
          break;

        default:
          System.out.println("Invalid choice. Try again.");
      }
    } while (choice != 5);
  }

  /**
   * @brief Adds a new route to the route map and graph.
   *
   * Ensures the route key is unique before adding it to the route map and graph.
   * @param routeKey The unique key of the route.
   * @param routeDescription The description of the route.
   */
  public void addRoute(int routeKey, String routeDescription) {
    if (routeMap.get(routeKey) == null) {
      routeMap.put(routeKey, routeDescription);
      graphSearch.addNode(routeDescription);
      System.out.println("Route added successfully.");
    } else {
      System.out.println("Route key already exists.");
    }
  }

  /**
   * @brief Searches for a route by its key and displays the result.
   *
   * Prompts the user to enter a route key and retrieves the corresponding route description.
   * @param scanner The Scanner object to read the route key.
   */
  public void searchForRoutes(Scanner scanner) {
    System.out.print("Enter route key to search: ");
    int routeKey = scanner.nextInt();
    scanner.nextLine();
    String routeDescription = routeMap.get(routeKey);

    if (routeDescription != null) {
      System.out.println("Route found: " + routeDescription);
    } else {
      System.out.println("Route not found.");
    }
  }

  /**
   * @brief Displays all departure times from the schedules.
   *
   * Outputs the contents of the BPlusTree containing schedules.
   */
  public void viewDepartureTimes() {
    System.out.println("All Departure Times:");
    schedules.display();
  }

  /**
   * @brief Checks graph connectivity using Depth-First Search (DFS).
   *
   * Uses GraphSearch to traverse and check connectivity starting from a given node.
   */
  public void checkConnectivity() {
    System.out.println("Checking connectivity using DFS:");
    graphSearch.dfs("StartPoint");
  }

  /**
   * @brief Saves all routes to the file.
   *
   * Serializes the HashTable containing route mappings and writes it to the specified file.
   */
  public void saveRoutes() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROUTES_FILE))) {
      oos.writeObject(routeMap);
      System.out.println("Routes successfully saved.");
    } catch (IOException e) {
      System.err.println("Error saving routes: " + e.getMessage());
    }
  }

  /**
   * @brief Loads routes from the file.
   *
   * Reads the serialized HashTable from the file and returns it.
   * @return A HashTable containing all the loaded routes.
   */
  public HashTable<Integer, String> loadRoutes() {
    File file = new File(ROUTES_FILE);

    if (!file.exists()) {
      return new HashTable<>();
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      return (HashTable<Integer, String>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading routes: " + e.getMessage());
      return new HashTable<>();
    }
  }

  /**
   * @brief Saves all schedules to the file.
   *
   * Serializes the BPlusTree containing schedules and writes it to the specified file.
   */
  public void saveSchedules() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCHEDULES_FILE))) {
      oos.writeObject(schedules);
      System.out.println("Schedules successfully saved.");
    } catch (IOException e) {
      System.err.println("Error saving schedules: " + e.getMessage());
    }
  }

  /**
   * @brief Loads schedules from the file.
   *
   * Reads the serialized BPlusTree from the file and returns it.
   * If the file doesn't exist, initializes a new BPlusTree.
   * @return A BPlusTree containing all the loaded schedules.
   */
  public BPlusTree loadSchedules() {
    File file = new File(SCHEDULES_FILE);

    if (!file.exists()) {
      return new BPlusTree(3);
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      return (BPlusTree) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading schedules: " + e.getMessage());
      return new BPlusTree(3);
    }
  }

  /**
   * @brief Retrieves the route map.
   *
   * Provides access to the current route mappings stored in memory.
   * @return The HashTable containing the route mappings.
   */
  public HashTable<Integer, String> getRouteMap() {
    return routeMap;
  }

  /**
   * @brief Retrieves the schedules.
   *
   * Provides access to the current schedules stored in memory.
   * @return The BPlusTree containing the schedules.
   */
  public BPlusTree getSchedules() {
    return schedules;
  }
}
