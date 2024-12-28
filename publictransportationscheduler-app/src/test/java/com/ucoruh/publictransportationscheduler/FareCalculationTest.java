package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.Heap;
import com.ucoruh.publictransportationscheduler.datastructures.XORLinkedList;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FareCalculationTest {

  private FareCalculation fareCalculation;

  @BeforeEach
  public void setUp() {
    fareCalculation = new FareCalculation();
  }

  @Test
  public void testDisplayCalculateFare() {
    Scanner scanner = new Scanner("1\n10\n1\n4\n"); // 10 km, Standard ticket, Exit
    fareCalculation.display(scanner);
    assertFalse(fareCalculation.getFareHistory().isEmpty());
    assertEquals(20.0, fareCalculation.getFareHistory().getLast()); // 10 km * 2.0
  }

  @Test
  public void testDisplayViewFareHistory() {
    fareCalculation.getFareHistory().add(15.0); // Add a fare for testing
    Scanner scanner = new Scanner("2\n4\n"); // View history, Exit
    fareCalculation.display(scanner);
    assertFalse(fareCalculation.getFareHistory().isEmpty());
  }

  @Test
  public void testDisplayViewLowestFare() {
    fareCalculation.getFareHeap().insert(30);
    fareCalculation.getFareHeap().insert(10);
    fareCalculation.getFareHeap().insert(20);
    Scanner scanner = new Scanner("3\n4\n"); // View lowest fare, Exit
    fareCalculation.display(scanner);
    assertEquals(10, fareCalculation.getFareHeap().peekMin());
  }

  @Test
  public void testCalculateFareStandard() {
    Scanner scanner = new Scanner("15\n1\n"); // 15 km, Standard ticket
    fareCalculation.calculateFare(scanner);
    assertEquals(15 * 2.0, fareCalculation.getFareHistory().getLast());
  }

  @Test
  public void testCalculateFarePremium() {
    Scanner scanner = new Scanner("5\n2\n"); // 5 km, Premium ticket
    fareCalculation.calculateFare(scanner);
    assertEquals(5 * 3.5, fareCalculation.getFareHistory().getLast());
  }

  @Test
  public void testViewFareHistoryEmpty() {
    assertTrue(fareCalculation.getFareHistory().isEmpty());
    fareCalculation.viewFareHistory();
  }

  @Test
  public void testViewFareHistoryNotEmpty() {
    fareCalculation.getFareHistory().add(50.0);
    fareCalculation.viewFareHistory();
    assertFalse(fareCalculation.getFareHistory().isEmpty());
    assertEquals(50.0, fareCalculation.getFareHistory().getLast());
  }

  @Test
  public void testViewLowestFareEmpty() {
    assertTrue(fareCalculation.getFareHeap().isEmpty());
    fareCalculation.viewLowestFare();
  }

  @Test
  public void testViewLowestFareNotEmpty() {
    fareCalculation.getFareHeap().insert(25);
    fareCalculation.getFareHeap().insert(15);
    fareCalculation.viewLowestFare();
    assertEquals(15, fareCalculation.getFareHeap().peekMin());
  }

  @Test
  public void testSaveAndLoadFares() throws IOException, ClassNotFoundException {
    fareCalculation.getFareHistory().add(40.0); // Add fare
    fareCalculation.saveFares();

    File file = new File(fareCalculation.getFaresFile());
    assertTrue(file.exists());

    FareCalculation loadedInstance = new FareCalculation();
    loadedInstance.loadFares();
    assertEquals(40.0, loadedInstance.getFareHistory().getLast());
  }

  @Test
  public void testSaveFaresFileNotFound() {
    // Test if the save handles exceptions properly
    try {
      fareCalculation.saveFares();
    } catch (Exception e) {
      fail("Exception during saving fares: " + e.getMessage());
    }
  }

  @Test
  public void testLoadFaresFileNotFound() {
    fareCalculation.loadFares(); // Should handle missing file gracefully
  }

  @Test
  public void testCompressionAndDecompression() {
    String fare = "75.0";
    String compressed = fareCalculation.huffman.compress(fare);
    String decompressed = fareCalculation.huffman.decompress(compressed);
    assertEquals(fare, decompressed);
  }

  @Test
  public void testGetterMethods() {
    assertNotNull(fareCalculation.getFareHeap());
    assertNotNull(fareCalculation.getFareHistory());
    assertEquals("fares.bin", fareCalculation.getFaresFile());
  }
}
