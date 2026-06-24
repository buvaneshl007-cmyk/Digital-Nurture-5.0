package com.example.mockitoadv;

/**
 * Stands in for a file-reading abstraction. Named MyFileReader (rather than
 * FileReader) to avoid clashing with java.io.FileReader, since both would
 * otherwise live in the same package and confuse imports.
 */
public interface MyFileReader {
    String read();
}
