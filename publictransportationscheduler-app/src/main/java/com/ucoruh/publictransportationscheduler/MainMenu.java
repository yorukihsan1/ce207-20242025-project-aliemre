package com.ucoruh.publictransportationscheduler;

import java.util.Scanner;

public class MainMenu {

  public void startApplication(Scanner scanner) {
    UserAuthentication auth = new UserAuthentication();
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
          BusAndTrainSchedules schedules = new BusAndTrainSchedules();
          schedules.display(scanner);
          break;

        case 2:
          RoutePlanning routePlanning = new RoutePlanning();
          routePlanning.display(scanner);
          break;

        case 3:
          FareCalculation fareCalculation = new FareCalculation();
          fareCalculation.display(scanner);
          break;

        case 4:
          Alerts alerts = new Alerts();
          alerts.display(scanner);
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
