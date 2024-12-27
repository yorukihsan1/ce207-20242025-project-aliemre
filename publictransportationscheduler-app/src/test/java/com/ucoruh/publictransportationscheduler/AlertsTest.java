package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AlertsTest {

  private Alerts alerts;
  private Scanner scanner;

  @BeforeEach
  public void setUp() {
    // Her testten önce çalışacak setup işlemi
    alerts = new Alerts();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testAddAlert() {
    // Test: Yeni bir uyarı ekleme
    String testMessage = "Train delay";
    alerts.addAlert(new Scanner(testMessage));
    // Uyarının alerts listesine eklenmiş olduğunu kontrol et
    assertTrue(alerts.getAlerts().contains(testMessage)); // contains kullanıldı
  }

  @Test
  public void testViewAlerts() {
    // Test: Uyarıları görüntüleme
    alerts.addAlert(new Scanner("Train delayed"));
    // Uyarıların listede bulunduğunu kontrol et
    assertNotNull(alerts.getAlerts());
    assertTrue(alerts.getAlerts().size() > 0);  // size() metodunu kullanıyoruz
  }

  @Test
  public void testUndoLastAlert() {
    // Test: Son eklenen uyarıyı geri alma
    String testMessage = "Service disruption on Line 5";
    alerts.addAlert(new Scanner(testMessage));
    // Undo işlemi yapalım
    alerts.undoLastAlert();
    // Son eklenen uyarı listeden kaldırılmış olmalı
    assertTrue(alerts.getAlerts().isEmpty());
  }

  @Test
  public void testSaveAlerts() {
    // Test: Uyarıları kaydetme
    String testMessage = "Service suspension";
    alerts.addAlert(new Scanner(testMessage));
    // Uyarıları kaydetme işlemi yapılmalı
    alerts.saveAlerts();
    // Dosyanın kaydedildiğini kontrol et
    File file = new File("src/test/resources/alerts.bin");
    assertTrue(file.exists());
  }

  @Test
  public void testLoadAlerts() {
    // Test: Uyarıları yükleme
    alerts.addAlert(new Scanner("Train cancellation"));
    // Uyarıları kaydedelim
    alerts.saveAlerts();
    // Uyarıları yükleyelim
    alerts.loadAlerts();
    // Yüklenen uyarıların listede bulunduğunu kontrol edelim
    assertFalse(alerts.getAlerts().isEmpty());
  }
}
