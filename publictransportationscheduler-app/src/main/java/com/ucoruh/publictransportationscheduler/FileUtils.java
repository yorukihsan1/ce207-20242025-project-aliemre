package com.ucoruh.publictransportationscheduler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Utility class for saving and loading data to/from files.
 */
public class FileUtils {

  /**
   * @brief Saves a list of data to a file.
   * @param fileName The name of the file where the data will be saved.
   * @param data The list of data to save.
   * @param <T> The type of data being saved.
   */
  public static <T> void saveToFile(String fileName, List<T> data) {
    try {
      // Check if the parent directory exists and create it if necessary
      File file = new File(fileName);
      File parentDir = file.getParentFile();

      if (parentDir != null && !parentDir.exists()) {
        parentDir.mkdirs(); // Create missing directories
      }

      // Write data to the file
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(data);
        System.out.println("Data successfully saved to " + fileName);
      }
    } catch (IOException e) {
      System.err.println("Error saving data to file: " + e.getMessage());
    }
  }

  /**
   * @brief Loads a list of data from a file.
   * @param fileName The name of the file from which to load the data.
   * @param <T> The type of data to load.
   * @return A list of data loaded from the file, or an empty list if the file does not exist or an error occurs.
   */
  @SuppressWarnings("unchecked")
  public static <T> List<T> loadFromFile(String fileName) {
    File file = new File(fileName);

    if (!file.exists()) {
      System.out.println("File not found: " + fileName + ". Returning empty list.");
      return new ArrayList<>();
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      return (List<T>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading data from file: " + e.getMessage());
      return new ArrayList<>();
    }
  }
}
