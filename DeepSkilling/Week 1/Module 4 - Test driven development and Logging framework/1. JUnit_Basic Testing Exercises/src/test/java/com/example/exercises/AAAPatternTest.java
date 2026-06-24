package com.example.exercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures,
 * Setup and Teardown Methods in JUnit.
 *
 * - @Before runs before EACH test method (setup / fixture creation).
 * - @After runs after EACH test method (teardown / cleanup).
 * - Each test method itself follows the Arrange-Act-Assert pattern.
 */
public class AAAPatternTest {

    private Calculator calculator;
    private List<String> shoppingList;

    @Before
    public void setUp() {
        // Runs before every test - create fresh fixtures
        calculator = new Calculator();
        shoppingList = new ArrayList<>();
        System.out.println("Setting up test fixtures...");
    }

    @After
    public void tearDown() {
        // Runs after every test - clean up resources
        calculator = null;
        shoppingList = null;
        System.out.println("Tearing down test fixtures...");
    }

    @Test
    public void testAddition_usingAAAPattern() {
        // Arrange
        int a = 4;
        int b = 6;
        int expected = 10;

        // Act
        int actual = calculator.add(a, b);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction_usingAAAPattern() {
        // Arrange
        int a = 10;
        int b = 3;
        int expected = 7;

        // Act
        int actual = calculator.subtract(a, b);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testShoppingListAddItem_usingAAAPattern() {
        // Arrange
        String item = "Milk";

        // Act
        shoppingList.add(item);

        // Assert
        assertEquals(1, shoppingList.size());
        assertTrue(shoppingList.contains(item));
    }

    @Test
    public void testShoppingListIsEmptyAtStart() {
        // Arrange - done in setUp()

        // Act - no action needed, just checking initial state

        // Assert
        assertTrue(shoppingList.isEmpty());
    }
}
