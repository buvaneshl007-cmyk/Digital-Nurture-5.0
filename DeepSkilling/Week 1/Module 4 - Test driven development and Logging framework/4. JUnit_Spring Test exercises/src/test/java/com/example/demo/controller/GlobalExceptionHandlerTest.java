package com.example.demo.controller;

import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 8: Test Controller Exception Handling.
 *
 * Importing GlobalExceptionHandler explicitly makes @WebMvcTest pick up the
 * @ControllerAdvice alongside UserController (by default @WebMvcTest only
 * auto-scans @Controller/@RestController beans; @ControllerAdvice classes
 * sometimes need to be included this way depending on Spring Boot version
 * and package scanning, so being explicit here keeps the test reliable).
 *
 * UserService.getUserByIdOrThrow(...) is mocked to throw
 * NoSuchElementException, which should be caught by GlobalExceptionHandler
 * and converted into a 404 response with the body "User not found".
 */
@WebMvcTest(controllers = UserController.class)
@org.springframework.context.annotation.Import(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUserStrict_shouldReturn404_whenUserNotFound() throws Exception {
        when(userService.getUserByIdOrThrow(999L))
                .thenThrow(new NoSuchElementException("User not found with id: 999"));

        mockMvc.perform(get("/users/strict/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
