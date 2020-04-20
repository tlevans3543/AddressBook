package tests.unitTests;

import AddressBook.AddressBook;
import AddressBook.FileSystem;
import AddressBook.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class FileSystemTest {

    AddressBook saveBook;
    Person savePerson;

    @BeforeEach
    public void setUp(){
        saveBook = new AddressBook();
        savePerson = new Person("fName","lName","address","city",
                "state","00000","0001112222");
    }

    @AfterEach
    public void tearDown(){
        saveBook = null;
        savePerson = null;
    }

    @Test
    void readFileTest() throws IOException, SQLException {
        // AddressBook saveBook, Person savePerson initialized in @BeforeEach method

        // Add savePerson to saveBook
        saveBook.add(savePerson);

        // Java.io.File file - new File object with valid path
        File file = new File("src/tests/resources/readFile");

        // In order to try reading a file, it must be saved so function calls saveFile(AddressBook, file)
        new FileSystem().saveFile(saveBook, file);
        // FileSystem calls readFile(AddressBook, File)
        new FileSystem().readFile(saveBook, file);

        // Assert checks that the overrode saveBook contains the correct string in field firstName
        Assertions.assertEquals("fName",saveBook.get(0).getFirstName());

        // Function attempts to call readFile(AddressBook, File) on a file with an invalid path
        // handles exceptions with stack trace
        try {
            new FileSystem().readFile(saveBook, new File("false path"));
        } catch (FileNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void saveFileTest() {
        // AddressBook saveBook, Person savePerson initialized in @BeforeEach method

        // Java.io.File saveFile - new File object with valid path
        File saveFile = new File("src/tests/resources/savedFile");

        // Add savePerson to saveBook
        saveBook.add(savePerson);

        // Function attempts to perform saveFile(AddressBook, File) function
        // If call fails, exceptions handled with stack trace
        try{
            new FileSystem().saveFile(saveBook, saveFile);
        } catch (Exception e){
            e.printStackTrace();
        }

        // When assertion passes, shows that the newly created saveFile exists, returns true
        Assertions.assertTrue(saveFile.exists());
    }
}