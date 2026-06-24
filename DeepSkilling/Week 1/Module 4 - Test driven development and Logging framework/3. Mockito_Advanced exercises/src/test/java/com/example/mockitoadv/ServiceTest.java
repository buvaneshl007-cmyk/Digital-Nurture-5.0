package com.example.mockitoadv;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 1: Mocking Databases and Repositories.
 *
 * Mocks a Repository so the test never touches a real database. The
 * service under test (Service) simply prefixes whatever the repository
 * returns with "Processed ", which is what we assert on.
 */
public class ServiceTest {

    @Test
    public void testServiceWithMockRepository() {
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");

        Service service = new Service(mockRepository);
        String result = service.processData();

        assertEquals("Processed Mock Data", result);
    }
}
