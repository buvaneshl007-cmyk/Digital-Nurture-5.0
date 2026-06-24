package com.example.demo.integration;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 3: Mocking a Service Dependency in an Integration Test.
 *
 * @SpringBootTest boots the full Spring application context (unlike
 * @WebMvcTest, which only loads the web layer). This is a true integration
 * test of the whole app wiring.
 *
 * @AutoConfigureMockMvc configures and injects a MockMvc instance so we can
 * perform HTTP-style requests without starting a real server on a port.
 *
 * @MockBean still replaces UserService with a Mockito mock inside the full
 * context, so the real UserRepository / database layer is never invoked,
 * while everything else (controller, dispatcher servlet, JSON conversion,
 * etc.) runs for real.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUser_shouldReturnUser_whenUserExists() throws Exception {
        // Arrange
        User user = new User(1L, "Alice Johnson");
        when(userService.getUserById(1L)).thenReturn(user);

        // Act + Assert
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice Johnson"));
    }

    @Test
    void getUser_shouldReturnOkWithEmptyBody_whenUserNotFound() throws Exception {
        // Arrange
        when(userService.getUserById(42L)).thenReturn(null);

        // Act + Assert
        mockMvc.perform(get("/users/{id}", 42L))
                .andExpect(status().isOk());
    }
}
