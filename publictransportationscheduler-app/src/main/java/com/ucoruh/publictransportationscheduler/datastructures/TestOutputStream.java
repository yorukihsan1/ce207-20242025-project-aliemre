package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @brief Utility class for capturing console output during testing.
 */
public class TestOutputStream {

  private final ByteArrayOutputStream outputStream; /**< Captures the output as a byte stream. */
  private final PrintStream printStream; /**< Prints to the byte stream. */

  /**
   * @brief Constructs a new `TestOutputStream` instance.
   *        Initializes the output and print streams.
   */
  public TestOutputStream() {
    outputStream = new ByteArrayOutputStream();
    printStream = new PrintStream(outputStream);
  }

  /**
   * @brief Gets the `PrintStream` associated with this instance.
   * @return The `PrintStream` to capture output.
   */
  public PrintStream getPrintStream() {
    return printStream;
  }

  /**
   * @brief Retrieves the captured output as a string.
   * @return A string containing all captured console output.
   */
  public String getOutput() {
    return outputStream.toString();
  }

  /**
   * @brief Resets the captured output, clearing the buffer.
   */
  public void reset() {
    outputStream.reset();
  }
}
