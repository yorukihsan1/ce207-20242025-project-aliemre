package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.Heap;
import com.ucoruh.publictransportationscheduler.datastructures.XORLinkedList;
import com.ucoruh.publictransportationscheduler.datastructures.HuffmanCoding;

import java.io.*;
import java.util.Scanner;

/**
 * @class FareCalculation
 * @brief Manages fare calculation, fare history, and lowest fare tracking.
 *
 * This class provides functionalities to calculate fares based on distance and ticket type, 
 * store fare history in an XOR Linked List, and track the lowest fare using a Heap.
 * Fares can also be compressed using Huffman coding for efficient storage.
 */
public class FareCalculation {
  private static final String FARES_FILE = "fares.bin"; /**< The file where fare history is saved. */
  private Heap fareHeap = new Heap(); /**< Heap to manage and find the lowest fare. */
  private XORLinkedList<Double> fareHistory = new XORLinkedList<>(); /**< XOR Linked List to store fare history. */
  HuffmanCoding huffman = new HuffmanCoding(); /**< Huffman coding for compression and decompression. */

  /**
   * @brief Displays the Fare Calculation menu.
   * @param scanner The Scanner object for user input.
   */
  public void display(Scanner scanner) {
    int choice;

    do {
      ConsoleUtils.clearConsole();
      System.out.println("=== Fare Calculation ===");
      System.out.println("1. Calculate New Fare");
      System.out.println("2. View Fare History");
      System.out.println("3. View Lowest Fare");
      System.out.println("4. Save and Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          calculateFare(scanner);
          break;

        case 2:
          viewFareHistory();
          break;

        case 3:
          viewLowestFare();
          break;

        case 4:
          saveFares();
          System.out.println("Fare data saved. Returning to Main Menu...");
          break;

        default:
          System.out.println("Invalid choice. Please try again.");
      }
    } while (choice != 4);
  }

  /**
   * @brief Calculates the fare based on distance and ticket type.
   * @param scanner The Scanner object for user input.
   */
  public void calculateFare(Scanner scanner) {
    System.out.print("Enter distance (in km): ");
    double distance = scanner.nextDouble();
    scanner.nextLine();
    System.out.print("Enter ticket type (1: Standard, 2: Premium): ");
    int ticketType = scanner.nextInt();
    scanner.nextLine();
    double baseRate = 2.0;

    if (ticketType == 2) {
      baseRate = 3.5;
    }

    double fareAmount = baseRate * distance;
    System.out.println("Calculated Fare: " + fareAmount + " units");
    fareHistory.add(fareAmount);
    fareHeap.insert((int) fareAmount);
    String compressedFare = huffman.compress(String.valueOf(fareAmount));
    System.out.println("Compressed Fare Data: " + compressedFare);
  }

  /**
   * @brief Displays the fare history.
   */
  public void viewFareHistory() {
    if (fareHistory.isEmpty()) {
      System.out.println("No fare history available.");
    } else {
      System.out.println("Fare History:");
      fareHistory.display();
    }
  }

  /**
   * @brief Displays the lowest fare.
   */
  public void viewLowestFare() {
    if (fareHeap.isEmpty()) {
      System.out.println("No fares available.");
    } else {
      System.out.println("Lowest Fare: " + fareHeap.peekMin() + " units");
    }
  }

  /**
   * @brief Saves the fare history to a file.
   */
  public void saveFares() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FARES_FILE))) {
      oos.writeObject(fareHistory);
      System.out.println("Fares successfully saved.");
    } catch (IOException e) {
      System.err.println("Error saving fares: " + e.getMessage());
    }
  }

  /**
   * @brief Loads the fare history from a file.
   */
  public void loadFares() {
    File file = new File(FARES_FILE);

    if (!file.exists()) {
      return;
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      fareHistory = (XORLinkedList<Double>) ois.readObject();
      System.out.println("Fare data successfully loaded.");
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading fares: " + e.getMessage());
    }
  }

  /**
   * @brief Gets the fare heap for testing purposes.
   * @return The Heap object containing fares.
   */
  public Heap getFareHeap() {
    return fareHeap;
  }

  /**
   * @brief Gets the fare history for testing purposes.
   * @return The XORLinkedList containing fare history.
   */
  public XORLinkedList<Double> getFareHistory() {
    return fareHistory;
  }

  /**
   * @brief Gets the HuffmanCoding object.
   * @return The HuffmanCoding object used for compression and decompression.
   */
  public HuffmanCoding getHuffman() {
    return huffman;
  }

  /**
   * @brief Gets the file name where fares are saved.
   * @return The name of the fares file.
   */
  public String getFaresFile() {
    return FARES_FILE;
  }
}
