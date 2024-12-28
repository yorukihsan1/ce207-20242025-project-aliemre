package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class UserAuthenticationTest {
  private UserAuthentication userAuth;

  /**
   * @brief Setup method to initialize the UserAuthentication instance and ensure a clean test environment.
   */
  @BeforeEach
  public void setUp() {
    userAuth = new UserAuthentication();
    deleteUsersFile();
  }

  /**
   * @brief Helper method to delete the users file if it exists.
   */
  private void deleteUsersFile() {
    File file = new File("users.bin");

    if (file.exists()) {
      file.delete();
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
    assertTrue(file.exists(), "The users file should exist after registration.");
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
    assertTrue(userAuth.isAuthenticated(), "User should be authenticated after a successful login.");
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
    assertFalse(userAuth.isAuthenticated(), "Authentication should fail for incorrect credentials.");
  }

  /**
   * @brief Test method for saving users to a file.
   *        Verifies that the users file is created after registration.
   */
  @Test
  public void testSaveUsers() {
    userAuth.register(new Scanner("username\npassword\n"));
    File file = new File(System.getProperty("user.dir") + "/users.bin");
    assertTrue(file.exists(), "Users file should be saved after registration.");
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
    assertTrue(newUserAuth.isAuthenticated(), "The loaded user should be authenticated with correct credentials.");
  }
}
