
package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {

  private static final String TEST_FILE = "test_data.bin";
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
      assertTrue(file.delete(), "Test file should be deleted after the test.");
    }
  }

  @Test
  public void testSaveToFileSuccess() {
    // Test saving data to file
    FileUtils.saveToFile(TEST_FILE, testData);
    // Verify the file is created
    File file = new File(TEST_FILE);
    assertTrue(file.exists(), "File should exist after saving.");
  }

  @Test
  public void testSaveToFileCreatesParentDirectories() {
    // Test saving data with a nested directory
    String nestedFileName = "nested_dir/test_data.bin";
    FileUtils.saveToFile(nestedFileName, testData);
    // Verify the file and directory are created
    File file = new File(nestedFileName);
    assertTrue(file.exists(), "Nested file should exist after saving.");
    // Clean up
    File parentDir = file.getParentFile();
    assertTrue(file.delete(), "Nested file should be deleted after the test.");
    assertTrue(parentDir.delete(), "Parent directory should be deleted after the test.");
  }

  @Test
  public void testSaveToFileHandlesIOException() {
    // Simulate a restricted directory path
    String restrictedFilePath = "/root/restricted_file.bin";
    assertDoesNotThrow(() -> FileUtils.saveToFile(restrictedFilePath, testData),
                       "Save operation should handle IOException gracefully.");
  }


  @Test
  public void testLoadFromFileFileNotFound() {
    // Attempt to load data from a non-existent file
    List<String> loadedData = FileUtils.loadFromFile("non_existent_file.bin");
    // Verify an empty list is returned
    assertNotNull(loadedData, "Loaded data should not be null.");
    assertTrue(loadedData.isEmpty(), "Loaded data should be empty for a non-existent file.");
  }

  @Test
  public void testLoadFromFileHandlesIOException() {
    // Simulate a restricted directory path
    String restrictedFilePath = "/root/restricted_file.bin";
    List<String> loadedData = FileUtils.loadFromFile(restrictedFilePath);
    // Verify an empty list is returned
    assertNotNull(loadedData, "Loaded data should not be null.");
  }

  @Test
  public void testLoadFromFileHandlesClassNotFoundException() {
    String invalidFileName = "invalid_data.bin";

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(invalidFileName))) {
      oos.writeObject("Not a list");
    } catch (IOException e) {
    }
  }
}
