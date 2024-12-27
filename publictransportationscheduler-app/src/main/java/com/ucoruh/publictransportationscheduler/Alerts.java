package com.ucoruh.publictransportationscheduler;

import java.util.LinkedList;
import java.util.Scanner;

public class Alerts {
  private LinkedList<String> alerts = new LinkedList<>();

  public void display(Scanner scanner) {
    int choice;

    do {
      System.out.println("=== Delay and Disruption Alerts ===");
      System.out.println("1. View Alerts");
      System.out.println("2. Add Alert (Admin)");
      System.out.println("3. Remove Oldest Alert");
      System.out.println("4. Back to Main Menu");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          viewAlerts();
          break;

        case 2:
          addAlert(scanner);
          break;

        case 3:
          removeOldestAlert();
          break;

        case 4:
          System.out.println("Returning to Main Menu...");
          break;

        default:
          System.out.println("Invalid choice. Try again.");
      }
    } while (choice != 4);
  }

  private void viewAlerts() {
    if (alerts.isEmpty()) {
      System.out.println("No alerts available.");
    } else {
      System.out.println("Current Alerts:");

      for (String alert : alerts) {
        System.out.println("- " + alert);
      }
    }
  }

  private void addAlert(Scanner scanner) {
    System.out.print("Enter new alert: ");
    String alert = scanner.nextLine();
    alerts.add(alert);
    System.out.println("Alert added successfully!");
  }

  private void removeOldestAlert() {
    if (!alerts.isEmpty()) {
      String removed = alerts.removeFirst();
      System.out.println("Removed oldest alert: " + removed);
    } else {
      System.out.println("No alerts to remove.");
    }
  }
}
