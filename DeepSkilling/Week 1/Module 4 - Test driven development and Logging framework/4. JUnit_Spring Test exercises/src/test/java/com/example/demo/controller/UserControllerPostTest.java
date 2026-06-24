package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 5: Test Controller POST Endpoint.
 *
 * Tests UserController's createUser endpoint. The request body is built
 * with Jackson's ObjectMapper (already on the classpath via
 * spring-boot-starter-web) and sent as JSON. UserService.saveUser(...) is
 * mocked so this stays a focused web-layer test rather than touching a
 * real database.
 */
@WebMvcTest(UserController.class)
public class UserControllerPostTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser_shouldReturnSavedUser() throws Exception {
        User requestUser = new User(null, "Diana");
        User savedUser = new User(10L, "Diana");

        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.name").value("Diana"));
    }
}
