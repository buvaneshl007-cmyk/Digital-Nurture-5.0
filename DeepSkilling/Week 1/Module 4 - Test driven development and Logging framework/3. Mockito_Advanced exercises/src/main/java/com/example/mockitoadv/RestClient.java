package com.example.mockitoadv;

/**
 * Stands in for a RESTful API client (e.g. a RestTemplate, WebClient, or
 * Feign client wrapper). Exercise 2 mocks this so the test never makes a
 * real HTTP call.
 */
public interface RestClient {
    String getResponse();
}
