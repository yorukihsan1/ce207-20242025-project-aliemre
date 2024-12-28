package com.ucoruh.publictransportationscheduler;

import java.util.Scanner;

/**
 * @brief Entry point for the Public Transportation Scheduler application.
 *        Handles application initialization and execution.
 */
public class PublicTransportationSchedulerApp {

  /**
   * @brief The main method to start the application.
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    new PublicTransportationSchedulerApp().run();
  }

  /**
   * @brief Runs the application, initializes components, and starts the main menu.
   */
  public void run() {
    Scanner scanner = new Scanner(System.in);
    MainMenu menu = createMainMenu();
    menu.startApplication(scanner);
    scanner.close();
    ConsoleUtils.clearConsole();
  }

  /**
   * @brief Creates an instance of the MainMenu class.
   *        This method can be overridden for testing purposes.
   * @return A new instance of MainMenu.
   */
  public MainMenu createMainMenu() {
    return new MainMenu();
  }
}
