package com.ucoruh.publictransportationscheduler;

import java.util.PriorityQueue;
import java.util.Scanner;

public class FareCalculation {
  private PriorityQueue<Fare> fareQueue = new PriorityQueue<>((f1, f2) -> Double.compare(f1.distance, f2.distance));

  public void display(Scanner scanner) {
    int choice;

    do {
      System.out.println("=== Fare Calculation ===");
      System.out.println("1. Estimate Fare");
      System.out.println("2. Add Fare Rule (Admin)");
      System.out.println("3. Back to Main Menu");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          estimateFare(scanner);
          break;

        case 2:
          addFareRule(scanner);
          break;

        case 3:
          System.out.println("Returning to Main Menu...");
          break;

        default:
          System.out.println("Invalid choice. Try again.");
      }
    } while (choice != 3);
  }

  private void estimateFare(Scanner scanner) {
    System.out.print("Enter distance (in km): ");
    double distance = scanner.nextDouble();
    scanner.nextLine();
    Fare closestFare = null;

    for (Fare fare : fareQueue) {
      if (fare.distance >= distance) {
        closestFare = fare;
        break;
      }
    }

    if (closestFare != null) {
      System.out.println("Estimated Fare: " + closestFare.price + " units for " + distance + " km.");
    } else {
      System.out.println("No matching fare rule found.");
    }
  }

  private void addFareRule(Scanner scanner) {
    System.out.print("Enter distance (in km): ");
    double distance = scanner.nextDouble();
    System.out.print("Enter price (in units): ");
    double price = scanner.nextDouble();
    scanner.nextLine();
    fareQueue.add(new Fare(distance, price));
    System.out.println("Fare rule added successfully!");
  }

  static class Fare {
    double distance;
    double price;

    Fare(double distance, double price) {
      this.distance = distance;
      this.price = price;
    }
  }
}
