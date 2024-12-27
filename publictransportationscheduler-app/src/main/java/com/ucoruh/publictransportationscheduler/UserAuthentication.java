package com.ucoruh.publictransportationscheduler;

import java.util.HashMap;
import java.util.Scanner;

public class UserAuthentication {
  private HashMap<String, String> users = new HashMap<>();
  private boolean authenticated = false;

  public void display(Scanner scanner) {
    System.out.println("1. Login");
    System.out.println("2. Register");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 1:
        login(scanner);
        break;

      case 2:
        register(scanner);
        break;

      default:
        System.out.println("Invalid choice. Please try again.");
    }
  }

  private void login(Scanner scanner) {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    if (users.containsKey(username) && users.get(username).equals(password)) {
      System.out.println("Login successful! Welcome, " + username);
      authenticated = true;
    } else {
      System.out.println("Invalid username or password.");
    }
  }

  private void register(Scanner scanner) {
    System.out.print("Enter new username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    if (users.containsKey(username)) {
      System.out.println("Username already exists.");
    } else {
      users.put(username, password);
      System.out.println("Registration successful!");
    }
  }

  public boolean isAuthenticated() {
    return authenticated;
  }
}
