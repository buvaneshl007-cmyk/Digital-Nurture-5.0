package com.example.mockitoadv;

/**
 * The class under test in Exercise 3. Reads content via MyFileReader,
 * processes it, and writes the processed result via MyFileWriter, then
 * returns the processed content too (handy for assertions in the test).
 */
public class FileService {

    private final MyFileReader fileReader;
    private final MyFileWriter fileWriter;

    public FileService(MyFileReader fileReader, MyFileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        String processedContent = "Processed " + content;
        fileWriter.write(processedContent);
        return processedContent;
    }
}
