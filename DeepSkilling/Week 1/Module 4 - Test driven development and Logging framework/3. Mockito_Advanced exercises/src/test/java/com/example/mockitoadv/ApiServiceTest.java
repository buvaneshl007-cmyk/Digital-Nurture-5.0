package com.example.mockitoadv;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 2: Mocking External Services (RESTful APIs).
 *
 * Mocks a RestClient so the test never makes a real HTTP call. ApiService
 * wraps whatever the client returns with a "Fetched " prefix.
 */
public class ApiServiceTest {

    @Test
    public void testServiceWithMockRestClient() {
        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenReturn("Mock Response");

        ApiService apiService = new ApiService(mockRestClient);
        String result = apiService.fetchData();

        assertEquals("Fetched Mock Response", result);
    }
}
