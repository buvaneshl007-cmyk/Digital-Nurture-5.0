package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3: Using Different Appenders.
 *
 * This class doesn't reference appenders directly in code — appenders are
 * configured entirely in src/main/resources/logback.xml. By attaching the
 * CONSOLE, FILE, and ROLLING_FILE appenders to the root logger, every log
 * statement below is automatically sent to all three destinations:
 *
 *   - the console (stdout)
 *   - app.log (a single, ever-growing file)
 *   - app-rolling.<date>.log (rotated daily, keeping the last 7 days)
 *
 * Run this class and then check the project's working directory: you should
 * see the same messages printed to your terminal AND written into app.log
 * and an app-rolling.<date>.log file.
 */
public class AppenderExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.debug("This debug message will NOT appear, because root level is INFO");
        logger.warn("Disk space is running low");
        logger.error("Failed to connect to external payment service");

        for (int i = 1; i <= 3; i++) {
            logger.info("Processing batch job {} of {}", i, 3);
        }

        logger.info("Application finished. Check the console output above, " +
                "and also app.log / app-rolling.*.log in the project directory.");
    }
}
