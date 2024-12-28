package com.ucoruh.publictransportationscheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class UserAuthenticationTest {
  private UserAuthentication userAuth;

  @BeforeEach
  public void setUp() {
    userAuth = new UserAuthentication();
    deleteUsersFile(); // Ensure a clean test environment
  }

  private void deleteUsersFile() {
    File file = new File("users.bin");

    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  public void testRegisterUser() {
    String input = "2\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    userAuth.display(new Scanner(System.in));
    File file = new File("users.bin");
    assertTrue(file.exists(), "The users file should exist after registration.");
  }

  @Test
  public void testLoginSuccessful() {
    // First, register the user
    String registerInput = "2\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(registerInput.getBytes()));
    userAuth.display(new Scanner(System.in));
    // Then, attempt to log in
    String loginInput = "1\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(loginInput.getBytes()));
    userAuth.display(new Scanner(System.in));
    assertTrue(userAuth.isAuthenticated(), "User should be authenticated after a successful login.");
  }

  @Test
  public void testLoginFailed() {
    String input = "1\nwronguser\nwrongpassword\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    userAuth.display(new Scanner(System.in));
    assertFalse(userAuth.isAuthenticated(), "Authentication should fail for incorrect credentials.");
  }

  @Test
  public void testSaveUsers() {
    userAuth.register(new Scanner("username\npassword\n")); // Kullanıcı kaydet
    File file = new File(System.getProperty("user.dir") + "/users.bin");
    assertTrue(file.exists(), "Users file should be saved after registration.");
  }



  @Test
  public void testLoadUsers() {
    // Register a user to save it to the file
    String input = "2\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    userAuth.display(new Scanner(System.in));
    // Create a new UserAuthentication instance to simulate loading from file
    UserAuthentication newUserAuth = new UserAuthentication();
    String loginInput = "1\nusername\npassword\n";
    System.setIn(new ByteArrayInputStream(loginInput.getBytes()));
    newUserAuth.display(new Scanner(System.in));
    assertTrue(newUserAuth.isAuthenticated(), "The loaded user should be authenticated with correct credentials.");
  }
}
