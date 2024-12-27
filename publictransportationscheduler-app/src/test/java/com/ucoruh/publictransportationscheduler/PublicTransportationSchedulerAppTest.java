package com.ucoruh.publictransportationscheduler;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PublicTransportationSchedulerAppTest {

  @Test
  void testLogin() {
    UserAuthentication auth = new UserAuthentication();
    auth.registerUser("testUser", "password");
    assertTrue(auth.validateLogin("testUser", "password"));
  }

  @Test
  void testAddRoute() {
    RoutePlanning planning = new RoutePlanning();
    planning.addRoute("A -> B");
    assertTrue(planning.getRoutes().contains("A -> B"));
  }

  @Test
  void testEstimateFare() {
    FareCalculation fareCalc = new FareCalculation();
    fareCalc.setFarePerKm(5.0);
    assertEquals(50.0, fareCalc.calculateFare(10));
  }
}
