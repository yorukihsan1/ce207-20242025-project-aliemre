package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class MainMenuTest {

  private MainMenu mainMenu;
  private UserAuthentication mockAuth;
  private BusAndTrainSchedules mockSchedules;
  private RoutePlanning mockRoutePlanning;
  private FareCalculation mockFareCalculation;
  private Alerts mockAlerts;

  @BeforeEach
  public void setUp() {
    mainMenu = spy(new MainMenu());
    mockAuth = mock(UserAuthentication.class);
    mockSchedules = mock(BusAndTrainSchedules.class);
    mockRoutePlanning = mock(RoutePlanning.class);
    mockFareCalculation = mock(FareCalculation.class);
    mockAlerts = mock(Alerts.class);
    // Inject mocked dependencies
    doReturn(mockAuth).when(mainMenu).createUserAuthentication();
    doReturn(mockSchedules).when(mainMenu).createBusAndTrainSchedules();
    doReturn(mockRoutePlanning).when(mainMenu).createRoutePlanning();
    doReturn(mockFareCalculation).when(mainMenu).createFareCalculation();
    doReturn(mockAlerts).when(mainMenu).createAlerts();
  }


  @Test
  public void testStartApplication_MainMenuNavigation() {
    when(mockAuth.isAuthenticated()).thenReturn(true); // Authentication succeeds
    String input = "1\n2\n3\n4\n5\n"; // Simulate navigating through the menu and exiting
    InputStream in = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(in);
    mainMenu.startApplication(scanner);
    verify(mockSchedules).display(scanner);
    verify(mockRoutePlanning).display(scanner);
    verify(mockFareCalculation).display(scanner);
    verify(mockAlerts).display(scanner);
  }

  @Test
  public void testShowMainMenu_InvalidChoice() {
    when(mockAuth.isAuthenticated()).thenReturn(true); // Authentication succeeds
    String input = "0\n5\n"; // Invalid choice followed by exit
    InputStream in = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(in);
    mainMenu.startApplication(scanner);
    // No exception should occur, and the menu should handle invalid input
  }

  @Test
  public void testShowMainMenu_Exit() {
    when(mockAuth.isAuthenticated()).thenReturn(true); // Authentication succeeds
    String input = "5\n"; // Exit choice
    InputStream in = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(in);
    mainMenu.startApplication(scanner);
    // Verify that the menu exits gracefully
  }
}
