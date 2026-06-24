package com.example.advanced;

/**
 * Exercise 5: Timeout and Performance Testing
 *
 * A class with a method that takes some time to complete,
 * used to demonstrate timeout-based testing.
 */
public class PerformanceTester {

    /**
     * Simulates a task that takes roughly the given number of milliseconds.
     */
    public void performTask(long simulatedDurationMillis) {
        try {
            Thread.sleep(simulatedDurationMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task was interrupted", e);
        }
    }
}
