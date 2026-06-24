package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 1: Mocking a Service Dependency in a Controller Test.
 *
 * @WebMvcTest loads only the web layer (controllers, filters, etc.) for the
 * given controller, instead of the full application context. This makes the
 * test fast and focused purely on the controller's behavior.
 *
 * @MockBean tells Spring to put a Mockito mock of UserService into the
 * application context, replacing the real bean. This means no real
 * UserRepository or database is ever touched.
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User(1L, "John Doe");
    }

    @Test
    void getUser_shouldReturnUser_whenUserExists() throws Exception {
        // Arrange: mock the service to return our sample user
        when(userService.getUserById(1L)).thenReturn(sampleUser);

        // Act + Assert: perform GET request and verify the JSON response
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void getUser_shouldReturnEmptyBody_whenUserDoesNotExist() throws Exception {
        // Arrange: mock the service to return null for a missing user
        when(userService.getUserById(99L)).thenReturn(null);

        // Act + Assert: controller still returns 200 OK with an empty/null body,
        // because the controller code does not handle the not-found case explicitly
        mockMvc.perform(get("/users/{id}", 99L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
