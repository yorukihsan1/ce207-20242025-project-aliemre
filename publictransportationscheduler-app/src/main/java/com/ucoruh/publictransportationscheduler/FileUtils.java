package com.ucoruh.publictransportationscheduler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

  // Veriyi dosyaya kaydetme
  public static <T> void saveToFile(String fileName, List<T> data) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
      oos.writeObject(data);
      System.out.println("Data successfully saved to " + fileName);
    } catch (IOException e) {
      System.err.println("Error saving data to file: " + e.getMessage());
    }
  }

  // Dosyadan veriyi yükleme
  @SuppressWarnings("unchecked")
  public static <T> List<T> loadFromFile(String fileName) {
    File file = new File(fileName);

    if (!file.exists()) {
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
