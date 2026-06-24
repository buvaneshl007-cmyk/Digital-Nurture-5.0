package com.example.advanced;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 3: Test Execution Order
 *
 * By default, JUnit does not guarantee the order tests run in.
 * @TestMethodOrder + @Order lets you force a specific, predictable order -
 * useful when tests build on shared state (as shown here with `executionLog`).
 *
 * Note: relying on execution order is usually a sign tests aren't fully
 * isolated. Use this feature sparingly - it's shown here purely to
 * demonstrate the mechanism.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    private static final List<String> executionLog = new ArrayList<>();

    @Test
    @Order(1)
    void firstTest_runsFirst() {
        executionLog.add("first");
        assertEquals(1, executionLog.size());
    }

    @Test
    @Order(2)
    void secondTest_runsSecond() {
        executionLog.add("second");
        assertEquals(2, executionLog.size());
        assertEquals("first", executionLog.get(0));
    }

    @Test
    @Order(3)
    void thirdTest_runsThird() {
        executionLog.add("third");
        assertEquals(3, executionLog.size());
        assertEquals("second", executionLog.get(1));
    }
}
