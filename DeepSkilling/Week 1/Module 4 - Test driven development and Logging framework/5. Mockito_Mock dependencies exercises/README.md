# Mocking Dependencies in Spring Tests using Mockito

This project contains a runnable Maven setup demonstrating three ways of
mocking dependencies in Spring Boot tests, as requested:

1. **Exercise 1** — Mocking a Service in a Controller test (`@WebMvcTest`)
2. **Exercise 2** — Mocking a Repository in a Service test (plain Mockito)
3. **Exercise 3** — Mocking a Service in a full Integration test (`@SpringBootTest` + `@AutoConfigureMockMvc`)

## Folder structure

```
spring-mockito-exercises/
├── pom.xml
└── src
    ├── main
    │   ├── java/com/example/demo
    │   │   ├── DemoApplication.java
    │   │   ├── entity/User.java
    │   │   ├── repository/UserRepository.java
    │   │   ├── service/UserService.java
    │   │   └── controller/UserController.java
    │   └── resources/application.properties
    └── test
        └── java/com/example/demo
            ├── controller/UserControllerTest.java     (Exercise 1)
            ├── service/UserServiceTest.java            (Exercise 2)
            └── integration/UserIntegrationTest.java    (Exercise 3)
```

## Exercise 1 — `UserControllerTest`

- Annotated with `@WebMvcTest(UserController.class)`, which loads **only** the
  web layer instead of the whole application context.
- `UserService` is replaced with a Mockito mock using `@MockBean`.
- `MockMvc` is used to fire a simulated `GET /users/1` request and the JSON
  response is verified with `jsonPath`.
- Covers both the "user found" and "user not found" cases.

## Exercise 2 — `UserServiceTest`

- A pure Mockito unit test — no Spring context is started at all
  (`@ExtendWith(MockitoExtension.class)`), so it's the fastest of the three.
- `UserRepository` is mocked with `@Mock`.
- `UserService` is instantiated for real and the mock repository is injected
  into it automatically via `@InjectMocks`.
- `verify()` confirms the repository method was actually called.

## Exercise 3 — `UserIntegrationTest`

- Annotated with `@SpringBootTest`, which boots the **entire** application
  context (a true integration test), plus `@AutoConfigureMockMvc` to get a
  `MockMvc` bean without starting a real HTTP server/port.
- `UserService` is still mocked with `@MockBean`, so the real
  `UserRepository`/database is never touched, but the rest of the wiring
  (dispatcher servlet, controller, JSON conversion) all runs for real.
- An H2 in-memory database dependency is included purely so the full
  application context has something to connect to when it starts up.

## How to run

```bash
mvn test
```

This runs all three test classes. You can also run a single class, e.g.:

```bash
mvn -Dtest=UserServiceTest test
```

## Notes

- Requires Java 17+ and Maven (or just open the folder in an IDE like
  IntelliJ/Eclipse with Maven support — it will pick up `pom.xml`
  automatically).
- `spring-boot-starter-test` already pulls in JUnit 5, Mockito, AssertJ, and
  Spring's test utilities, so no extra dependencies are needed for any of the
  three exercises.
