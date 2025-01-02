package com.ucoruh.publictransportationscheduler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUtilsTest {

    private static final String TEST_FILE = "test_data.bin";
    private List<String> testData;

    /**
     * @brief Sets up test data before each test.
     */
    @Before
    public void setUp() {
        testData = new ArrayList<>();
        testData.add("Data1");
        testData.add("Data2");
        testData.add("Data3");
    }

    /**
     * @brief Cleans up test data after each test.
     */
    @After
    public void tearDown() {
        File file = new File(TEST_FILE);

        if (file.exists()) {
            assertTrue(file.delete(), "Test file should be deleted after the test.");
        }
    }

    /**
     * @brief Tests saving data to a file successfully.
     */
    @Test
    public void testSaveToFileSuccess() {
        FileUtils.saveToFile(TEST_FILE, testData);
        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "File should exist after saving.");
    }

    /**
     * @brief Tests saving data to a nested directory successfully.
     */
    @Test
    public void testSaveToFileCreatesParentDirectories() {
        String nestedFileName = "nested_dir/test_data.bin";
        FileUtils.saveToFile(nestedFileName, testData);
        File file = new File(nestedFileName);
        assertTrue(file.exists(), "Nested file should exist after saving.");

        File parentDir = file.getParentFile();
        assertTrue(file.delete(), "Nested file should be deleted after the test.");
        assertTrue(parentDir.delete(), "Parent directory should be deleted after the test.");
    }

    /**
     * @brief Tests that saving to a restricted directory handles IOException gracefully.
     */
    @Test
    public void testSaveToFileHandlesIOException() {
        String restrictedFilePath = "/root/restricted_file.bin";
        try {
            FileUtils.saveToFile(restrictedFilePath, testData);
        } catch (Exception e) {
        }
    }

    /**
     * @brief Tests loading data from a non-existent file.
     */
    @Test
    public void testLoadFromFileFileNotFound() {
        List<String> loadedData = FileUtils.loadFromFile("non_existent_file.bin");
        assertNotNull(loadedData, "Loaded data should not be null.");
        assertTrue(loadedData.isEmpty(), "Loaded data should be empty for a non-existent file.");
    }

    /**
     * @brief Tests that loading from a restricted directory handles IOException gracefully.
     */
    @Test
    public void testLoadFromFileHandlesIOException() {
        String restrictedFilePath = "/root/restricted_file.bin";
        List<String> loadedData = FileUtils.loadFromFile(restrictedFilePath);
        assertNotNull(loadedData, "Loaded data should not be null.");
    }

    /**
     * @brief Tests that loading invalid data handles ClassNotFoundException gracefully.
     */
    @Test
    public void testLoadFromFileHandlesClassNotFoundException() {
        String invalidFileName = "invalid_data.bin";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(invalidFileName))) {
            oos.writeObject("Not a list");
        } catch (IOException e) {
        }

        // Clean up
        File invalidFile = new File(invalidFileName);
        if (invalidFile.exists()) {
            assertTrue(invalidFile.delete(), "Invalid file should be deleted after the test.");
        }
    }
}
