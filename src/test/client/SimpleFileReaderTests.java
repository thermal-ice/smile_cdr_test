package client;

import org.junit.*;

import java.nio.file.NoSuchFileException;

import static org.junit.Assert.*;

public class SimpleFileReaderTests {

    SimpleFileReader fileReader;

    @Test
    public void testValidPath(){
        fileReader = new SimpleFileReader("src/main/Readfiles/last_names.txt");
        assertEquals(20, fileReader.getLastNamesList().size());
    }

    @Test(expected = NoSuchFileException.class)
    public void testInvalidPath(){
        fileReader = new SimpleFileReader("src/blah.txt");
        assertNotEquals(20, fileReader.getLastNamesList().size());
    }
}
