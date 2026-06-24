# Mockito Hands-On Exercises

A single Maven project covering all 7 exercises. Rather than scattering
near-duplicate `MyServiceTest` classes, each exercise is implemented as its
own clearly-commented `@Test` method inside one test class, since they all
share the same `MyService` / `ExternalApi` classes under test.

## Folder structure

```
mockito-handson-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/mockitodemo
    │   ├── ExternalApi.java   (the dependency every exercise mocks)
    │   └── MyService.java     (the class under test)
    └── test/java/com/example/mockitodemo
        └── MyServiceTest.java (all 7 exercises, one method each)
```

`ExternalApi` and `MyService` were expanded slightly beyond the minimal
versions shown in Exercises 1–2 of the prompt, so that Exercises 3–7 have
something concrete to mock: an argument-taking lookup method, a void
"log" method, and a second void "notify" method (for the ordering
exercise).

## Exercise-by-exercise summary

| # | Exercise | Key Mockito API used |
|---|----------|----------------------|
| 1 | Mocking and Stubbing | `Mockito.mock(...)`, `when(...).thenReturn(...)` |
| 2 | Verifying Interactions | `verify(mock).method()` |
| 3 | Argument Matching | `eq(...)`, `anyString()`, `startsWith(...)` |
| 4 | Handling Void Methods | `doNothing().when(mock).voidMethod(...)` |
| 5 | Multiple Returns | `when(...).thenReturn(v1).thenReturn(v2)...` |
| 6 | Verifying Interaction Order | `InOrder inOrder = inOrder(mock); inOrder.verify(...)` |
| 7 | Void Methods with Exceptions | `doThrow(...).when(mock).voidMethod(...)` |

### Exercise 1 — Mocking and Stubbing
Stub `getData()` to return `"Mock Data"`, then assert `MyService.fetchData()`
returns it.

### Exercise 2 — Verifying Interactions
Calls `service.fetchData()` and uses `verify(mockApi).getData()` to confirm
the mock method was actually invoked — no assertion on a return value here.

### Exercise 3 — Argument Matching
Two variants: one using `eq("123")` for an exact match, another using
`anyString()` to stub for *any* argument and `startsWith(...)` to verify the
argument had a particular shape. Note Mockito's all-or-nothing matcher rule:
if any argument in a call uses a matcher, every argument in that call must.

### Exercise 4 — Handling Void Methods
`logRequest(String)` returns `void`, so it can't be stubbed with
`when(...)`. Instead: `doNothing().when(mockApi).logRequest(anyString())`,
then verify the call happened with the expected argument.

### Exercise 5 — Multiple Returns
`when(...).thenReturn(a).thenReturn(b).thenReturn(c)` returns `a`, then `b`,
then `c` on the 1st/2nd/3rd calls — and `c` again on every call after that.

### Exercise 6 — Verifying Interaction Order
`MyService.processAndNotify(...)` calls `logRequest(...)` and then
`sendNotification(...)`. `InOrder` is used to assert they happened in that
exact sequence, which separate `verify()` calls alone don't guarantee.

### Exercise 7 — Void Methods with Exceptions
`doThrow(new RuntimeException(...)).when(mockApi).logRequest(anyString())`
makes the mock throw when called; `assertThrows(...)` confirms `MyService`
propagates that exception, and `verify(...)` confirms the call still
happened before it threw.

## How to run

```bash
mvn test
```

Or run a single exercise method directly from your IDE by clicking the green
arrow next to that `@Test` method in `MyServiceTest.java`.
