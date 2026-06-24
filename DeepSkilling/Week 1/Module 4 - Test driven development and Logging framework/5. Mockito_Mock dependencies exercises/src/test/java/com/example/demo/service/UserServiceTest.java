package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercise 2: Mocking a Repository in a Service Test.
 *
 * This is a plain Mockito unit test (no Spring context is started at all,
 * which makes it very fast). MockitoExtension enables the @Mock and
 * @InjectMocks annotations.
 *
 * @Mock creates a mock UserRepository.
 * @InjectMocks creates a real UserService instance and injects the mocked
 * repository into its userRepository field.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // Arrange
        User user = new User(1L, "Jane Smith");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Jane Smith", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_shouldReturnNull_whenUserDoesNotExist() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        User result = userService.getUserById(99L);

        // Assert
        assertNull(result);
        verify(userRepository, times(1)).findById(99L);
    }
}
