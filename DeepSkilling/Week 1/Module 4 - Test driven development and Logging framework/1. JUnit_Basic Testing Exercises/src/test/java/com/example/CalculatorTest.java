package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Exercise 2: Writing Basic JUnit Tests
 *
 * Basic tests for the Calculator class methods.
 */
public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    public void testSubtract() {
        int result = calculator.subtract(5, 3);
        assertEquals(2, result);
    }

    @Test
    public void testMultiply() {
        int result = calculator.multiply(4, 3);
        assertEquals(12, result);
    }

    @Test
    public void testDivide() {
        int result = calculator.divide(10, 2);
        assertEquals(5, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZeroThrowsException() {
        calculator.divide(10, 0);
    }

    @Test
    public void testIsEven() {
        assertTrue(calculator.isEven(4));
        assertFalse(calculator.isEven(7));
    }
}
