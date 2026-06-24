package com.example.mockitoadv;

/**
 * Stands in for a low-level network client (e.g. a socket wrapper or
 * connection pool client). Exercise 4 mocks this so the test never opens a
 * real network connection.
 */
public interface NetworkClient {
    String connect();
}
