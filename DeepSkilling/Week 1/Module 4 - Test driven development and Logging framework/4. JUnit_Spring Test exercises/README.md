# Spring Testing Exercises 1–9

A single Maven project covering all 9 exercises, with the supporting
entity/repository/service/controller classes needed to make each test
runnable.

## Folder structure

```
spring-testing-exercises/
├── pom.xml
└── src
    ├── main
    │   ├── java/com/example/demo
    │   │   ├── DemoApplication.java
    │   │   ├── entity/User.java
    │   │   ├── repository/UserRepository.java          (findByName for Ex. 7)
    │   │   ├── service/CalculatorService.java           (Ex. 1, 9)
    │   │   ├── service/UserService.java                 (Ex. 2, 5, 6)
    │   │   ├── controller/UserController.java           (Ex. 3, 4, 5, 8)
    │   │   └── exception/GlobalExceptionHandler.java     (Ex. 8)
    │   └── resources/application.properties
    └── test/java/com/example/demo
        ├── service/CalculatorServiceTest.java                  (Exercise 1)
        ├── service/UserServiceTest.java                        (Exercise 2)
        ├── controller/UserControllerTest.java                  (Exercise 3)
        ├── integration/UserIntegrationTest.java                (Exercise 4)
        ├── controller/UserControllerPostTest.java               (Exercise 5)
        ├── service/UserServiceExceptionTest.java                (Exercise 6)
        ├── repository/UserRepositoryTest.java                   (Exercise 7)
        ├── controller/GlobalExceptionHandlerTest.java           (Exercise 8)
        └── service/CalculatorServiceParameterizedTest.java       (Exercise 9)
```

## A few design decisions worth knowing about

- **`UserService.getUserById`** keeps the exact behavior from the prompt
  (returns `null` if not found). A second method, **`getUserByIdOrThrow`**,
  was added specifically for Exercises 6 and 8, since those need a method
  that actually throws `NoSuchElementException` for `GlobalExceptionHandler`
  to have something to catch. The original method is untouched.
- **`UserController`** has a `/users/strict/{id}` endpoint that calls
  `getUserByIdOrThrow`, used only by the Exercise 8 test, alongside the
  original `/users/{id}` endpoint from the prompt.
- **`CalculatorService`** (Exercise 1) is also reused in Exercise 9, since
  it's the simplest dependency-free method available for demonstrating
  `@ParameterizedTest` clearly.
- **Exercise 4** has no service/controller code to add since the prompt's
  Exercise 4 doesn't introduce new classes — it integration-tests the
  existing `UserController` → `UserService` → `UserRepository` chain against
  a real (embedded H2) database, with nothing mocked.

## Exercise-by-exercise summary

| # | Exercise | Test style |
|---|----------|-----------|
| 1 | Basic unit test (`CalculatorService.add`) | Plain JUnit, `new CalculatorService()` |
| 2 | Mocking a repository in a service test | `@ExtendWith(MockitoExtension.class)`, `@Mock` / `@InjectMocks` |
| 3 | REST controller with MockMvc | `@WebMvcTest` + `@MockBean` |
| 4 | Full integration test | `@SpringBootTest` + `@AutoConfigureMockMvc`, nothing mocked, real H2 DB |
| 5 | Controller POST endpoint | `@WebMvcTest` + `@MockBean`, JSON request body via `ObjectMapper` |
| 6 | Service exception handling | `@ExtendWith(MockitoExtension.class)`, `assertThrows` |
| 7 | Custom repository query | `@DataJpaTest` against an embedded DB |
| 8 | Controller exception handling (`@ControllerAdvice`) | `@WebMvcTest` + `@Import(GlobalExceptionHandler.class)` |
| 9 | Parameterized test | `@ParameterizedTest` + `@CsvSource` |

## How to run

```bash
mvn test
```

Or run any individual test class from your IDE.
