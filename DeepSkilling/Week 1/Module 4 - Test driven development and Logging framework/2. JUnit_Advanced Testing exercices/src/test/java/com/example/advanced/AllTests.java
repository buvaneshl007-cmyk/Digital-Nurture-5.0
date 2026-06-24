package com.example.advanced;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Exercise 2: Test Suites and Categories
 *
 * @Suite + @SelectClasses groups several independent test classes so they
 * can all be run together with a single run command/configuration.
 *
 * Run this class directly in your IDE, or via:
 *   mvn test -Dtest=AllTests
 */
@Suite
@SelectClasses({
        EvenCheckerTest.class,
        ExceptionThrowerTest.class,
        OrderedTests.class,
        PerformanceTesterTest.class
})
public class AllTests {
    // No body needed - annotations do all the work.
}
