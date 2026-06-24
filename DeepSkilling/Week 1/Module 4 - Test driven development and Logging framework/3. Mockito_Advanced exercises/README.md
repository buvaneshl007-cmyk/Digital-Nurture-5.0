# Advanced Mockito Hands-On Exercises

A Maven project implementing the 5 advanced exercises from the prompt. The
document provided the test code already; this project adds the production
classes (`Repository`, `Service`, `RestClient`, `ApiService`, `MyFileReader`,
`MyFileWriter`, `FileService`, `NetworkClient`, `NetworkService`) that those
tests depend on and need in order to compile and run.

## Folder structure

```
advanced-mockito-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/mockitoadv
    │   ├── Repository.java          (Exercise 1, 5)
    │   ├── Service.java              (Exercise 1, 5)
    │   ├── RestClient.java           (Exercise 2)
    │   ├── ApiService.java           (Exercise 2)
    │   ├── MyFileReader.java         (Exercise 3)
    │   ├── MyFileWriter.java         (Exercise 3)
    │   ├── FileService.java          (Exercise 3)
    │   ├── NetworkClient.java        (Exercise 4)
    │   └── NetworkService.java       (Exercise 4)
    └── test/java/com/example/mockitoadv
        ├── ServiceTest.java              (Exercise 1)
        ├── ApiServiceTest.java           (Exercise 2)
        ├── FileServiceTest.java          (Exercise 3)
        ├── NetworkServiceTest.java       (Exercise 4)
        └── MultiReturnServiceTest.java   (Exercise 5)
```

## A note on naming

`MyFileReader` and `MyFileWriter` are named that way (instead of
`FileReader`/`FileWriter`, as casually referenced in Exercise 3) specifically
to avoid colliding with `java.io.FileReader` / `java.io.FileWriter`, which
are standard JDK classes. Using the same names here would either cause
confusing shadowing or compile errors depending on import order, so the
interfaces were renamed for clarity. Everything else matches the document
as given.

## Exercise-by-exercise summary

### Exercise 1 — Mocking Databases and Repositories
`Repository.getData()` is mocked to return `"Mock Data"`. `Service`
prefixes whatever the repository returns with `"Processed "`.

### Exercise 2 — Mocking External Services (RESTful APIs)
`RestClient.getResponse()` is mocked to return `"Mock Response"`.
`ApiService` prefixes it with `"Fetched "`.

### Exercise 3 — Mocking File I/O
Both `MyFileReader` and `MyFileWriter` are mocked. `FileService` reads
content, prefixes it with `"Processed "`, writes the result through the
writer, and returns the processed string (which the test asserts on).

### Exercise 4 — Mocking Network Interactions
`NetworkClient.connect()` is mocked to return `"Mock Connection"`.
`NetworkService` prefixes it with `"Connected to "`.

### Exercise 5 — Mocking Multiple Return Values
Reuses `Repository`/`Service` from Exercise 1, but stubs `getData()` with
two chained `.thenReturn(...)` calls so the first and second invocations
return different values — demonstrating Mockito's consecutive-call
stubbing.

## How to run

```bash
mvn test
```

Or run any individual test class/method directly from your IDE.
