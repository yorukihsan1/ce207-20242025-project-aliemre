package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class PublicTransportationSchedulerAppTest {

  private PublicTransportationSchedulerApp app;
  private MainMenu mockMenu;

  @BeforeEach
  public void setUp() {
    app = spy(new PublicTransportationSchedulerApp());
    mockMenu = mock(MainMenu.class);
  }

  @Test
  public void testRunWithValidInput() {
    // Simulate user entering valid inputs for MainMenu
    String input = "1\n"; // Simulate user input
    InputStream in = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(in);
    doNothing().when(mockMenu).startApplication(scanner);
    // Replace the MainMenu creation with the mock
    doReturn(mockMenu).when(app).createMainMenu();
    app.run();
    verify(mockMenu, times(1)).startApplication(scanner);
  }

  @Test
  public void testRunWithEmptyInput() {
    // Simulate user entering nothing
    String input = "\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(in);
    doNothing().when(mockMenu).startApplication(scanner);
    // Replace the MainMenu creation with the mock
    doReturn(mockMenu).when(app).createMainMenu();
    app.run();
    verify(mockMenu, times(1)).startApplication(scanner);
  }
}
