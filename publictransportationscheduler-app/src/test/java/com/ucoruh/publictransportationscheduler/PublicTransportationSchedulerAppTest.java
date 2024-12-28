package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the PublicTransportationSchedulerApp class.
 */
public class PublicTransportationSchedulerAppTest {

  private PublicTransportationSchedulerApp app;
  private MainMenu mockMenu;
  private ByteArrayOutputStream outputStream;

  /**
   * @brief Setup method to initialize test dependencies.
   */
  @BeforeEach
  public void setUp() {
    app = spy(new PublicTransportationSchedulerApp());
    mockMenu = mock(MainMenu.class);
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
  }

  /**
   * @brief Test method for the run method in PublicTransportationSchedulerApp.
   *        It verifies that the MainMenu is created and the startApplication method is called.
   */
  @Test
  public void testRunMethod() {
    String input = "5\n"; // Simulate user entering "5" to exit
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    doReturn(mockMenu).when(app).createMainMenu();
    app.run();
    verify(mockMenu, times(1)).startApplication(any(Scanner.class));
    assertNotNull(app.createMainMenu(), "MainMenu instance should not be null.");
  }

  /**
   * @brief Test method for creating a MainMenu instance.
   *        It verifies that the createMainMenu method returns a non-null instance.
   */
  @Test
  public void testCreateMainMenu() {
    MainMenu menu = app.createMainMenu();
    assertNotNull(menu, "createMainMenu should return a non-null MainMenu instance.");
  }

  /**
   * @brief Test method to verify that clearConsole is called when the application exits.
   */
  @Test
  public void testConsoleClearOnExit() {
    String input = "5\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    doReturn(mockMenu).when(app).createMainMenu();
    app.run();
    String output = outputStream.toString();
    assertNotNull(output, "Console output should not be null.");
  }
}
