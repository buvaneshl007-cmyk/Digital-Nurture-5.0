package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Exercise 6: Test Service Exception Handling.
 *
 * Verifies that UserService.getUserByIdOrThrow(...) throws a
 * NoSuchElementException (rather than silently returning null) when the
 * repository has no matching user. This is the method GlobalExceptionHandler
 * (Exercise 8) is built to catch.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceExceptionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserByIdOrThrow_shouldThrowNoSuchElementException_whenUserMissing() {
        when(userRepository.findById(404L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> userService.getUserByIdOrThrow(404L)
        );

        assertEquals("User not found with id: 404", exception.getMessage());
    }
}
