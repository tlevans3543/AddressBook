package tests.unitTests;

import AddressBook.AddressBook;
import AddressBook.Person;
import AddressBook.FileSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FileSystemTest {

    FileSystem testFileSystem = new FileSystem();
    AddressBook saveBook;
    Person savePerson;

    @BeforeEach
    public void setUp(){
        saveBook = new AddressBook();
        savePerson = new Person("fName","lName","address","city",
                "state","zip","phone");
    }

    @AfterEach
    public void tearDown(){
        saveBook = null;
        savePerson = null;
    }

    @Test
    void readFileTest() throws IOException, SQLException {
        saveBook.add(savePerson);
        File file = new File("src/tests/resources/readFile");

        new FileSystem().saveFile(saveBook, file);
        new FileSystem().readFile(saveBook, file);

        Assertions.assertEquals("fName",saveBook.get(0).getFirstName());

        try {
            new FileSystem().readFile(saveBook, new File("src/tests/resources/failFile"));
        } catch (FileNotFoundException | SQLException e){
            e.printStackTrace();
        }


    }

    @Test
    void saveFileTest() {
        File saveFile = new File("src/tests/resources/savedFile");
        saveBook.add(savePerson);
        try{
            new FileSystem().saveFile(saveBook, saveFile);
        } catch (Exception e){
            e.printStackTrace();
        }
        Assertions.assertTrue(saveFile.exists());
    }
}