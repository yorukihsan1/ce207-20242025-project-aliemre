package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BusAndTrainSchedulesTest {

  @Test
  public void testAddSchedule() {
    BusAndTrainSchedules schedules = new BusAndTrainSchedules();
    schedules.addSchedule("Route1", "10:00 AM");
    assertEquals("10:00 AM", schedules.getSchedule("Route1"));
  }

  @Test
  public void testSearchRoute() {
    BusAndTrainSchedules schedules = new BusAndTrainSchedules();
    schedules.addSchedule("Route1", "10:00 AM");
    assertTrue(schedules.hasRoute("Route1"));
  }
}
