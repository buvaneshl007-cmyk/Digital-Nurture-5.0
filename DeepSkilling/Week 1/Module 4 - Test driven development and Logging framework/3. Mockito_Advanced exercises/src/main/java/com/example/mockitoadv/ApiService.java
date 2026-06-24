package com.example.mockitoadv;

/**
 * The class under test in Exercise 2. Depends on a RestClient (injected via
 * constructor) and wraps its raw response with a descriptive prefix.
 */
public class ApiService {

    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String fetchData() {
        String response = restClient.getResponse();
        return "Fetched " + response;
    }
}
