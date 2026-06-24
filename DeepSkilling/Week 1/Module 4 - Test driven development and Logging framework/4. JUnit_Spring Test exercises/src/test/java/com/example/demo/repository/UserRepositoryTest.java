package com.example.demo.repository;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 7: Test Custom Repository Query.
 *
 * @DataJpaTest spins up only the JPA-related parts of the Spring context
 * (entity manager, repositories) backed by an embedded database (H2 here),
 * and wraps each test in a transaction that's rolled back afterward. This
 * is the standard, lightweight way to test custom Spring Data query methods
 * like findByName(...) against a real (if temporary) database, rather than
 * mocking the repository itself — mocking wouldn't actually prove the query
 * derivation works.
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByName_shouldReturnMatchingUsers() {
        userRepository.save(new User(1L, "Eve"));
        userRepository.save(new User(2L, "Eve"));
        userRepository.save(new User(3L, "Frank"));

        List<User> results = userRepository.findByName("Eve");

        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(u -> u.getName().equals("Eve")));
    }

    @Test
    void findByName_shouldReturnEmptyList_whenNoMatch() {
        userRepository.save(new User(1L, "Grace"));

        List<User> results = userRepository.findByName("NonExistentName");

        assertTrue(results.isEmpty());
    }
}
