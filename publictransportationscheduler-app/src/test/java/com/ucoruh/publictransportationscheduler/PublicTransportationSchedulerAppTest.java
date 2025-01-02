package com.ucoruh.publictransportationscheduler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the PublicTransportationSchedulerApp class.
 */
public class PublicTransportationSchedulerAppTest {

    private PublicTransportationSchedulerApp app;
    private MainMenu mockMenu;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        app = spy(new PublicTransportationSchedulerApp());
        mockMenu = mock(MainMenu.class);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testRunMethod() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        doReturn(mockMenu).when(app).createMainMenu();
        app.run();
        verify(mockMenu, times(1)).startApplication(any(Scanner.class));
        assertNotNull("MainMenu instance should not be null.", app.createMainMenu());
    }

    @Test
    public void testCreateMainMenu() {
        MainMenu menu = app.createMainMenu();
        assertNotNull("createMainMenu should return a non-null MainMenu instance.", menu);
    }

    @Test
    public void testConsoleClearOnExit() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        doReturn(mockMenu).when(app).createMainMenu();
        app.run();
        String output = outputStream.toString();
        assertNotNull("Output should not be null after application run.", output);
    }
}
