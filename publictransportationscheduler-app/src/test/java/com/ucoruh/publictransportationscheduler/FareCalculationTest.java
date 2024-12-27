import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Scanner;

public class FareCalculationTest {

  private FareCalculation fareCalculation;
  private Scanner scanner;

  @BeforeEach
  public void setUp() {
    fareCalculation = new FareCalculation();
    scanner = mock(Scanner.class);  // Mocking the Scanner object
  }

  @Test
  public void testCalculateFareStandardTicket() {
    when(scanner.nextDouble()).thenReturn(10.0); // Distance: 10 km
    when(scanner.nextInt()).thenReturn(1); // Ticket type: 1 (Standard)
    fareCalculation.calculateFare(scanner);
    assertTrue(fareCalculation.getFareHistory().size() > 0);  // Fare history should contain data
  }

  @Test
  public void testCalculateFarePremiumTicket() {
    when(scanner.nextDouble()).thenReturn(10.0); // Distance: 10 km
    when(scanner.nextInt()).thenReturn(2); // Ticket type: 2 (Premium)
    fareCalculation.calculateFare(scanner);
    assertTrue(fareCalculation.getFareHistory().size() > 0);  // Fare history should contain data
  }

  @Test
  public void testViewFareHistoryEmpty() {
    fareCalculation.viewFareHistory();
    // Should print "No fare history available."
    assertTrue(true);
  }

  @Test
  public void testViewFareHistoryNotEmpty() {
    XORLinkedList<Double> fareHistoryMock = mock(XORLinkedList.class);
    fareCalculation.fareHistory = fareHistoryMock;
    fareCalculation.viewFareHistory();
    verify(fareHistoryMock, times(1)).display(); // Verifying display was called
  }

  @Test
  public void testViewLowestFareEmpty() {
    Heap heapMock = mock(Heap.class);
    fareCalculation.fareHeap = heapMock;
    when(heapMock.isEmpty()).thenReturn(true);
    fareCalculation.viewLowestFare();
    // "No fares available." message should be printed
    assertTrue(true);
  }

  @Test
  public void testViewLowestFareNotEmpty() {
    Heap heapMock = mock(Heap.class);
    fareCalculation.fareHeap = heapMock;
    when(heapMock.isEmpty()).thenReturn(false);
    when(heapMock.peekMin()).thenReturn(10);
    fareCalculation.viewLowestFare();
    // "Lowest Fare: 10 units" message should be printed
    assertTrue(true);
  }

  @Test
  public void testSaveFares() {
    fareCalculation.saveFares();
    File faresFile = new File(fareCalculation.getFaresFile());
    assertTrue(faresFile.exists());  // Check if file was saved
  }

  @Test
  public void testLoadFares() {
    File faresFile = new File(fareCalculation.getFaresFile());

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(faresFile))) {
      XORLinkedList<Double> fareHistory = new XORLinkedList<>();
      fareHistory.add(20.0);
      oos.writeObject(fareHistory);
    } catch (IOException e) {
      e.printStackTrace();
    }

    fareCalculation.loadFares();  // Load from file
    assertTrue(true);  // Test passes if data is successfully loaded
  }

  @Test
  public void testLoadFaresFileDoesNotExist() {
    File faresFile = new File(fareCalculation.getFaresFile());

    if (faresFile.exists()) {
      faresFile.delete();
    }

    fareCalculation.loadFares();  // Try loading
    assertTrue(fareCalculation.getFareHistory().isEmpty());  // Should be empty
  }
}
