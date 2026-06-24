package com.example.exercises;

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
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    public void testMultiply() {
        int result = calculator.multiply(3, 4);
        assertEquals(12, result);
    }

    @Test
    public void testDivide() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result, 0.0001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroThrowsException() {
        calculator.divide(10, 0);
    }

    @Test
    public void testIsEven() {
        assertTrue(calculator.isEven(4));
        assertFalse(calculator.isEven(7));
    }
}
