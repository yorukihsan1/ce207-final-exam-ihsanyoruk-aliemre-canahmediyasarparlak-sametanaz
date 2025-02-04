package com.ucoruh.publictransportationscheduler;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ConsoleUtilsTest {

    /**
     * @brief Test method for clearing console on Windows OS.
     * @param None
     */
    @Test
    public void testClearConsoleOnWindows() {
        System.setProperty("os.name", "Windows");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            ConsoleUtils.clearConsole();
        } catch (Exception e) {
            fail("clearConsole should not throw an exception");
        }

        System.setOut(System.out);
    }

    /**
     * @brief Test method for clearing console on non-Windows OS.
     * @param None
     */
    @Test
    public void testClearConsoleOnNonWindows() {
        System.setProperty("os.name", "Linux");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ConsoleUtils.clearConsole();
        String output = outContent.toString();
        assertTrue(output.contains("\033[H\033[2J"));

        System.setOut(System.out);
    }

    /**
     * @brief Test method for exception handling in clearConsole.
     * @param None
     */
    @Test
    public void testClearConsoleExceptionHandling() {
        System.setProperty("os.name", "Windows");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            ConsoleUtils.clearConsole();
            String output = outContent.toString();
            assertTrue(output.isEmpty() || output.contains("Failed to clear console"));
        } catch (Exception e) {
            fail("Exception handling in clearConsole failed.");
        }

        System.setOut(System.out);
    }
}
