package com.example.mockitodemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.*;

/**
 * Mockito Hands-On Exercises 1-7.
 *
 * @ExtendWith(MockitoExtension.class) enables @Mock / @InjectMocks so we
 * don't have to call Mockito.openMocks(this) manually in a @BeforeEach.
 *
 * @Mock creates a mock ExternalApi.
 * @InjectMocks creates a real MyService and injects the mock into its
 * constructor automatically.
 */
@ExtendWith(MockitoExtension.class)
public class MyServiceTest {

    @Mock
    private ExternalApi mockApi;

    @InjectMocks
    private MyService service;

    // -----------------------------------------------------------------
    // Exercise 1: Mocking and Stubbing
    // -----------------------------------------------------------------
    /**
     * Stub getData() to return a fixed value, then assert that MyService
     * returns exactly that value. This is the most basic Mockito pattern:
     * "when this mock method is called, then return this value".
     */
    @Test
    public void testExternalApi() {
        when(mockApi.getData()).thenReturn("Mock Data");

        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }

    // -----------------------------------------------------------------
    // Exercise 2: Verifying Interactions
    // -----------------------------------------------------------------
    /**
     * Here we don't care what getData() returns — we only care that
     * MyService actually called it. verify() fails the test if the mock
     * method was never invoked.
     */
    @Test
    public void testVerifyInteraction() {
        service.fetchData();

        verify(mockApi).getData();
    }

    // -----------------------------------------------------------------
    // Exercise 3: Argument Matching
    // -----------------------------------------------------------------
    /**
     * Argument matchers let you stub/verify calls based on flexible
     * conditions on the arguments, not just exact equality.
     *
     *   - eq("123")       -> argument must equal exactly "123"
     *   - anyString()     -> any non-null String argument
     *   - startsWith("U") -> any String starting with "U"
     *
     * Important Mockito rule: if you use a matcher for ANY argument in a
     * call, you must use matchers for ALL arguments of that call (you
     * can't mix a raw value and a matcher in the same call).
     */
    @Test
    public void testArgumentMatching_exactValue() {
        when(mockApi.getDataById(eq("123"))).thenReturn("Data for 123");

        String result = service.fetchDataById("123");

        assertEquals("Data for 123", result);
        verify(mockApi).getDataById("123");
    }

    @Test
    public void testArgumentMatching_anyString() {
        when(mockApi.getDataById(anyString())).thenReturn("Some Data");

        String result = service.fetchDataById("any-id-at-all");

        assertEquals("Some Data", result);
        // Verify it was called with an argument matching a custom condition
        verify(mockApi).getDataById(startsWith("any"));
    }

    // -----------------------------------------------------------------
    // Exercise 4: Handling Void Methods
    // -----------------------------------------------------------------
    /**
     * Void methods can't be used with when(...) the normal way, because
     * when(mockApi.logRequest(...)) wouldn't compile — there's no return
     * value to pass to when(). Two common approaches:
     *
     *   1. Do nothing (the default mock behavior already does nothing,
     *      this just makes it explicit): doNothing().when(mock).voidMethod();
     *   2. Just verify the interaction happened with the right argument.
     */
    @Test
    public void testHandlingVoidMethod() {
        doNothing().when(mockApi).logRequest(anyString());

        service.processRequest("Hello World");

        verify(mockApi).logRequest("Hello World");
    }

    // -----------------------------------------------------------------
    // Exercise 5: Mocking and Stubbing with Multiple Returns
    // -----------------------------------------------------------------
    /**
     * thenReturn() accepts multiple values: the first call returns the
     * first value, the second call returns the second value, and every
     * call after that keeps returning the LAST value specified.
     */
    @Test
    public void testMultipleReturnValues() {
        when(mockApi.getData())
                .thenReturn("First Call Data")
                .thenReturn("Second Call Data")
                .thenReturn("Third Call Data");

        assertEquals("First Call Data", service.fetchData());
        assertEquals("Second Call Data", service.fetchData());
        assertEquals("Third Call Data", service.fetchData());
        // Any further calls keep returning the last stubbed value
        assertEquals("Third Call Data", service.fetchData());

        verify(mockApi, times(4)).getData();
    }

    // -----------------------------------------------------------------
    // Exercise 6: Verifying Interaction Order
    // -----------------------------------------------------------------
    /**
     * InOrder lets you assert that mock methods were called in a specific
     * sequence, which plain verify() calls alone cannot guarantee (each
     * verify() on its own only checks that a call happened, not when,
     * relative to other calls).
     */
    @Test
    public void testVerifyInteractionOrder() {
        service.processAndNotify("Order #42");

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).logRequest("Order #42");
        inOrder.verify(mockApi).sendNotification("Order #42");

        // This also confirms both methods were called exactly once each
        verify(mockApi, times(1)).logRequest("Order #42");
        verify(mockApi, times(1)).sendNotification("Order #42");
    }

    // -----------------------------------------------------------------
    // Exercise 7: Handling Void Methods with Exceptions
    // -----------------------------------------------------------------
    /**
     * To make a void method throw, you can't use when(...).thenThrow(...)
     * (again, because the method has no return value to call when() on).
     * Instead, use doThrow(...).when(mock).voidMethod(...).
     */
    @Test
    public void testVoidMethodThrowsException() {
        doThrow(new RuntimeException("External API failure"))
                .when(mockApi).logRequest(anyString());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.processRequest("This will fail")
        );

        assertEquals("External API failure", exception.getMessage());
        verify(mockApi).logRequest("This will fail");
    }
}
