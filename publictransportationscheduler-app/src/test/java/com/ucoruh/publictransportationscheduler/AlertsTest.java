package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.DoubleLinkedList;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * @brief Unit tests for the Alerts class to ensure correct functionality.
 */
public class AlertsTest {

  private Alerts alerts;
  private Scanner mockScanner;

  /**
   * @brief Sets up the Alerts object and mock Scanner before each test.
   */
  @Before
  public void setUp() {
    alerts = new Alerts();
    mockScanner = new Scanner(System.in);
  }

  /**
   * @brief Tests the addAlert method to ensure alerts are added correctly.
   */
  @Test
  public void testAddAlert() {
    String testMessage = "Train delay";
    alerts.addAlert(new Scanner(testMessage));
    assertTrue(alerts.getAlerts().contains(alerts.huffman.compress(testMessage)));
  }

  /**
   * @brief Tests the viewAlerts method to ensure alerts are displayed correctly.
   */
  @Test
  public void testViewAlerts() {
    String testMessage = "Train delayed";
    alerts.addAlert(new Scanner(testMessage));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    try {
      alerts.viewAlerts();
      assertFalse(outContent.toString().contains(testMessage));
    } finally {
      System.setOut(originalOut);
    }
  }

  /**
   * @brief Tests the undoLastAlert method to ensure the last alert is removed correctly.
   */
  @Test
  public void testUndoLastAlert() {
    String testMessage = "Service disruption on Line 5";
    alerts.addAlert(new Scanner(testMessage));
    alerts.undoLastAlert();
    assertFalse(alerts.getAlerts().isEmpty());
  }

  /**
   * @brief Tests the undoLastAlert method when there are no alerts to undo.
   */
  @Test
  public void testUndoLastAlertEmptyStack() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    try {
      alerts.undoLastAlert();
      assertTrue(outContent.toString().contains("No alerts to undo."));
    } finally {
      System.setOut(originalOut);
    }
  }

  /**
   * @brief Tests the saveAlerts method to ensure alerts are saved to a file.
   */
  @Test
  public void testSaveAlerts() {
    String testMessage = "Service suspension";
    alerts.addAlert(new Scanner(testMessage));
    alerts.saveAlerts();
    File file = new File(Alerts.ALERTS_FILE);
    assertTrue(file.exists());
  }

  /**
   * @brief Tests the loadAlerts method to ensure alerts are loaded from a file.
   */
  @Test
  public void testLoadAlerts() {
    String testMessage = "Train cancellation";
    alerts.addAlert(new Scanner(testMessage));
    alerts.saveAlerts();
    alerts.loadAlerts();
    assertFalse(alerts.getAlerts().isEmpty());
  }

  /**
   * @brief Tests the display menu with a valid user choice.
   */
  @Test
  public void testDisplayMenuWithValidChoice() {
    Scanner scanner = new Scanner("1\nAlert message\n4\n");
    alerts.display(scanner);
    assertFalse(alerts.getAlerts().isEmpty());
  }

  /**
   * @brief Tests the display menu with an invalid user choice.
   */
  @Test
  public void testDisplayMenuWithInvalidChoice() {
    Scanner scanner = new Scanner("0\n4\n");
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    try {
      alerts.display(scanner);
      assertTrue(outContent.toString().contains("Invalid choice. Try again."));
    } finally {
      System.setOut(originalOut);
    }
  }
}
