package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.DoubleLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AlertsTest {

  private Alerts alerts;
  private Scanner mockScanner;

  @BeforeEach
  public void setUp() {
    alerts = new Alerts();
    mockScanner = new Scanner(System.in);
  }

  @Test
  public void testAddAlert() {
    String testMessage = "Train delay";
    alerts.addAlert(new Scanner(testMessage));
    assertTrue(alerts.getAlerts().contains(alerts.huffman.compress(testMessage)));
  }

  @Test
  public void testViewAlerts() {
    String testMessage = "Train delayed";
    alerts.addAlert(new Scanner(testMessage));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    try {
      alerts.viewAlerts();
      assertTrue(outContent.toString().contains(testMessage));
    }

    finally {
      System.setOut(originalOut);
    }
  }

  @Test
  public void testUndoLastAlert() {
    String testMessage = "Service disruption on Line 5";
    alerts.addAlert(new Scanner(testMessage));
    alerts.undoLastAlert();
    assertTrue(alerts.getAlerts().isEmpty());
  }

  @Test
  public void testUndoLastAlertEmptyStack() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    try {
      alerts.undoLastAlert();
      assertTrue(outContent.toString().contains("No alerts to undo."));
    }

    finally {
      System.setOut(originalOut);
    }
  }

  @Test
  public void testSaveAlerts() {
    String testMessage = "Service suspension";
    alerts.addAlert(new Scanner(testMessage));
    alerts.saveAlerts();
    File file = new File(Alerts.ALERTS_FILE);
    assertTrue(file.exists());
  }

  @Test
  public void testLoadAlerts() {
    String testMessage = "Train cancellation";
    alerts.addAlert(new Scanner(testMessage));
    alerts.saveAlerts();
    alerts.loadAlerts();
    assertFalse(alerts.getAlerts().isEmpty());
  }

  @Test
  public void testDisplayMenuWithValidChoice() {
    // Simulate valid menu navigation
    Scanner scanner = new Scanner("1\nAlert message\n4\n");
    alerts.display(scanner);
    assertFalse(alerts.getAlerts().isEmpty());
  }

  @Test
  public void testDisplayMenuWithInvalidChoice() {
    Scanner scanner = new Scanner("0\n4\n");
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    try {
      alerts.display(scanner);
      assertTrue(outContent.toString().contains("Invalid choice. Try again."));
    }

    finally {
      System.setOut(originalOut);
    }
  }
}
