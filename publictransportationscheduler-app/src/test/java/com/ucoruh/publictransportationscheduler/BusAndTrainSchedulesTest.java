package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.BPlusTree;
import com.ucoruh.publictransportationscheduler.datastructures.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BusAndTrainSchedulesTest {

  private BusAndTrainSchedules busAndTrainSchedules;
  private Scanner scanner;

  @BeforeEach
  public void setUp() {
    busAndTrainSchedules = new BusAndTrainSchedules();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testAddRoute() {
    int routeKey = 101;
    String routeDescription = "Route 101";
    busAndTrainSchedules.addRoute(routeKey, routeDescription);
    assertEquals(routeDescription, busAndTrainSchedules.getRouteMap().get(routeKey));
  }

  @Test
  public void testAddExistingRoute() {
    int routeKey = 101;
    String routeDescription = "Route 101";
    busAndTrainSchedules.addRoute(routeKey, routeDescription);
    busAndTrainSchedules.addRoute(routeKey, "New Route");
    assertEquals(routeDescription, busAndTrainSchedules.getRouteMap().get(routeKey));
  }

  @Test
  public void testSearchForRoutes() {
    int routeKey = 101;
    String routeDescription = "Route 101";
    busAndTrainSchedules.addRoute(routeKey, routeDescription);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    Scanner testScanner = new Scanner(routeKey + "\n");
    busAndTrainSchedules.searchForRoutes(testScanner);
    assertTrue(outContent.toString().contains("Route found: Route 101"));
    System.setOut(System.out); // Konsol çıktısını eski haline getir
  }

  @Test
  public void testSearchNonExistentRoute() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    Scanner testScanner = new Scanner("999\n");
    busAndTrainSchedules.searchForRoutes(testScanner);
    assertTrue(outContent.toString().contains("Route not found."));
    System.setOut(System.out); // Konsol çıktısını eski haline getir
  }

  @Test
  public void testViewDepartureTimes() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    busAndTrainSchedules.viewDepartureTimes();
    assertTrue(outContent.toString().contains("All Departure Times:"));
    System.setOut(System.out); // Konsol çıktısını eski haline getir
  }

  @Test
  public void testCheckConnectivity() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    busAndTrainSchedules.checkConnectivity();
    assertTrue(outContent.toString().contains("Checking connectivity using DFS:"));
    System.setOut(System.out); // Konsol çıktısını eski haline getir
  }

  @Test
  public void testSaveRoutes() {
    busAndTrainSchedules.addRoute(101, "Route 101");
    busAndTrainSchedules.saveRoutes();
    File routesFile = new File(BusAndTrainSchedules.ROUTES_FILE);
    assertTrue(routesFile.exists());
  }

  @Test
  public void testLoadRoutes() {
    busAndTrainSchedules.addRoute(101, "Route 101");
    busAndTrainSchedules.saveRoutes();
    HashTable<Integer, String> loadedRoutes = busAndTrainSchedules.loadRoutes();
    assertEquals("Route 101", loadedRoutes.get(101));
  }

  @Test
  public void testSaveSchedules() {
    busAndTrainSchedules.saveSchedules();
    File schedulesFile = new File(BusAndTrainSchedules.SCHEDULES_FILE);
    assertTrue(schedulesFile.exists());
  }

  @Test
  public void testLoadSchedules() {
    busAndTrainSchedules.saveSchedules();
    BPlusTree loadedSchedules = busAndTrainSchedules.loadSchedules();
    assertNotNull(loadedSchedules);
  }

  @Test
  public void testDisplayMenuWithAddRoute() {
    Scanner testScanner = new Scanner("4\n101\nRoute 101\n5\n");
    busAndTrainSchedules.display(testScanner);
    assertEquals("Route 101", busAndTrainSchedules.getRouteMap().get(101));
  }

  @Test
  public void testDisplayMenuWithInvalidChoice() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    Scanner testScanner = new Scanner("0\n5\n");
    busAndTrainSchedules.display(testScanner);
    assertTrue(outContent.toString().contains("Invalid choice. Try again."));
    System.setOut(System.out); // Konsol çıktısını eski haline getir
  }
}
