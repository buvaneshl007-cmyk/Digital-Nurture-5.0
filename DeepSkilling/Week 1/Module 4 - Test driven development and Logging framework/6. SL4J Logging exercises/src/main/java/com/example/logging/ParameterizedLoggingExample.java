package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 2: Parameterized Logging.
 *
 * Instead of building log messages with string concatenation (e.g.
 * "User " + name + " logged in"), SLF4J supports {} placeholders. This is
 * preferred for two reasons:
 *
 *   1. Performance: if the log level is disabled (e.g. DEBUG is turned off
 *      in production), the message is never actually built/concatenated.
 *      With string concatenation, the concatenation happens regardless of
 *      whether the log statement is enabled.
 *   2. Readability: placeholders keep the message template clean and easy
 *      to read at a glance.
 */
public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "john_doe";
        int loginAttempts = 3;

        // Single parameter
        logger.info("User {} logged in successfully", username);

        // Two parameters
        logger.warn("User {} failed to log in after {} attempts", username, loginAttempts);

        // More than two parameters require an Object[] (varargs)
        String orderId = "ORD-1001";
        double amount = 249.99;
        String status = "FAILED";
        logger.error("Order {} for user {} with amount {} has status {}",
                orderId, username, amount, status);

        // Parameterized logging combined with an exception as the final argument.
        // Note: when an exception is the last argument and there are also {}
        // placeholders, SLF4J uses it for the stack trace, not as a placeholder value.
        try {
            processOrder(orderId);
        } catch (IllegalStateException e) {
            logger.error("Failed to process order {}", orderId, e);
        }
    }

    private static void processOrder(String orderId) {
        throw new IllegalStateException("Inventory not available for order " + orderId);
    }
}
