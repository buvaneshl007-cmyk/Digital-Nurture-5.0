package com.example.mockitoadv;

/**
 * The class under test in Exercise 4. Depends on a NetworkClient (injected
 * via constructor) and wraps its connection result with a descriptive
 * prefix.
 */
public class NetworkService {

    private final NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {
        String connection = networkClient.connect();
        return "Connected to " + connection;
    }
}
