package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.DoubleLinkedList;
import com.ucoruh.publictransportationscheduler.datastructures.HuffmanCoding;
import com.ucoruh.publictransportationscheduler.datastructures.Stack;

import java.io.*;
import java.util.Scanner;

/**
 * @class Alerts
 * @brief Handles delay and disruption alerts for the public transportation scheduler.
 * 
 * This class provides functionality to add, view, undo, save, and load alerts.
 * Alerts are stored in a Double Linked List and can be compressed and decompressed using Huffman coding.
 */
public class Alerts {
  /**
   * @brief Name of the file where alerts are stored.
   */
  public static final String ALERTS_FILE = "alerts.bin"; // File to store alerts

  /**
   * @brief Double Linked List to store alerts.
   */
  public DoubleLinkedList<String> alerts = new DoubleLinkedList<>(); // Double Linked List for alerts

  /**
   * @brief Stack to keep track of alerts for undo operations.
   */
  public Stack<String> undoStack = new Stack<>(); // Stack for undo operations

  /**
   * @brief Huffman coding utility for compression and decompression.
   */
  public HuffmanCoding huffman = new HuffmanCoding(); // Compression and decompression utility

  /**
   * @brief Constructor that initializes alerts by loading from file.
   */
  public Alerts() {
    loadAlerts();
  }

  /**
   * @brief Displays the main menu for alerts and processes user input.
   * 
   * This method provides options to add, view, undo, save, or exit the alerts menu.
   * @param scanner The Scanner object to read user input.
   */
  public void display(Scanner scanner) {
    int choice;

    do {
      ConsoleUtils.clearConsole();
      System.out.println("=== Delay and Disruption Alerts ===");
      System.out.println("1. Add New Alert");
      System.out.println("2. View All Alerts");
      System.out.println("3. Undo Last Alert");
      System.out.println("4. Save and Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          addAlert(scanner);
          break;

        case 2:
          viewAlerts();
          break;

        case 3:
          undoLastAlert();
          break;

        case 4:
          saveAlerts();
          System.out.println("Alerts saved. Returning to Main Menu...");
          break;

        default:
          System.out.println("Invalid choice. Try again.");
      }
    } while (choice != 4);
  }

  /**
   * @brief Adds a new alert to the system.
   * 
   * Prompts the user to enter an alert message, compresses it, and adds it to the alerts list.
   * @param scanner The Scanner object to read the alert message from the user.
   */
  public void addAlert(Scanner scanner) {
    System.out.print("Enter new alert message: ");
    String alertMessage = scanner.nextLine();
    String compressedAlert = huffman.compress(alertMessage);
    alerts.addLast(compressedAlert);
    undoStack.push(compressedAlert);
    System.out.println("Alert added: " + alertMessage);
    System.out.println("Compressed Alert Data: " + compressedAlert);
  }

  /**
   * @brief Displays all alerts in the system.
   * 
   * Decompresses and prints each alert stored in the alerts list.
   */
  public void viewAlerts() {
    if (alerts.isEmpty()) {
      System.out.println("No alerts available.");
    } else {
      System.out.println("All Alerts:");

      for (String alert : alerts) {
        String decompressedAlert = huffman.decompress(alert);
        System.out.println(decompressedAlert);
      }
    }
  }

  /**
   * @brief Removes the last added alert from the system.
   * 
   * Pops the last alert from the undo stack and removes it from the alerts list.
   */
  public void undoLastAlert() {
    if (undoStack.isEmpty()) {
      System.out.println("No alerts to undo.");
    } else {
      String lastAlert = undoStack.pop();
      alerts.remove(lastAlert);
      System.out.println("Last alert undone.");
    }
  }

  /**
   * @brief Saves all alerts to a file.
   * 
   * Serializes the alerts list and writes it to the specified file.
   */
  public void saveAlerts() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ALERTS_FILE))) {
      oos.writeObject(alerts);
      System.out.println("Alerts successfully saved.");
    } catch (IOException e) {
      System.err.println("Error saving alerts: " + e.getMessage());
    }
  }

  /**
   * @brief Loads alerts from a file into memory.
   * 
   * Reads the serialized alerts list from the specified file and deserializes it.
   */
  @SuppressWarnings("unchecked")
  public void loadAlerts() {
    File file = new File(ALERTS_FILE);

    if (!file.exists()) {
      return;
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      alerts = (DoubleLinkedList<String>) ois.readObject();
      System.out.println("Alerts successfully loaded.");
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading alerts: " + e.getMessage());
    }
  }

  /**
   * @brief Retrieves the alerts list.
   * 
   * Provides access to the current alerts stored in memory.
   * @return A DoubleLinkedList containing all the alerts.
   */
  public DoubleLinkedList<String> getAlerts() {
    return alerts;
  }
}
