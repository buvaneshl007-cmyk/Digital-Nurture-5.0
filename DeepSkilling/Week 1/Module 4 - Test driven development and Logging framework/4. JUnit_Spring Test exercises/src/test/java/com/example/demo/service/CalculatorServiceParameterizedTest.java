package com.example.demo.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 9: Parameterized Test with JUnit.
 *
 * @ParameterizedTest with @CsvSource runs the same test method once per
 * row, with each row's values bound to the method's parameters. This
 * avoids writing near-duplicate @Test methods for each input combination.
 *
 * Reuses CalculatorService from Exercise 1 since it's the simplest
 * dependency-free method available to demonstrate the pattern clearly.
 */
public class CalculatorServiceParameterizedTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "0, 0, 0",
            "-5, 5, 0",
            "100, 200, 300",
            "-10, -20, -30"
    })
    void add_shouldReturnExpectedSum(int a, int b, int expectedSum) {
        int result = calculatorService.add(a, b);

        assertEquals(expectedSum, result);
    }
}
