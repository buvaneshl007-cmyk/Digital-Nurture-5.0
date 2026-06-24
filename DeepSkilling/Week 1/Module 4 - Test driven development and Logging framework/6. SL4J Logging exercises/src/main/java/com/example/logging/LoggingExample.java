package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 1: Logging Error Messages and Warning Levels.
 *
 * Demonstrates the two most commonly used "problem" log levels in SLF4J:
 *   - error: something failed and needs attention
 *   - warn:  something unexpected happened, but the app can continue
 *
 * SLF4J itself only defines the API (the Logger interface). Logback is the
 * actual logging implementation that does the work underneath, configured
 * via logback.xml on the classpath.
 */
public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.error("This is an error message");
        logger.warn("This is a warning message");

        // A couple of extra, slightly more realistic examples:
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            // Passing the exception as the last argument lets Logback print
            // the full stack trace along with the message.
            logger.error("Failed to perform division", e);
        }

        int retriesRemaining = 1;
        if (retriesRemaining <= 1) {
            logger.warn("Low on retries, only {} attempt remaining", retriesRemaining);
        }
    }
}
