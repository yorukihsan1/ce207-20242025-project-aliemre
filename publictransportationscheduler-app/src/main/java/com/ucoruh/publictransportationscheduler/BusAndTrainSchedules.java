package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.BPlusTree;
import com.ucoruh.publictransportationscheduler.datastructures.HashTable;
import com.ucoruh.publictransportationscheduler.datastructures.GraphSearch;

import java.io.*;
import java.util.Scanner;

public class BusAndTrainSchedules {
  public static final String ROUTES_FILE = "routes.bin";
  public static final String SCHEDULES_FILE = "schedules.bin";

  public BPlusTree schedules = loadSchedules(); // Zaman çizelgelerini yükler
  public HashTable<Integer, String> routeMap = loadRoutes(); // Rota eşleştirmesi
  public GraphSearch graphSearch = new GraphSearch(); // DFS bağlantı kontrolü için

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
          // Burada scanner'ı parametre olarak almıyoruz, doğru parametreyi gönderiyoruz
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

  // Burada routeKey (int) ve routeDescription (String) parametrelerini alıyoruz
  public void addRoute(int routeKey, String routeDescription) {
    if (routeMap.get(routeKey) == null) {
      routeMap.put(routeKey, routeDescription);
      graphSearch.addNode(routeDescription);
      System.out.println("Route added successfully.");
    } else {
      System.out.println("Route key already exists.");
    }
  }

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

  public void viewDepartureTimes() {
    System.out.println("All Departure Times:");
    schedules.display();
  }

  public void checkConnectivity() {
    System.out.println("Checking connectivity using DFS:");
    graphSearch.dfs("StartPoint"); // Örnek başlangıç düğümü
  }

  public void saveRoutes() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROUTES_FILE))) {
      oos.writeObject(routeMap);
      System.out.println("Routes successfully saved.");
    } catch (IOException e) {
      System.err.println("Error saving routes: " + e.getMessage());
    }
  }

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

  public void saveSchedules() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCHEDULES_FILE))) {
      oos.writeObject(schedules);
      System.out.println("Schedules successfully saved.");
    } catch (IOException e) {
      System.err.println("Error saving schedules: " + e.getMessage());
    }
  }

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

  public HashTable<Integer, String> getRouteMap() {
    return routeMap;
  }

  public BPlusTree getSchedules() {
    return schedules;
  }

}
