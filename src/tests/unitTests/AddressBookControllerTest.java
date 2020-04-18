package tests.unitTests;

import AddressBook.AddressBook;
import AddressBook.AddressBookController;
import AddressBook.Person;
import AddressBook.FileSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class AddressBookControllerTest {

    AddressBookController controllerTestBook;
    AddressBook testBook;
    Person controllerTestPerson;


    @BeforeEach
    public void setUp(){
        testBook = new AddressBook();
        controllerTestBook = new AddressBookController(testBook);
        controllerTestPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","(239) 590-1000");
    }

    @AfterEach
    public void tearDown(){
        controllerTestBook = null;
        controllerTestPerson = null;
    }

    @Test
    public void add() {
        controllerTestBook.add(controllerTestPerson);
        Person[] personArray = testBook.getPersons();
        String result = personArray[0].toString();
        Assertions.assertEquals("Doe, John", result);
    }

    @Test
    public void setTest() {
        Person controllerSetPerson = new Person("fName","lName","address","city",
                "state","zip","phone");
        controllerTestBook.add(controllerTestPerson);
        int replaceIndex = testBook.getRowCount() - 1;
        controllerTestBook.set(replaceIndex, controllerSetPerson);
        String result = controllerTestBook.get(replaceIndex).getLastName();
        Assertions.assertEquals("lName", result);
    }

    @Test
    public void removeTest() {
        Person[] personArray;
        boolean personRemoved = false;
        controllerTestBook.add(controllerTestPerson);
        controllerTestBook.remove(0);
        personArray = testBook.getPersons();
        Assertions.assertEquals(0, personArray.length);
    }

    @Test
    public void getTest() {
        Person getPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","(239) 590-1000");
        Boolean gotPerson = false;
        controllerTestBook.add(controllerTestPerson);
        int getIndex = testBook.getRowCount() - 1;
        String expectedFirstName = getPerson.getFirstName();
        Assertions.assertEquals(expectedFirstName, controllerTestBook.get(getIndex).getFirstName());
    }

    @Test
    public void clearTest() {
        controllerTestBook.add(controllerTestPerson);
        int notEmptyBook = testBook.getRowCount(); // row count will be 1 because a person was added.
        controllerTestBook.clear();
        Assertions.assertEquals(notEmptyBook - 1, testBook.getRowCount());
    }

    @Test
    public void saveTest() throws IOException {
        File saveTestFile = new File("src/tests/resources/saveTestFile");
        try{
            controllerTestBook.save(saveTestFile);
        } catch (SQLException e){
            System.err.println("SQL Exception: AddressBookControllerTest.java - line 103");
        }
        Assertions.assertTrue(saveTestFile.exists());
    }

    @Test
    public void openTest() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File testFile = new File(classLoader.getResource("tests/resources/saveTestFile").getFile());
        try {
            controllerTestBook.open(testFile);
        } catch (FileNotFoundException f){
            System.err.println("File Not Found Exception: AddressBookControllerTest.java - line 91");
        } catch (SQLException s){
            System.err.println("SQL Exception: AddressBookControllerTest.java - line 91");
        }
    }

    @Test
    public void getModelTest() {
        controllerTestBook.add(controllerTestPerson);
        controllerTestBook.getModel();
    }
}