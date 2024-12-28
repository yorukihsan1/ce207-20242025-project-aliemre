package com.ucoruh.publictransportationscheduler;

import java.util.Scanner;

public class PublicTransportationSchedulerApp {

  public static void main(String[] args) {
    new PublicTransportationSchedulerApp().run();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    MainMenu menu = createMainMenu();
    menu.startApplication(scanner);
    scanner.close();
    ConsoleUtils.clearConsole();
  }

  // Allow mocking of MainMenu for testing
  public MainMenu createMainMenu() {
    return new MainMenu();
  }
}
