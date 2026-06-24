package com.example.advanced;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Exercise 5: Timeout and Performance Testing
 *
 * Three common ways to enforce a time limit on a test in JUnit 5:
 *  1. @Timeout annotation on the test method
 *  2. assertTimeout(...) - lets the task finish, then checks duration
 *  3. assertTimeoutPreemptively(...) - aborts the task if it runs too long
 */
public class PerformanceTesterTest {

    private final PerformanceTester performanceTester = new PerformanceTester();

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void performTask_completesWithinAnnotatedTimeout() {
        performanceTester.performTask(100);
    }

    @Test
    void performTask_completesWithinAssertTimeout() {
        assertTimeout(Duration.ofMillis(500), () -> performanceTester.performTask(100));
    }

    @Test
    void performTask_completesWithinPreemptiveTimeout() {
        // Preemptively aborts the supplied task if it exceeds the duration,
        // instead of waiting for it to finish naturally.
        assertTimeoutPreemptively(Duration.ofMillis(500), () -> performanceTester.performTask(100));
    }
}
