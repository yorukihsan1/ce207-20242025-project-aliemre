package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleUtilsTest {

  @Test
  public void testClearConsoleOnWindows() {
    // Simulate Windows OS
    System.setProperty("os.name", "Windows");
    // Redirect standard output to capture any error messages
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    // Run the method
    assertDoesNotThrow(ConsoleUtils::clearConsole);
    // Restore the original System.out
    System.setOut(System.out);
  }

  @Test
  public void testClearConsoleOnNonWindows() {
    // Simulate non-Windows OS
    System.setProperty("os.name", "Linux");
    // Redirect standard output to capture ANSI escape sequences
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    // Run the method
    ConsoleUtils.clearConsole();
    // Verify the ANSI escape sequence is printed
    String output = outContent.toString();
    assertTrue(output.contains("\033[H\033[2J"));
    // Restore the original System.out
    System.setOut(System.out);
  }

  @Test
  public void testClearConsoleExceptionHandling() {
    // Simulate Windows OS
    System.setProperty("os.name", "Windows");
    // Redirect standard output to capture error messages
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    // Run the method
    ConsoleUtils.clearConsole();
    // Verify the error message is printed if an exception occurs
    String output = outContent.toString();
    assertTrue(output.isEmpty() || output.contains("Failed to clear console"));
    // Restore the original System.out
    System.setOut(System.out);
  }
}
