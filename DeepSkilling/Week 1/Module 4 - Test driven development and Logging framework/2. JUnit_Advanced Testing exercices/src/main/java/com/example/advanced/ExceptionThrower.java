package com.example.advanced;

/**
 * Exercise 4: Exception Testing
 *
 * A class whose method throws an exception under certain conditions.
 */
public class ExceptionThrower {

    /**
     * Throws an IllegalArgumentException if the input is negative.
     * Otherwise returns the doubled value.
     */
    public int throwException(int input) {
        if (input < 0) {
            throw new IllegalArgumentException("Input must not be negative: " + input);
        }
        return input * 2;
    }
}
