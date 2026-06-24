# JUnit Testing Exercises

This project contains a runnable Maven setup covering four JUnit exercises.

## Project Structure
```
junit-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/exercises/
    │   └── Calculator.java          # class under test (Exercise 2)
    └── test/java/com/example/exercises/
        ├── CalculatorTest.java      # Exercise 2: basic tests
        ├── AssertionsTest.java      # Exercise 3: assertion types
        └── AAAPatternTest.java      # Exercise 4: AAA pattern + @Before/@After
```

## Exercise 1 — Setting Up JUnit
The `pom.xml` already includes the JUnit 4.13.2 dependency with `test` scope:
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```
Open this folder as a Maven project in IntelliJ IDEA, Eclipse, or VS Code, and the IDE
will pick up the dependency automatically.

## Exercise 2 — Writing Basic JUnit Tests
`Calculator.java` is a simple class with `add`, `subtract`, `multiply`, `divide`,
and `isEven` methods. `CalculatorTest.java` contains a basic test for each method,
including a test that expects an exception (`testDivideByZeroThrowsException`).

## Exercise 3 — Assertions in JUnit
`AssertionsTest.java` demonstrates the core JUnit assertions:
`assertEquals`, `assertTrue`, `assertFalse`, `assertNull`, `assertNotNull`,
plus a few extras: `assertArrayEquals`, `assertSame`, `assertNotSame`, and
delta-based equality for floating point numbers.

## Exercise 4 — AAA Pattern, Test Fixtures, Setup/Teardown
`AAAPatternTest.java` shows:
- `@Before` (`setUp`) — runs before every test method to create fresh fixtures.
- `@After` (`tearDown`) — runs after every test method to clean up.
- Each test method structured with clear **Arrange / Act / Assert** comments.

## How to Run the Tests

### With Maven
```bash
mvn test
```

### With your IDE
Right-click any test class (e.g. `CalculatorTest`) and choose
"Run" / "Run Tests" — most IDEs detect JUnit 4 tests automatically once the
dependency is on the classpath.

## Notes
- This setup uses **JUnit 4** (`org.junit.Test`, `org.junit.Before`, `org.junit.After`)
  to match the exercise instructions exactly. If you'd like a **JUnit 5** (Jupiter)
  version instead — using `@BeforeEach`/`@AfterEach` and `org.junit.jupiter.api.*` —
  just ask and it can be converted.
