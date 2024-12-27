package com.ucoruh.publictransportationscheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BusAndTrainSchedules {
  private Map<String, String> schedules = new HashMap<>(); // Rota -> Zaman eşleştirmesi

  public void display(Scanner scanner) {
    int choice;

    do {
      System.out.println("=== Bus and Train Schedules ===");
      System.out.println("1. Search for Routes");
      System.out.println("2. View All Schedules");
      System.out.println("3. Add Schedule (Admin)");
      System.out.println("4. Back to Main Menu");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          searchForRoutes(scanner);
          break;

        case 2:
          viewAllSchedules();
          break;

        case 3:
          addSchedule(scanner);
          break;

        case 4:
          System.out.println("Returning to Main Menu...");
          break;

        default:
          System.out.println("Invalid choice. Try again.");
      }
    } while (choice != 4);
  }

  private void searchForRoutes(Scanner scanner) {
    System.out.print("Enter route name to search: ");
    String route = scanner.nextLine();

    if (schedules.containsKey(route)) {
      System.out.println("Route found: " + route + " - " + schedules.get(route));
    } else {
      System.out.println("No matching route found.");
    }
  }

  private void viewAllSchedules() {
    if (schedules.isEmpty()) {
      System.out.println("No schedules available.");
    } else {
      for (Map.Entry<String, String> entry : schedules.entrySet()) {
        System.out.println("Route: " + entry.getKey() + ", Time: " + entry.getValue());
      }
    }
  }

  private void addSchedule(Scanner scanner) {
    System.out.print("Enter route name: ");
    String route = scanner.nextLine();
    System.out.print("Enter schedule time: ");
    String time = scanner.nextLine();
    schedules.put(route, time);
    System.out.println("Schedule added successfully!");
  }
}
