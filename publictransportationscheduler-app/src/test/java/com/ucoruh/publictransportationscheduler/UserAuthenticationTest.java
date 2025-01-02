package com.ucoruh.publictransportationscheduler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

public class UserAuthenticationTest {
  private UserAuthentication userAuth;
  private String testUserFilePath = "users.bin";

  /**
   * @brief Setup method to initialize the UserAuthentication instance and ensure a clean test environment.
   */
  @Before
  public void setUp() {
    userAuth = new UserAuthentication();
  }

  /**
   * @brief Tear down method to clean up after each test.
   */
  @After
  public void tearDown() {
    deleteFile(testUserFilePath);
  }

  public static boolean deleteFile(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
      return file.delete(); // Attempt to delete and return result
    } else {
      return false; // File does not exist
    }
  }

  /**
   * @brief Test method for user registration.
   *        Verifies that the users file is created after registration.
   */
  @Test
  public void testRegisterUser() {
    String input = "2\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    userAuth.display(new Scanner(System.in));
    File file = new File("users.bin");
    assertTrue("The users file should exist after registration.", file.exists());
  }

  /**
   * @brief Test method for successful login.
   *        Verifies that a user can log in successfully after registration.
   */
  @Test
  public void testLoginSuccessful() {
    String registerInput = "2\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(registerInput.getBytes()));
    userAuth.display(new Scanner(System.in));
    String loginInput = "1\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(loginInput.getBytes()));
    userAuth.display(new Scanner(System.in));
    assertTrue("User should be authenticated after a successful login.", userAuth.isAuthenticated());
  }

  /**
   * @brief Test method for failed login.
   *        Verifies that authentication fails for incorrect credentials.
   */
  @Test
  public void testLoginFailed() {
    String input = "1\nwronguser\nwrongpassword\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    userAuth.display(new Scanner(System.in));
    assertFalse("Authentication should fail for incorrect credentials.", userAuth.isAuthenticated());
  }

  /**
   * @brief Test method for saving users to a file.
   *        Verifies that the users file is created after registration.
   */
  @Test
  public void testSaveUsers() {
    userAuth.register(new Scanner("username78\npassword\n"));
    File file = new File(System.getProperty("user.dir") + "/users.bin");
    assertTrue("Users file should be saved after registration.", file.exists());
  }

  /**
   * @brief Test method for loading users from a file.
   *        Verifies that the loaded user is authenticated with the correct credentials.
   */
  @Test
  public void testLoadUsers() {
    String input = "2\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    userAuth.display(new Scanner(System.in));
    UserAuthentication newUserAuth = new UserAuthentication();
    String loginInput = "1\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(loginInput.getBytes()));
    newUserAuth.display(new Scanner(System.in));
    assertTrue("The loaded user should be authenticated with correct credentials.", newUserAuth.isAuthenticated());
  }
}
