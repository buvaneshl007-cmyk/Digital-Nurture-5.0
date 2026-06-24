package com.example.mockitoadv;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 4: Mocking Network Interactions.
 *
 * Mocks a NetworkClient so the test never opens a real network connection.
 * NetworkService wraps whatever the client's connect() call returns with a
 * "Connected to " prefix.
 */
public class NetworkServiceTest {

    @Test
    public void testServiceWithMockNetworkClient() {
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");

        NetworkService networkService = new NetworkService(mockNetworkClient);
        String result = networkService.connectToServer();

        assertEquals("Connected to Mock Connection", result);
    }
}
