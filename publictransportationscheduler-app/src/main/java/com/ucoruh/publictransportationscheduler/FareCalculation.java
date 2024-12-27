package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.Heap;
import com.ucoruh.publictransportationscheduler.datastructures.XORLinkedList;
import com.ucoruh.publictransportationscheduler.datastructures.HuffmanCoding;

import java.io.*;
import java.util.Scanner;

public class FareCalculation {
  private static final String FARES_FILE = "fares.bin"; // Ücret geçmişinin saklanacağı dosya
  private Heap fareHeap = new Heap(); // En düşük ücreti bulmak için Heap kullanımı
  private XORLinkedList<Double> fareHistory = new XORLinkedList<>(); // Ücret geçmişi için XOR Linked List
  private HuffmanCoding huffman = new HuffmanCoding(); // Sıkıştırma ve deşifreleme

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

  public void calculateFare(Scanner scanner) {
    System.out.print("Enter distance (in km): ");
    double distance = scanner.nextDouble();
    scanner.nextLine();
    System.out.print("Enter ticket type (1: Standard, 2: Premium): ");
    int ticketType = scanner.nextInt();
    scanner.nextLine();
    double baseRate = 2.0; // Standard ücret birim fiyatı

    if (ticketType == 2) {
      baseRate = 3.5; // Premium ücret birim fiyatı
    }

    double fareAmount = baseRate * distance;
    System.out.println("Calculated Fare: " + fareAmount + " units");
    fareHistory.add(fareAmount); // XOR Linked List'e ekleme
    fareHeap.insert((int) fareAmount); // Heap'e ekleme
    String compressedFare = huffman.compress(String.valueOf(fareAmount));
    System.out.println("Compressed Fare Data: " + compressedFare);
  }

  public void viewFareHistory() {
    if (fareHistory.isEmpty()) {
      System.out.println("No fare history available.");
    } else {
      System.out.println("Fare History:");
      fareHistory.display(); // XOR Linked List'ten geçmişi gösterir
    }
  }

  public void viewLowestFare() {
    if (fareHeap.isEmpty()) {
      System.out.println("No fares available.");
    } else {
      System.out.println("Lowest Fare: " + fareHeap.peekMin() + " units");
    }
  }

  public void saveFares() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FARES_FILE))) {
      oos.writeObject(fareHistory);
      System.out.println("Fares successfully saved.");
    } catch (IOException e) {
      System.err.println("Error saving fares: " + e.getMessage());
    }
  }

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

  // Getter and Setter for testability
  public Heap getFareHeap() {
    return fareHeap;
  }

  public XORLinkedList<Double> getFareHistory() {
    return fareHistory;
  }

  public String getFaresFile() {
    return FARES_FILE;
  }
}
