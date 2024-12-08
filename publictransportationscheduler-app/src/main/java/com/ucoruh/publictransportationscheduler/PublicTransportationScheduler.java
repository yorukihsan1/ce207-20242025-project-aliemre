/**

@file PublicTransportatioScheduler.java
@brief This file serves as a demonstration file for the PublicTransportatioScheduler class.
@details This file contains the implementation of the PublicTransportatioScheduler class, which provides various mathematical operations.
*/

/**

@package com.ucoruh.publictransportationscheduler
@brief The com.ucoruh.publictransportationscheduler package contains all the classes and files related to the PublicTransportatioScheduler App.
*/
package com.ucoruh.publictransportationscheduler;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
/**

@class PublicTransportatioScheduler
@brief This class represents a PublicTransportatioScheduler that performs mathematical operations.
@details The PublicTransportatioScheduler class provides methods to perform mathematical operations such as addition, subtraction, multiplication, and division. It also supports logging functionality using the logger object.
@author ugur.coruh
*/
public class PublicTransportatioScheduler {

  /**
   * @brief Logger for the PublicTransportatioScheduler class.
   */
  private static final Logger logger = (Logger) LoggerFactory.getLogger(PublicTransportatioScheduler.class);

  /**
   * @brief Calculates the sum of two integers.
   *
   * @details This function takes two integer values, `a` and `b`, and returns their sum. It also logs a message using the logger object.
   *
   * @param a The first integer value.
   * @param b The second integer value.
   * @return The sum of `a` and `b`.
   */
  public int add(int a, int b) {
    // Logging an informational message
    logger.info("Logging message");
    // Logging an error message
    logger.error("Error message");
    // Returning the sum of `a` and `b`
    return a + b;
  }
}
