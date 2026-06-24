package com.example.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 3: Assertions in JUnit
 *
 * Demonstrates the most commonly used JUnit assertions.
 */
public class AssertionsTest {

    @Test
    public void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        assertNull(null);

        // Assert not null
        assertNotNull(new Object());
    }

    @Test
    public void testAssertArrayEquals() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAssertSame() {
        String a = "hello";
        String b = a;
        assertSame(a, b);
    }

    @Test
    public void testAssertNotSame() {
        String a = new String("hello");
        String b = new String("hello");
        assertNotSame(a, b);
    }

    @Test
    public void testAssertEqualsWithDelta() {
        // Useful for comparing floating point numbers
        assertEquals(0.3, 0.1 + 0.2, 0.0001);
    }
}
