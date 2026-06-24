package com.example.mockitodemo;

/**
 * A stand-in for some external API/service that MyService depends on.
 * In a real project this might be a REST client, a payment gateway SDK,
 * a third-party library wrapper, etc. We mock this interface in every
 * exercise so tests never make a real network call.
 */
public interface ExternalApi {

    /** Exercise 1, 2, 5: returns some data. */
    String getData();

    /** Exercise 3: returns data filtered/looked-up by a specific id. */
    String getDataById(String id);

    /** Exercise 4, 7: a void method that performs a side-effecting action. */
    void logRequest(String message);

    /** Exercise 6: a second void action, used to verify call ordering. */
    void sendNotification(String message);
}
