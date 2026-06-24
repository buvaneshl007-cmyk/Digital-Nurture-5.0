package com.example.mockitoadv;

/**
 * Stands in for a database repository (e.g. a Spring Data repository, or a
 * hand-rolled DAO). Exercise 1 mocks this so the test never touches a real
 * database.
 */
public interface Repository {
    String getData();
}
