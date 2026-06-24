# Advanced JUnit Testing Exercises (JUnit 5 / Jupiter)

These exercises use features that require **JUnit 5** (Jupiter), not JUnit 4:
`@ParameterizedTest`, `@ValueSource`, `@Suite`, `@SelectClasses`,
`@TestMethodOrder`, `@Order`, `assertThrows`, and `@Timeout`. The `pom.xml`
is configured accordingly.

## Project Structure
```
junit-advanced-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/advanced/
    │   ├── EvenChecker.java          # Exercise 1
    │   ├── ExceptionThrower.java     # Exercise 4
    │   └── PerformanceTester.java    # Exercise 5
    └── test/java/com/example/advanced/
        ├── EvenCheckerTest.java       # Exercise 1: Parameterized Tests
        ├── AllTests.java              # Exercise 2: Test Suite
        ├── OrderedTests.java          # Exercise 3: Test Execution Order
        ├── ExceptionThrowerTest.java  # Exercise 4: Exception Testing
        └── PerformanceTesterTest.java # Exercise 5: Timeout/Performance
```

## Exercise 1 — Parameterized Tests
`EvenChecker.isEven(int)` is tested by `EvenCheckerTest` using:
- `@ParameterizedTest` + `@ValueSource(ints = {...})` to run the same test
  body once per supplied integer (one for even numbers, one for odd).
- A bonus `@CsvSource` example pairing an input with its expected boolean result.

## Exercise 2 — Test Suites and Categories
`AllTests.java` uses `@Suite` and `@SelectClasses` to group
`EvenCheckerTest`, `ExceptionThrowerTest`, `OrderedTests`, and
`PerformanceTesterTest` so they can all be run together in one go.

> Note: JUnit 5 replaced JUnit 4's `@Category` with **tags** (`@Tag("...")`)
> for categorizing tests, and you can filter a suite by tag using
> `@IncludeTags("...")` / `@ExcludeTags("...")` from
> `org.junit.platform.suite.api` if you want to extend this exercise.

## Exercise 3 — Test Execution Order
`OrderedTests.java` uses `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`
at the class level and `@Order(1)`, `@Order(2)`, `@Order(3)` on each method to
force a specific run order. A shared static list (`executionLog`) is used to
prove the tests really did run in that order.

> Tests that depend on a fixed execution order are generally considered an
> anti-pattern (tests should be independent). This is shown purely to
> demonstrate the mechanism.

## Exercise 4 — Exception Testing
`ExceptionThrower.throwException(int)` throws an `IllegalArgumentException`
for negative input. `ExceptionThrowerTest` uses `assertThrows(...)` to verify
both the exception type and its message.

## Exercise 5 — Timeout and Performance Testing
`PerformanceTester.performTask(long)` simulates a slow operation.
`PerformanceTesterTest` shows three ways to enforce a time limit:
1. `@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)` on the test method.
2. `assertTimeout(Duration, Executable)` — lets the task run to completion,
   then fails if it took too long.
3. `assertTimeoutPreemptively(Duration, Executable)` — aborts the task early
   if it exceeds the duration (runs on a separate thread).

## How to Run

### With Maven
```bash
mvn test
```

To run just the suite:
```bash
mvn test -Dtest=AllTests
```

### With your IDE
Right-click any test class and choose "Run". Most modern IDEs (IntelliJ IDEA,
Eclipse, VS Code with the Java extensions) detect JUnit 5 automatically once
the Jupiter dependencies are on the classpath.

## Dependencies Used
- `junit-jupiter-api` / `junit-jupiter-engine` — core JUnit 5 testing.
- `junit-jupiter-params` — enables `@ParameterizedTest`.
- `junit-platform-suite-api` / `junit-platform-suite-engine` — enables
  `@Suite` / `@SelectClasses`.
