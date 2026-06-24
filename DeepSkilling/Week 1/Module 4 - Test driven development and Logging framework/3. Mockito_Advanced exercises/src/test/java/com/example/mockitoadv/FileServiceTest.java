package com.example.mockitoadv;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 3: Mocking File I/O.
 *
 * Mocks both MyFileReader and MyFileWriter so the test never touches the
 * real filesystem. FileService reads content, prefixes it with
 * "Processed ", writes the result via the (mocked) writer, and returns it.
 *
 * Note: only the reader is stubbed with when(...).thenReturn(...), since
 * MyFileWriter.write(...) returns void — there is nothing to stub a return
 * value for. The test still implicitly exercises the writer mock (it just
 * does nothing by default), which is enough here since the assertion is on
 * FileService's return value rather than on the writer being called.
 */
public class FileServiceTest {

    @Test
    public void testServiceWithMockFileIO() {
        MyFileReader mockFileReader = mock(MyFileReader.class);
        MyFileWriter mockFileWriter = mock(MyFileWriter.class);
        when(mockFileReader.read()).thenReturn("Mock File Content");

        FileService fileService = new FileService(mockFileReader, mockFileWriter);
        String result = fileService.processFile();

        assertEquals("Processed Mock File Content", result);
    }
}
