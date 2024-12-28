package com.ucoruh.publictransportationscheduler;

import java.util.Scanner;

public class MainMenu {

  public void startApplication(Scanner scanner) {
    UserAuthentication auth = createUserAuthentication();
    boolean isAuthenticated = false;

    // Kullanıcı doğrulama
    do {
      ConsoleUtils.clearConsole();
      System.out.println("=== User Authentication ===");
      auth.display(scanner);
      isAuthenticated = auth.isAuthenticated();
    } while (!isAuthenticated);

    // Ana Menü
    if (isAuthenticated) {
      showMainMenu(scanner);
    }
  }

  protected UserAuthentication createUserAuthentication() {
    return new UserAuthentication();
  }

  protected BusAndTrainSchedules createBusAndTrainSchedules() {
    return new BusAndTrainSchedules();
  }

  protected RoutePlanning createRoutePlanning() {
    return new RoutePlanning();
  }

  protected FareCalculation createFareCalculation() {
    return new FareCalculation();
  }

  protected Alerts createAlerts() {
    return new Alerts();
  }

  private void showMainMenu(Scanner scanner) {
    int choice;

    do {
      ConsoleUtils.clearConsole();
      System.out.println("=== Main Menu ===");
      System.out.println("1. Bus and Train Schedules");
      System.out.println("2. Route Planning");
      System.out.println("3. Fare Calculation");
      System.out.println("4. Delay and Disruption Alerts");
      System.out.println("5. Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          createBusAndTrainSchedules().display(scanner);
          break;

        case 2:
          createRoutePlanning().display(scanner);
          break;

        case 3:
          createFareCalculation().display(scanner);
          break;

        case 4:
          createAlerts().display(scanner);
          break;

        case 5:
          System.out.println("Exiting... Goodbye!");
          break;

        default:
          System.out.println("Invalid choice. Try again.");
      }
    } while (choice != 5);
  }
}
