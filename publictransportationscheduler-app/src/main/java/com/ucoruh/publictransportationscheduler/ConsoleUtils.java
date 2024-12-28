package com.ucoruh.publictransportationscheduler;

/**
 * @brief Utility class for console-related operations.
 */
public class ConsoleUtils {

  /**
   * @brief Clears the console screen based on the operating system.
   *
   * For Windows systems, it uses the "cls" command. For Unix-based systems,
   * it sends the ANSI escape codes to clear the console.
   */
  public static void clearConsole() {
    try {
      if (System.getProperty("os.name").contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }
    } catch (Exception e) {
      // Exception is ignored to ensure smooth execution.
    }
  }
}
