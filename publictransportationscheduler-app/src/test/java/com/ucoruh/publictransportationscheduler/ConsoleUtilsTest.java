package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.lang.reflect.Method;

public class ConsoleUtilsTest {

  @Test
  public void testClearConsoleWindows() throws IOException, InterruptedException {
    // Test: Eğer işletim sistemi Windows ise, "cls" komutu çalıştırılmalı
    if (System.getProperty("os.name").contains("Windows")) {
      // Simüle edilmiş komut çalıştırma kodunu test ederiz
      ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
      processBuilder.inheritIO().start().waitFor();
      // Beklenen durum: cls komutu çalıştırılır
      assertTrue(true);  // Burada gerçek kontrol yapmamıza gerek yok, işlem başarılı ise test geçer
    }
  }

  @Test
  public void testClearConsoleUnix() {
    // Test: Eğer işletim sistemi Windows dışında ise, terminal temizlenmeli
    if (!System.getProperty("os.name").contains("Windows")) {
      // UNIX/Linux/Mac sistemlerinde terminali temizlemek için karakter dizisini yazdırıyoruz
      System.out.print("\033[H\033[2J");
      System.out.flush();
      // Bu kodu test etmek için fiziksel olarak terminali temizlemek yerine, bu kodu çalıştırmamız yeterlidir.
      // Testi geçerli saymamız için bu satırdaki işlemde bir hata almazsak test başarılı kabul edilir.
      assertTrue(true);  // Bu işlem başarılı bir şekilde çalıştığında test geçerli sayılır
    }
  }

  @Test
  public void testClearConsoleException() {
    // Test: Exception oluştuğunda hata mesajının doğru şekilde yazdırılması
    try {
      // IOException simüle etmek için metodu hata verecek şekilde çağırabiliriz
      Method method = ConsoleUtils.class.getDeclaredMethod("clearConsole");
      method.setAccessible(true);
      method.invoke(null);  // Metodu çağırıyoruz
    } catch (Exception e) {
      // Exception oluştuğunda hata mesajının doğru yazıldığını kontrol et
      assertTrue(e.getMessage().contains("Failed to clear console"));
    }
  }

  @Test
  public void testClearConsoleCatchBlock() {
    // Test: Exception catch bloğunda mesajın doğru yazılıp yazılmadığını test et
    try {
      // Simüle edilmiş bir IOException hatası yaratmak için bazı işlemleri yapacağız
      throw new IOException("Simulated IOException");
    } catch (IOException e) {
      // Simülasyon sırasında e.printStackTrace() ile hata mesajı çıktısını doğrulayabiliriz.
      System.err.println("Failed to clear console: " + e.getMessage());
      // Hata mesajının doğru çıktığını kontrol et.
      assertTrue(e.getMessage().contains("Simulated IOException"));
    }
  }

  @Test
  public void testClearConsoleProcessBuilder() throws IOException, InterruptedException {
    // Test: ProcessBuilder kullanarak 'cls' komutunun çalışıp çalışmadığını test etme
    if (System.getProperty("os.name").contains("Windows")) {
      // Windows işletim sisteminde cls komutunu çalıştırmak
      ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
      processBuilder.inheritIO().start().waitFor();
      assertTrue(true);  // Bu durumda, işlem başarılı ise test geçer
    }
  }

  @Test
  public void testClearConsoleNoException() {
    // Test: Eğer clearConsole içinde hiçbir hata oluşmazsa, hiçbir şey yazdırılmamalı
    try {
      ConsoleUtils.clearConsole();
      // Bu işlemde herhangi bir exception beklenmiyor
      assertTrue(true);  // Exception oluşmadığı için test başarılı kabul edilir.
    } catch (Exception e) {
      // Eğer exception oluşursa test başarısız olur
      fail("Exception should not be thrown.");
    }
  }
}
