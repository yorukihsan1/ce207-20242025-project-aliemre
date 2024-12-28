package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {

  private static final String TEST_FILE = "test_file.bin";
  private List<String> testData;

  @BeforeEach
  public void setUp() {
    testData = new ArrayList<>();
    testData.add("Data1");
    testData.add("Data2");
    testData.add("Data3");
  }

  @AfterEach
  public void tearDown() {
    File file = new File(TEST_FILE);

    if (file.exists()) {
      assertTrue(file.delete(), "Failed to clean up test file.");
    }
  }

  @Test
  public void testSaveToFileSuccess() {
    // Test: Save data to file
    FileUtils.saveToFile(TEST_FILE, testData);
    // Assert file exists
    File file = new File(TEST_FILE);
    assertTrue(file.exists(), "File should exist after saving.");
  }

  @Test
  public void testLoadFromFileSuccess() {
    // Save test data
    FileUtils.saveToFile(TEST_FILE, testData);
    // Load data
    List<String> loadedData = FileUtils.loadFromFile(TEST_FILE);
    // Assert data is loaded correctly
    assertNotNull(loadedData, "Loaded data should not be null.");
    assertEquals(testData.size(), loadedData.size(), "Loaded data size should match.");
    assertEquals(testData, loadedData, "Loaded data should match the saved data.");
  }

  @Test
  public void testLoadFromFileNonExistentFile() {
    // Load data from a non-existent file
    List<String> loadedData = FileUtils.loadFromFile("non_existent_file.bin");
    // Assert empty list is returned
    assertNotNull(loadedData, "Loaded data should not be null.");
    assertTrue(loadedData.isEmpty(), "Loaded data should be empty for a non-existent file.");
  }

  @Test
  public void testSaveToFileIOException() {
    // Simulate an IOException by attempting to save to a restricted directory (Linux/Unix-specific)
    String restrictedFilePath = "/root/restricted_file.bin";
    // Attempt to save and verify it handles the exception
    FileUtils.saveToFile(restrictedFilePath, testData);
    // Verify file does not exist
    File file = new File(restrictedFilePath);
    assertFalse(file.exists(), "File should not be created in a restricted directory.");
  }

  @Test
  public void testLoadFromFileIOException() {
    // Simulate an IOException by attempting to load from a restricted directory (Linux/Unix-specific)
    String restrictedFilePath = "/root/restricted_file.bin";
    // Attempt to load and verify it handles the exception
    List<String> loadedData = FileUtils.loadFromFile(restrictedFilePath);
    // Assert empty list is returned
    assertNotNull(loadedData, "Loaded data should not be null.");
    assertTrue(loadedData.isEmpty(), "Loaded data should be empty for a restricted file.");
  }
}
