package com.example.advanced;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 1: Parameterized Tests
 *
 * Instead of writing a separate test method for every input, @ParameterizedTest
 * lets the same test logic run once per value supplied by @ValueSource
 * (or another source like @CsvSource).
 */
public class EvenCheckerTest {

    private final EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100, 0, -4})
    void isEven_returnsTrue_forEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99, -3})
    void isEven_returnsFalse_forOddNumbers(int number) {
        assertFalse(evenChecker.isEven(number));
    }

    // Bonus: @CsvSource lets you pair an input with its expected result
    @ParameterizedTest
    @CsvSource({
            "2, true",
            "3, false",
            "0, true",
            "-7, false"
    })
    void isEven_matchesExpectedResult(int number, boolean expected) {
        assertEqualsBoolean(expected, evenChecker.isEven(number));
    }

    private void assertEqualsBoolean(boolean expected, boolean actual) {
        if (expected) {
            assertTrue(actual);
        } else {
            assertFalse(actual);
        }
    }
}
