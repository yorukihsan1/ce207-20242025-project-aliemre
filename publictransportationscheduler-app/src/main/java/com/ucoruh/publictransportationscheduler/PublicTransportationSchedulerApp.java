package com.ucoruh.publictransportationscheduler;

import java.util.Scanner;

public class PublicTransportationSchedulerApp {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    MainMenu menu = new MainMenu();
    menu.startApplication(scanner);
    scanner.close();
  }
}
