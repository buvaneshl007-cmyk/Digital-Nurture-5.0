package com.example.mockitodemo;

/**
 * The class under test in every exercise. It depends on ExternalApi, which
 * is injected through the constructor — this is what makes it easy to pass
 * in a Mockito mock during tests instead of a real implementation.
 */
public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    /** Exercise 1, 2, 5: simply delegates to the external API and returns its data. */
    public String fetchData() {
        return externalApi.getData();
    }

    /** Exercise 3: delegates to the external API with a specific id argument. */
    public String fetchDataById(String id) {
        return externalApi.getDataById(id);
    }

    /** Exercise 4, 7: performs an action via a void method on the external API. */
    public void processRequest(String message) {
        externalApi.logRequest(message);
    }

    /**
     * Exercise 6: calls two void methods in a specific order — first logs
     * the request, then sends a notification. Tests can verify this order
     * using Mockito's InOrder API.
     */
    public void processAndNotify(String message) {
        externalApi.logRequest(message);
        externalApi.sendNotification(message);
    }
}
