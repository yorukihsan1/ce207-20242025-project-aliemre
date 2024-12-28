package com.ucoruh.publictransportationscheduler;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @brief Handles user authentication tasks such as login, registration, and user data persistence.
 */
public class UserAuthentication {
  private static final String USERS_FILE = "users.bin"; /**< File for storing user data */
  private HashMap<String, String> users = loadUsers(); /**< Map for storing usernames and passwords */
  private boolean authenticated = false; /**< Indicates whether the user is authenticated */

  /**
   * @brief Displays the authentication menu and processes user choices.
   * @param scanner The scanner object for reading user input.
   */
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

  /**
   * @brief Handles user login by validating the provided credentials.
   * @param scanner The scanner object for reading username and password.
   */
  public void login(Scanner scanner) {
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

  /**
   * @brief Registers a new user by storing their credentials.
   * @param scanner The scanner object for reading username and password.
   */
  public void register(Scanner scanner) {
    System.out.print("Enter new username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    if (users.containsKey(username)) {
      System.out.println("Username already exists.");
    } else {
      users.put(username, password);
      saveUsers();
      System.out.println("Registration successful!");
    }
  }

  /**
   * @brief Checks whether the user is authenticated.
   * @return True if the user is authenticated, otherwise false.
   */
  public boolean isAuthenticated() {
    return authenticated;
  }

  /**
   * @brief Saves user data to the specified file.
   */
  private void saveUsers() {
    try {
      File file = new File(USERS_FILE);
      File parentDir = file.getParentFile();

      if (parentDir != null && !parentDir.exists()) {
        parentDir.mkdirs(); // Create parent directories if missing
      }

      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(users);
        System.out.println("Users successfully saved.");
      }
    } catch (IOException e) {
      System.err.println("Error saving users: " + e.getMessage());
    }
  }

  /**
   * @brief Loads user data from the specified file.
   * @return A HashMap containing usernames and passwords.
   */
  @SuppressWarnings("unchecked")
  private HashMap<String, String> loadUsers() {
    File file = new File(USERS_FILE);

    if (!file.exists()) {
      return new HashMap<>();
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      return (HashMap<String, String>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading users: " + e.getMessage());
      return new HashMap<>();
    }
  }
}
