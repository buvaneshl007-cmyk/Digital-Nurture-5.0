package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Original method as given in the prompt: returns null if the user
     * isn't found, rather than throwing.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Used by Exercise 6 and Exercise 8. Throws NoSuchElementException when
     * the user isn't found, which is what GlobalExceptionHandler (Exercise 8)
     * is designed to catch. Kept as a separate method from getUserById so
     * the original null-returning behavior from the prompt is preserved
     * unchanged, while still giving the exception-handling exercises
     * something realistic to test.
     */
    public User getUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    /**
     * Used by Exercise 5 (POST endpoint test).
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
