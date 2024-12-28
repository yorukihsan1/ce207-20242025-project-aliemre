package com.ucoruh.publictransportationscheduler.datastructures;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestOutputStream {
  private final ByteArrayOutputStream outputStream;
  private final PrintStream printStream;

  public TestOutputStream() {
    outputStream = new ByteArrayOutputStream();
    printStream = new PrintStream(outputStream);
  }

  public PrintStream getPrintStream() {
    return printStream;
  }

  public String getOutput() {
    return outputStream.toString();
  }

  public void reset() {
    outputStream.reset();
  }
}
