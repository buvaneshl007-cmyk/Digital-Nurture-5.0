package com.example.mockitoadv;

/**
 * The class under test in Exercise 1 and Exercise 5. It depends on a
 * Repository (injected via constructor), fetches raw data from it, and
 * applies some simple "processing" — here, just prefixing the string with
 * "Processed " — to keep the example easy to follow and verify in tests.
 */
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        String data = repository.getData();
        return "Processed " + data;
    }
}
