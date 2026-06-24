package com.example.advanced;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 4: Exception Testing
 *
 * Demonstrates how to assert that a method throws the expected exception,
 * and how to inspect the exception's message once caught.
 */
public class ExceptionThrowerTest {

    private final ExceptionThrower exceptionThrower = new ExceptionThrower();

    @Test
    void throwException_throwsIllegalArgumentException_forNegativeInput() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> exceptionThrower.throwException(-5)
        );

        assertTrue(exception.getMessage().contains("must not be negative"));
    }

    @Test
    void throwException_returnsDoubledValue_forNonNegativeInput() {
        int result = exceptionThrower.throwException(4);
        assertEquals(8, result);
    }
}
