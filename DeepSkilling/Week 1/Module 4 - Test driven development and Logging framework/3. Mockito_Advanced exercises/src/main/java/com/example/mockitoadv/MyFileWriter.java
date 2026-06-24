package com.example.mockitoadv;

/**
 * Stands in for a file-writing abstraction. Named MyFileWriter (rather than
 * FileWriter) to avoid clashing with java.io.FileWriter.
 */
public interface MyFileWriter {
    void write(String content);
}
