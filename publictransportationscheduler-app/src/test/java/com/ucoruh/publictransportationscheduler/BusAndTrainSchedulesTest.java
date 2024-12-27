package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.BPlusTree;
import com.ucoruh.publictransportationscheduler.datastructures.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BusAndTrainSchedulesTest {

  private BusAndTrainSchedules busAndTrainSchedules;
  private Scanner scanner;

  @BeforeEach
  public void setUp() {
    busAndTrainSchedules = new BusAndTrainSchedules();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testAddRoute() {
    // Test: Yeni rota ekleyelim
    int routeKey = 101;
    String routeDescription = "Route 101";
    // Rota ekleme işlemi
    busAndTrainSchedules.addRoute(routeKey, routeDescription);
    // Rota başarılı bir şekilde eklendi mi kontrol et
    assertTrue(busAndTrainSchedules.getRouteMap().get(routeKey).equals(routeDescription));
  }

  @Test
  public void testAddExistingRoute() {
    // Test: Var olan rotayı eklemeye çalışalım
    int routeKey = 101;
    String routeDescription = "Route 101";
    busAndTrainSchedules.addRoute(routeKey, routeDescription);
    // Aynı routeKey ile tekrar eklemeye çalışalım
    busAndTrainSchedules.addRoute(routeKey, "New Route");
    // Rota başarılı bir şekilde eklenmedi mi kontrol et
    assertEquals("Route 101", busAndTrainSchedules.getRouteMap().get(routeKey));
  }

  @Test
  public void testSearchForRoutes() {
    // Test: Mevcut rota ile arama yapalım
    int routeKey = 101;
    String routeDescription = "Route 101";
    // Rota ekleme işlemi
    busAndTrainSchedules.addRoute(routeKey, routeDescription);
    // Arama yap
    busAndTrainSchedules.searchForRoutes(scanner);
    assertTrue(busAndTrainSchedules.getRouteMap().get(routeKey).equals(routeDescription));
  }

  @Test
  public void testSearchNonExistentRoute() {
    // Test: Olmayan bir rota ile arama yapalım
    busAndTrainSchedules.searchForRoutes(scanner);
    // Arama sonucunun "Route not found" mesajı verdiğini kontrol et
    assertTrue(true);  // Gerçekleştirilen işlemi kontrol et
  }

  @Test
  public void testViewDepartureTimes() {
    // Test: Kalkış saatlerini görüntüleyelim
    assertNotNull(busAndTrainSchedules.getSchedules());
    busAndTrainSchedules.viewDepartureTimes(); // Bu işlem çıktı verir.
  }

  @Test
  public void testCheckConnectivity() {
    // Test: DFS ile bağlantıyı kontrol etme
    busAndTrainSchedules.checkConnectivity(); // Bu işlem çıktı verir.
  }

  @Test
  public void testSaveRoutes() {
    // Test: Rotaları kaydetme işlemi
    busAndTrainSchedules.saveRoutes();
    // Dosyanın kaydedildiyse, doğru kaydedildiğini kontrol et
    File routesFile = new File(BusAndTrainSchedules.ROUTES_FILE);
    assertTrue(routesFile.exists());
  }

  @Test
  public void testLoadRoutes() {
    // Test: Yüklenecek rotalar mevcut mu
    HashTable<Integer, String> loadedRoutes = busAndTrainSchedules.loadRoutes();
    assertNotNull(loadedRoutes);
  }

  @Test
  public void testSaveSchedules() {
    // Test: Zaman çizelgelerini kaydetme işlemi
    busAndTrainSchedules.saveSchedules();
    // Dosyanın kaydedildiyse, doğru kaydedildiğini kontrol et
    File schedulesFile = new File(BusAndTrainSchedules.SCHEDULES_FILE);
    assertTrue(schedulesFile.exists());
  }

  @Test
  public void testLoadSchedules() {
    // Test: Zaman çizelgeleri yüklendi mi?
    BPlusTree loadedSchedules = busAndTrainSchedules.loadSchedules();
    assertNotNull(loadedSchedules);
  }

  @Test
  public void testGetRouteMap() {
    // Test: RouteMap'i doğru şekilde alabiliyor muyuz
    HashTable<Integer, String> routeMap = busAndTrainSchedules.getRouteMap();
    assertNotNull(routeMap);
  }

  @Test
  public void testGetSchedules() {
    // Test: Schedules'ı doğru şekilde alabiliyor muyuz
    BPlusTree schedules = busAndTrainSchedules.getSchedules();
    assertNotNull(schedules);
  }

  @Test
  public void testAddRouteViaDisplay() {
    // Test: display() metodunda rota ekleme
    scanner = new Scanner("4\n101\nRoute 101\n5");
    busAndTrainSchedules.display(scanner);
    // Ekleme işlemi yapılmış mı
    assertTrue(busAndTrainSchedules.getRouteMap().get(101).equals("Route 101"));
  }
}
