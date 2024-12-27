package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.DoubleLinkedList;
import com.ucoruh.publictransportationscheduler.datastructures.HuffmanCoding;
import com.ucoruh.publictransportationscheduler.datastructures.Stack;

import java.io.*;
import java.util.Scanner;

public class Alerts {
  public static final String ALERTS_FILE = "alerts.bin"; // Uyarıların saklanacağı dosya
  public DoubleLinkedList<String> alerts = new DoubleLinkedList<>(); // Uyarılar için Double Linked List
  public Stack<String> undoStack = new Stack<>(); // Geri alma işlemi için Stack
  public HuffmanCoding huffman = new HuffmanCoding(); // Sıkıştırma ve deşifreleme

  public Alerts() {
    loadAlerts(); // Uygulama başlatıldığında uyarıları yükler
  }

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

  public void addAlert(Scanner scanner) {
    System.out.print("Enter new alert message: ");
    String alertMessage = scanner.nextLine();
    // Sıkıştırılmış uyarıyı elde et
    String compressedAlert = huffman.compress(alertMessage);
    // Uyarıyı Double Linked List'e ekle
    alerts.addLast(compressedAlert);
    // Geri alma işlemleri için Stack'e ekle
    undoStack.push(compressedAlert);
    System.out.println("Alert added: " + alertMessage);
    System.out.println("Compressed Alert Data: " + compressedAlert);
  }

  public void viewAlerts() {
    if (alerts.isEmpty()) {
      System.out.println("No alerts available.");
    } else {
      System.out.println("All Alerts:");

      // alerts üzerinde for-each döngüsü ile gezilecektir
      for (String alert : alerts) {
        // Her bir uyarıyı deşifre et ve yazdır
        String decompressedAlert = huffman.decompress(alert);
        System.out.println(decompressedAlert);
      }
    }
  }

  public void undoLastAlert() {
    if (undoStack.isEmpty()) {
      System.out.println("No alerts to undo.");
    } else {
      String lastAlert = undoStack.pop(); // Son eklenen uyarıyı yığından al
      alerts.remove(lastAlert); // Çift bağlı listeden kaldır
      System.out.println("Last alert undone.");
    }
  }

  public void saveAlerts() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ALERTS_FILE))) {
      oos.writeObject(alerts);
      System.out.println("Alerts successfully saved.");
    } catch (IOException e) {
      System.err.println("Error saving alerts: " + e.getMessage());
    }
  }

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

  public DoubleLinkedList<String> getAlerts() {
    return alerts;
  }
}
