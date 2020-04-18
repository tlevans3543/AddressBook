package tests.unitTests;

import AddressBook.AddressBook;
import AddressBook.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddressBookControllerTest {

    AddressBook controllerTestBook;
    Person controllerTestPerson;


    @BeforeEach
    public void setUp(){
        controllerTestBook = new AddressBook();
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
        Person[] personArray = controllerTestBook.getPersons();
        String result = personArray[0].toString();
        Assertions.assertEquals("Doe, John", result);
    }

    @Test
    public void set() {
        Person controllerSetPerson = new Person("fName","lName","address","city",
                "state","zip","phone");
        controllerTestBook.add(controllerTestPerson);
        int replaceIndex = controllerTestBook.getRowCount() - 1;
        controllerTestBook.set(replaceIndex, controllerSetPerson);
        String result = controllerTestBook.get(replaceIndex).getLastName();
        Assertions.assertEquals("lName", result);
    }

    @Test
    public void remove() {
        Person[] personArray;
        boolean personRemoved = false;
        controllerTestBook.add(controllerTestPerson);
        controllerTestBook.remove(0);
        personArray = controllerTestBook.getPersons();
        Assertions.assertEquals(0, personArray.length);
    }

    @Test
    public void get() {
        Person getPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","(239) 590-1000");
        Boolean gotPerson = false;
        controllerTestBook.add(controllerTestPerson);
        int getIndex = controllerTestBook.getRowCount() - 1;
        String expectedFirstName = getPerson.getFirstName();
        Assertions.assertEquals(expectedFirstName, controllerTestBook.get(getIndex).getFirstName());
    }

    @Test
    public void clear() {
        controllerTestBook.add(controllerTestPerson);
        int notEmptyBook = controllerTestBook.getRowCount(); // row count will be 1 because a person was added.
        controllerTestBook.clear();
        Assertions.assertEquals(notEmptyBook - 1, controllerTestBook.getRowCount());
    }

    @Test
    public void open() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File testFile = new File(classLoader.getResource("tests/resources/loremipsum.txt").getFile());
        Assertions.assertTrue(testFile.exists());
    }

    @Test
    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter("saveTestFile.txt");
        fileWriter.write("text");
        File savedFile = new File("saveTestFile.txt");
        Assertions.assertTrue(savedFile.canWrite());
    }

    @Test
    public void getModel() {
        AddressBook modelbook = new AddressBook();
        modelbook.add(controllerTestPerson);
        controllerTestBook.add(controllerTestPerson);
        Assertions.assertEquals(modelbook.get(0).toString(), controllerTestBook.get(0).toString());
    }
}