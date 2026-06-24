package com.example.mockitoadv;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 5: Mocking Multiple Return Values.
 *
 * Reuses the same Repository / Service pair from Exercise 1, but stubs
 * getData() to return a different value on each consecutive call:
 * the first call returns "First Mock Data", the second returns
 * "Second Mock Data". Any calls beyond that would keep returning
 * "Second Mock Data" (the last stubbed value).
 */
public class MultiReturnServiceTest {

    @Test
    public void testServiceWithMultipleReturnValues() {
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
                .thenReturn("First Mock Data")
                .thenReturn("Second Mock Data");

        Service service = new Service(mockRepository);
        String firstResult = service.processData();
        String secondResult = service.processData();

        assertEquals("Processed First Mock Data", firstResult);
        assertEquals("Processed Second Mock Data", secondResult);
    }
}
