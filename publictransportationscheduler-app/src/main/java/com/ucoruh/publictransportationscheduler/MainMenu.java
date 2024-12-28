package com.ucoruh.publictransportationscheduler;

import java.util.Scanner;

/**
 * @brief Main menu for the Public Transportation Scheduler application.
 * Handles user authentication and navigation to different functionalities such as
 * bus/train schedules, route planning, fare calculation, and alerts management.
 */
public class MainMenu {

  /**
   * @brief Starts the application by authenticating the user and showing the main menu.
   * @param scanner A Scanner object to read user input.
   */
  public void startApplication(Scanner scanner) {
    UserAuthentication auth = createUserAuthentication();
    boolean isAuthenticated = false;

    // User authentication
    do {
      ConsoleUtils.clearConsole();
      System.out.println("=== User Authentication ===");
      auth.display(scanner);
      isAuthenticated = auth.isAuthenticated();
    } while (!isAuthenticated);

    // Show main menu if authentication is successful
    if (isAuthenticated) {
      showMainMenu(scanner);
    }
  }

  /**
   * @brief Factory method to create a UserAuthentication instance.
   * @return A new UserAuthentication object.
   */
  protected UserAuthentication createUserAuthentication() {
    return new UserAuthentication();
  }

  /**
   * @brief Factory method to create a BusAndTrainSchedules instance.
   * @return A new BusAndTrainSchedules object.
   */
  protected BusAndTrainSchedules createBusAndTrainSchedules() {
    return new BusAndTrainSchedules();
  }

  /**
   * @brief Factory method to create a RoutePlanning instance.
   * @return A new RoutePlanning object.
   */
  protected RoutePlanning createRoutePlanning() {
    return new RoutePlanning();
  }

  /**
   * @brief Factory method to create a FareCalculation instance.
   * @return A new FareCalculation object.
   */
  protected FareCalculation createFareCalculation() {
    return new FareCalculation();
  }

  /**
   * @brief Factory method to create an Alerts instance.
   * @return A new Alerts object.
   */
  protected Alerts createAlerts() {
    return new Alerts();
  }

  /**
   * @brief Displays the main menu and allows the user to navigate between different functionalities.
   * @param scanner A Scanner object to read user input.
   */
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
