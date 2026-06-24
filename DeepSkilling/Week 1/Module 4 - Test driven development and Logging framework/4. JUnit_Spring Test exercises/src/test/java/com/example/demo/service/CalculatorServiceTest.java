package com.example.demo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 1: Basic Unit Test for a Service Method.
 *
 * CalculatorService has no dependencies to mock, so this is the simplest
 * possible test: instantiate it directly with "new" and assert on its
 * output. No Spring context, no Mockito needed at all.
 */
public class CalculatorServiceTest {

    @Test
    void add_shouldReturnSumOfTwoNumbers() {
        CalculatorService calculatorService = new CalculatorService();

        int result = calculatorService.add(2, 3);

        assertEquals(5, result);
    }

    @Test
    void add_shouldHandleNegativeNumbers() {
        CalculatorService calculatorService = new CalculatorService();

        int result = calculatorService.add(-4, 10);

        assertEquals(6, result);
    }
}
