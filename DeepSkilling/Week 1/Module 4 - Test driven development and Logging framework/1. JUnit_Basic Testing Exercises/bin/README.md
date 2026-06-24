# JUnit Testing Exercises

A small Maven project containing four JUnit (JUnit 4) exercises.

## Project Structure

```
junit-exercises/
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java/com/example/Calculator.java        (class under test)
    └── test
        └── java/com/example
            ├── CalculatorTest.java                  (Exercise 2)
            ├── AssertionsTest.java                   (Exercise 3)
            └── AAAPatternTest.java                   (Exercise 4)
```

## Exercise 1 — Setting Up JUnit
The `pom.xml` already includes the JUnit 4.13.2 dependency:

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

Open this folder as a Maven project in your IDE (IntelliJ IDEA, Eclipse, VS Code)
and it should resolve automatically.

## Exercise 2 — Writing Basic JUnit Tests
`Calculator.java` is a simple class with `add`, `subtract`, `multiply`, `divide`,
and `isEven` methods. `CalculatorTest.java` contains basic `@Test` methods for each.

## Exercise 3 — Assertions in JUnit
`AssertionsTest.java` demonstrates common assertions:
`assertEquals`, `assertTrue`, `assertFalse`, `assertNull`, `assertNotNull`,
`assertArrayEquals`, `assertSame`, `assertNotSame`, and delta-based equality
for doubles.

## Exercise 4 — AAA Pattern, Fixtures, Setup/Teardown
`AAAPatternTest.java` shows:
- The **Arrange–Act–Assert** pattern within each test method (clearly commented).
- `@Before` / `@After` — run before/after **every** test method (fresh fixtures).
- `@BeforeClass` / `@AfterClass` — run **once** for the whole test class.

## How to Run the Tests

With Maven installed:

```bash
mvn test
```

Or run the test classes directly from your IDE (right-click → Run Tests).

## Requirements
- Java 11+
- Maven 3.6+ (if running from the command line)
