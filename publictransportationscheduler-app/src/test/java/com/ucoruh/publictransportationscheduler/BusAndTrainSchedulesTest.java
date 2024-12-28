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
    busAndTrainSchedules.schedules.insert(101);
    busAndTrainSchedules.schedules.insert(102);
    busAndTrainSchedules.saveSchedules();
    File schedulesFile = new File(BusAndTrainSchedules.SCHEDULES_FILE);
    assertTrue(schedulesFile.exists(), "Schedules file should exist after saving.");
  }


  @Test
  public void testLoadSchedules() {
    busAndTrainSchedules.schedules.insert(101);
    busAndTrainSchedules.schedules.insert(102);
    busAndTrainSchedules.saveSchedules();
    BPlusTree loadedSchedules = busAndTrainSchedules.loadSchedules();
    assertNotNull(loadedSchedules, "Loaded schedules should not be null.");
    assertTrue(loadedSchedules.contains(101), "Loaded schedules should contain 101.");
    assertTrue(loadedSchedules.contains(102), "Loaded schedules should contain 102.");
  }



  @Test
  public void testDisplayMenuWithAddRoute() {
    // Simulate menu input for adding a route and exiting
    Scanner testScanner = new Scanner("4\n101\nRoute 101\n5\n");
    busAndTrainSchedules.display(testScanner);
    assertEquals("Route 101", busAndTrainSchedules.getRouteMap().get(101), "Route description should match after adding.");
  }


  @Test
  public void testDisplayMenuWithInvalidChoice() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    Scanner testScanner = new Scanner("0\n5\n");
  }


  @Test
  public void testAddRouteWithNewKey() {
    int routeKey = 101;
    String routeDescription = "New Route";
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    // Add a new route
    busAndTrainSchedules.addRoute(routeKey, routeDescription);
    // Assert that the route was added successfully
    assertEquals(routeDescription, busAndTrainSchedules.getRouteMap().get(routeKey),
                 "The route should be added with the correct description.");
    assertTrue(outContent.toString().contains("Route added successfully."),
               "The success message should be displayed.");
    System.setOut(System.out); // Reset System.out
  }

  @Test
  public void testAddRouteWithExistingKey() {
    int routeKey = 101;
    String initialDescription = "Initial Route";
    String duplicateDescription = "Duplicate Route";
    // Add the initial route
    busAndTrainSchedules.addRoute(routeKey, initialDescription);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    // Try to add a route with the same key
    busAndTrainSchedules.addRoute(routeKey, duplicateDescription);
    // Assert that the route description did not change
    assertEquals(initialDescription, busAndTrainSchedules.getRouteMap().get(routeKey),
                 "The route description should not change for an existing key.");
  }

  @Test
  public void testGraphSearchNodeAdded() {
    int routeKey = 102;
    String routeDescription = "Graph Route";
    // Add a new route
    busAndTrainSchedules.addRoute(routeKey, routeDescription);
  }

  @Test
  public void testLoadSchedulesFileDoesNotExist() {
    // Ensure the schedules file does not exist
    File schedulesFile = new File(BusAndTrainSchedules.SCHEDULES_FILE);

    if (schedulesFile.exists()) {
      schedulesFile.delete();
    }

    BPlusTree schedules = busAndTrainSchedules.loadSchedules();
    // Verify a new BPlusTree is returned when the file does not exist
    assertNotNull(schedules, "Schedules should not be null.");
  }

  @Test
  public void testLoadSchedulesFileExists() {
    // Insert data into schedules and save
    busAndTrainSchedules.schedules.insert(101);
    busAndTrainSchedules.schedules.insert(102);
    busAndTrainSchedules.saveSchedules();
    // Reload schedules from file
    BPlusTree loadedSchedules = busAndTrainSchedules.loadSchedules();
    // Verify loaded schedules contain the inserted data
    assertNotNull(loadedSchedules, "Loaded schedules should not be null.");
    assertTrue(loadedSchedules.contains(101), "Schedules should contain key 101.");
    assertTrue(loadedSchedules.contains(102), "Schedules should contain key 102.");
  }

  @Test
  public void testLoadSchedulesWithIOException() {
    // Force an exception by using an invalid file path
    BusAndTrainSchedules invalidSchedules = new BusAndTrainSchedules() {
      @Override
      public BPlusTree loadSchedules() {
        return super.loadSchedules();
      }
    };

    assertDoesNotThrow(invalidSchedules::loadSchedules, "Loading schedules with restricted path should not throw an exception.");
  }

  @Test
  public void testGetRouteMap() {
    // Add routes to the map
    busAndTrainSchedules.addRoute(101, "Route 101");
    busAndTrainSchedules.addRoute(102, "Route 102");
    // Retrieve route map
    HashTable<Integer, String> routeMap = busAndTrainSchedules.getRouteMap();
    // Verify routes exist in the map
    assertNotNull(routeMap, "Route map should not be null.");
    assertEquals("Route 101", routeMap.get(101), "Route map should contain key 101 with correct description.");
    assertEquals("Route 102", routeMap.get(102), "Route map should contain key 102 with correct description.");
  }

  @Test
  public void testGetSchedules() {
    // Insert data into schedules
    busAndTrainSchedules.schedules.insert(101);
    busAndTrainSchedules.schedules.insert(102);
    // Retrieve schedules
    BPlusTree schedules = busAndTrainSchedules.getSchedules();
    // Verify schedules contain the inserted data
    assertNotNull(schedules, "Schedules should not be null.");
    assertTrue(schedules.contains(101), "Schedules should contain key 101.");
    assertTrue(schedules.contains(102), "Schedules should contain key 102.");
  }
}
