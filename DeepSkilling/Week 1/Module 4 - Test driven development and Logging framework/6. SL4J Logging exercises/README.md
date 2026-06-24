# Logging using SLF4J — Exercises

Three small, runnable examples demonstrating common SLF4J + Logback patterns.

## Folder structure

```
slf4j-logging-exercises/
├── pom.xml
└── src/main
    ├── java/com/example/logging
    │   ├── LoggingExample.java               (Exercise 1)
    │   ├── ParameterizedLoggingExample.java   (Exercise 2)
    │   └── AppenderExample.java               (Exercise 3)
    └── resources
        └── logback.xml                        (Exercise 3 config)
```

## Exercise 1 — `LoggingExample`

Logs at `error` and `warn` levels. Also shows logging an exception (so the
stack trace gets printed) and a warning driven by a simple condition.

## Exercise 2 — `ParameterizedLoggingExample`

Shows SLF4J's `{}` placeholder style instead of string concatenation:

```java
logger.info("User {} logged in successfully", username);
logger.error("Order {} for user {} with amount {} has status {}",
        orderId, username, amount, status);
```

This is more efficient (the message is only built if the log level is
enabled) and easier to read than `"User " + username + " logged in"`.

It also shows combining placeholders with a trailing exception argument,
which SLF4J interprets as the throwable for the stack trace rather than as
a placeholder value.

## Exercise 3 — `AppenderExample` + `logback.xml`

`logback.xml` defines three appenders, all attached to the root logger:

- **CONSOLE** — prints to stdout
- **FILE** — writes every log line to `app.log`
- **ROLLING_FILE** — writes to `app-rolling.<date>.log`, rotating daily and
  keeping the last 7 days (via `TimeBasedRollingPolicy`)

Run `AppenderExample`, and the same log lines will appear in your terminal
**and** get written to both files in the project's working directory.

> Note: the root logger level is `INFO`, so the `logger.debug(...)` call in
> `AppenderExample` intentionally does **not** show up anywhere — that's
> included to illustrate how log levels filter output.

## How to run

With Maven installed:

```bash
mvn compile

# Exercise 1
mvn exec:java -Dexec.mainClass="com.example.logging.LoggingExample"

# Exercise 2
mvn exec:java -Dexec.mainClass="com.example.logging.ParameterizedLoggingExample"

# Exercise 3
mvn exec:java -Dexec.mainClass="com.example.logging.AppenderExample"
```

Or just open the folder in an IDE (IntelliJ/Eclipse) with Maven support and
run each `main` method directly.

## Note on dependency versions

The exercise prompt specified `slf4j-api 1.7.30` and `logback-classic
1.2.3`. Those are old releases, so this project's `pom.xml` uses current,
compatible versions (`slf4j-api 2.0.13`, `logback-classic 1.5.6`) instead —
the API and `logback.xml` syntax used here work the same way. If you
specifically need the older versions (e.g. to match a course requirement),
just edit the `<slf4j.version>` / `<logback.version>` properties in
`pom.xml`.
