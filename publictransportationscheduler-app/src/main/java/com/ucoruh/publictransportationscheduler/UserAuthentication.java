package com.ucoruh.publictransportationscheduler;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class UserAuthentication {
  private static final String USERS_FILE = "users.bin";
  private HashMap<String, String> users = loadUsers();
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

  public boolean isAuthenticated() {
    return authenticated;
  }

  private void saveUsers() {
    try {
      File file = new File("users.bin");
      // Klasörün var olup olmadığını kontrol et
      File parentDir = file.getParentFile();

      if (parentDir != null && !parentDir.exists()) {
        parentDir.mkdirs(); // Eksik klasörleri oluştur
      }

      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(users);
        System.out.println("Users successfully saved.");
      }
    } catch (IOException e) {
      System.err.println("Error saving users: " + e.getMessage());
    }
  }


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
