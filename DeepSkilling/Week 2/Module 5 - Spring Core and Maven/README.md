# Library Management - Spring Exercises 1-9

This package contains two Maven projects that together cover all 9 exercises:

## 1. LibraryManagement-XML  (Exercises 1, 2, 3, 4, 5, 6, 7, 8)
Classic Spring Framework project using `applicationContext.xml`.

- `pom.xml` - Maven config with spring-context, spring-aop, aspectjweaver,
  spring-webmvc, and the Maven Compiler Plugin set to Java 1.8 (Exercise 4).
- `src/main/resources/applicationContext.xml` - Bean definitions, component-scan,
  and AspectJ auto-proxy configuration (Exercises 1, 5, 6, 7, 8).
- `com.library.repository.BookRepository` - `@Repository` (Exercise 1, 6).
- `com.library.service.BookService` - `@Service` with both a constructor and a
  setter for `BookRepository` (Exercises 2, 6, 7).
- `com.library.aspect.LoggingAspect` - `@Before`, `@After`, and `@Around` advice
  that logs method execution time (Exercises 3, 8).
- `com.library.LibraryManagementApplication` - main class that loads the Spring
  context and exercises all the wiring styles.

### How to run
```bash
cd LibraryManagement-XML
mvn clean compile exec:java
```

## 2. LibraryManagement-Boot  (Exercise 9)
Spring Boot project (Spring Web + Spring Data JPA + H2).

- `pom.xml` - Spring Boot starter parent, Web, Data JPA, and H2 dependencies.
- `application.properties` - H2 in-memory datasource configuration.
- `com.library.boot.entity.Book` - JPA entity.
- `com.library.boot.repository.BookRepository` - Spring Data JPA repository.
- `com.library.boot.controller.BookController` - REST controller with full CRUD
  endpoints (`GET /api/books`, `GET /api/books/{id}`, `POST /api/books`,
  `PUT /api/books/{id}`, `DELETE /api/books/{id}`).
- `com.library.boot.LibraryManagementBootApplication` - Spring Boot entry point.

### How to run
```bash
cd LibraryManagement-Boot
mvn spring-boot:run
```
Then test, e.g.:
```bash
curl http://localhost:8080/api/books
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Boot in Action","author":"Craig Walls","isbn":"9781617292545"}'
```
H2 console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:librarydb`)

## Notes
- These projects were not built/run in this environment (no internet access
  to Maven Central here), so before running, make sure you have an internet
  connection so Maven can download the dependencies, and a JDK 8+ installed.
- If you upgrade `LibraryManagement-Boot` to Spring Boot 3.x, switch the
  `javax.persistence.*` imports in `Book.java` to `jakarta.persistence.*`.
