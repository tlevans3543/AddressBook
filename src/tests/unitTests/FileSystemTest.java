package tests.unitTests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystemTest {

    @Test
    public void readFile() {
       ClassLoader classLoader = getClass().getClassLoader();
       File fileToRead = new File(classLoader.getResource("tests/resources/loremipsum.txt").getFile());
        Assertions.assertTrue(fileToRead.canRead());
    }

    @Test
    public void saveFile() throws IOException {
        FileWriter fileWriter = new FileWriter("saveTestFile.txt");
        fileWriter.write("text");
        File savedFile = new File("saveTestFile.txt");
        Assertions.assertTrue(savedFile.canWrite());
    }
}