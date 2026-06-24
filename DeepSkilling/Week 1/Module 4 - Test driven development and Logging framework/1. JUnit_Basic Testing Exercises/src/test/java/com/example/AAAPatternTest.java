package com.example;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures,
 * Setup and Teardown Methods in JUnit.
 *
 * Demonstrates:
 *  - The AAA pattern (Arrange, Act, Assert) inside each test
 *  - @Before / @After running before/after EVERY test method (per-test fixture)
 *  - @BeforeClass / @AfterClass running once for the whole class (shared fixture)
 */
public class AAAPatternTest {

    private Calculator calculator;
    private List<Integer> numbers;

    @BeforeClass
    public static void setUpClass() {
        // Runs once before any test in this class.
        System.out.println("AAAPatternTest: starting test suite...");
    }

    @AfterClass
    public static void tearDownClass() {
        // Runs once after all tests in this class have finished.
        System.out.println("AAAPatternTest: test suite finished.");
    }

    @Before
    public void setUp() {
        // Runs before EACH test method - fresh fixtures for every test.
        calculator = new Calculator();
        numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @After
    public void tearDown() {
        // Runs after EACH test method - clean up resources.
        calculator = null;
        numbers = null;
    }

    @Test
    public void testAddUsingAAAPattern() {
        // Arrange
        int a = 10;
        int b = 20;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(30, result);
    }

    @Test
    public void testListSizeUsingAAAPattern() {
        // Arrange
        // (numbers list already arranged in setUp())

        // Act
        int size = numbers.size();

        // Assert
        assertEquals(3, size);
    }

    @Test
    public void testDivideUsingAAAPattern() {
        // Arrange
        int dividend = 20;
        int divisor = 4;

        // Act
        int result = calculator.divide(dividend, divisor);

        // Assert
        assertEquals(5, result);
    }

    @Test
    public void testListContainsElementUsingAAAPattern() {
        // Arrange
        int valueToCheck = 2;

        // Act
        boolean contains = numbers.contains(valueToCheck);

        // Assert
        assertTrue(contains);
    }
}
