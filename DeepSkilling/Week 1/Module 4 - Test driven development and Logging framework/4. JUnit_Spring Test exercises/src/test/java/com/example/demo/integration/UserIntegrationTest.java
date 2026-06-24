package com.example.demo.integration;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 4: Integration Test with Spring Boot.
 *
 * Unlike Exercise 3, nothing is mocked here. @SpringBootTest boots the full
 * application context, including the real UserService and the real
 * UserRepository backed by the H2 in-memory database configured in
 * application.properties. A real User row is saved via the repository, and
 * then the test asks the actual HTTP endpoint for it via MockMvc, proving
 * the entire flow — controller, service, repository, database — works
 * together correctly.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getUser_shouldReturnUserPersistedInRealDatabase() throws Exception {
        // Arrange: save a real user into the H2 database via the real repository
        User savedUser = userRepository.save(new User(1L, "Charlie"));

        // Act + Assert: the full stack (controller -> service -> repository -> DB)
        // should return that exact user when queried over HTTP
        mockMvc.perform(get("/users/{id}", savedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Charlie"));
    }
}
