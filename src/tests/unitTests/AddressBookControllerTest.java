package tests.unitTests;

import AddressBook.AddressBook;
import AddressBook.AddressBookController;
import AddressBook.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class AddressBookControllerTest {

    AddressBookController controllerTestBook;
    AddressBook testBook;
    Person controllerTestPerson;


    @BeforeEach
    public void setUp(){
        testBook = new AddressBook();
        controllerTestBook = new AddressBookController(testBook);
        controllerTestPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","2395901000");
    }

    @AfterEach
    public void tearDown(){
        controllerTestBook = null;
        controllerTestPerson = null;
    }

    @Test
    public void add() {
        // AddressBookController controllerTestBook, AddressBook testBook, Person controllerTestPerson
        // Initialized in @BeforeEach method

        // Add controllerTestPerson to controllerTestBook(testBook)
        controllerTestBook.add(controllerTestPerson);

        // Person array holds the Person in testBook
        Person[] personArray = testBook.getPersons();

        // Person.toString() stored in String result
        // Test passes if the stored Person's lastName field is "Doe" and firstName field is "John"
        String result = personArray[0].toString();
        Assertions.assertEquals("Doe, John", result);
    }

    @Test
    public void setTest() {
        // AddressBookController controllerTestBook, AddressBook testBook, Person controllerTestPerson
        // Initialized in @BeforeEach method

        // Person controllerSetPerson - new Person object to replace controllerTestPerson in the
        // AddressBook Controller
        Person controllerSetPerson = new Person("fName","lName","address","city",
                "state","00000","0001112222");

        // Add controllerTestPerson to controllerTestBook
        controllerTestBook.add(controllerTestPerson);

        // int replaceIndex - gets the index of controllerTestPerson, used to replace controllerTestPerson with
        // controllerSetPerson
        int replaceIndex = testBook.getRowCount() - 1;
        controllerTestBook.set(replaceIndex, controllerSetPerson);

        // String result - stores return from AddressBookController.get(index).getLastName()
        // Assertion passes if lastName field in Person object is the new Person object's lastName field
        String result = controllerTestBook.get(replaceIndex).getLastName();
        Assertions.assertEquals("lName", result);
    }

    @Test
    public void removeTest() {
        // AddressBookController controllerTestBook, AddressBook testBook, Person controllerTestPerson
        // Initialized in @BeforeEach method

        // Person array holds Person object from testBook
        Person[] personArray;

        // Add controllerTestPerson to controllerTestBook
        controllerTestBook.add(controllerTestPerson);

        // Assertion passes if row count is greater than 0, shows a Person object was successfully added
        Assertions.assertTrue(controllerTestBook.getModel().getRowCount() > 0);

        // Calls AddressBookController.remove(int) at the index of the added Person
        controllerTestBook.remove(0);

        // personArray stores Person object from testbook
        // Test passes if Assertion passes that the personArray is empty meaning the AddressBook is empty
        personArray = testBook.getPersons();
        Assertions.assertEquals(0, personArray.length);
    }

    @Test
    public void getTest() {
        // AddressBookController controllerTestBook, AddressBook testBook, Person controllerTestPerson
        // Initialized in @BeforeEach method

        // Add controllerTestPerson to controllerTestBook(testBook)
        // Assertion checks that the # of rows in the addressBook is greater than 0, meaning Person added
        controllerTestBook.add(controllerTestPerson);
        Assertions.assertTrue(controllerTestBook.getModel().getRowCount() > 0);

        // int getIndex - stores index of newly added Person
        // Test passes after assertion checks that the field firstName of the Person at index 0
        // matches with the Person that was added to the book
        int getIndex = testBook.getRowCount() - 1;
        Assertions.assertEquals("John", controllerTestBook.get(getIndex).getFirstName());
    }

    @Test
    public void clearTest() {
        // AddressBookController controllerTestBook, AddressBook testBook, Person controllerTestPerson
        // Initialized in @BeforeEach method

        // Add controllerTestPerson to controllerTestBook(testBook)
        controllerTestBook.add(controllerTestPerson);

        // int notEmptyBook - stores the # of rows in testBook
        // When assertion passes, the AddressBook is not empty, showing a Person was added
        int notEmptyBook = testBook.getRowCount(); // row count will be 1 because one person was added.
        Assertions.assertTrue(notEmptyBook > 0);

        // controllerTestBook calls clear()
        // When assertion passes, shows that the AddressBook is now empty after calling clear()
        controllerTestBook.clear();
        Assertions.assertEquals(0, testBook.getRowCount());
    }

    @Test
    public void saveTest() {
        // AddressBookController controllerTestBook, AddressBook testBook
        // Initialized in @BeforeEach method

        // Java.io.File saveTestFile - new File object with a valid path in the project packages
        File saveTestFile = new File("src/tests/resources/saveTestFile");

        // When assertion passes, the file was successfully saved without an SQL exception being thrown
        Assertions.assertDoesNotThrow(()->{
           controllerTestBook.save(saveTestFile);
        });

        // Final assertion passes after verifying that the file was successfully saved.
        Assertions.assertTrue(saveTestFile.exists());
    }

    @Test
    public void openTest() {
        // AddressBookController controllerTestBook, AddressBook testBook
        // Initialized in @BeforeEach method

        // ClassLoader object - used to retrieve a Java.io.File object with a valid path
        ClassLoader classLoader = this.getClass().getClassLoader();

        // Java.io.File testFile - new File object with valid path
        File testFile = new File(classLoader.getResource("tests/resources/saveTestFile").getFile());

        // When assertion passes, shows that file exists due to FileNotFoundException NOT being thrown
        Assertions.assertDoesNotThrow(()->{
            controllerTestBook.open(testFile);
        });

        // File noSuchFileExists - new File object with invalid path
        File noSuchFileExists = new File("false path");

        // When assertion passes, shows that file does not exist and function will throw FileNotFoundException
        Assertions.assertThrows(FileNotFoundException.class,()->{
            controllerTestBook.open(noSuchFileExists);
        });
    }

    @Test
    public void getModelTest() {
        // AddressBookController controllerTestBook, AddressBook testBook, Person controllerTestPerson
        // Initialized in @BeforeEach method

        // Add controllerTestPerson to controllerTestBook
        controllerTestBook.add(controllerTestPerson);

        // AddressBook cModelBook - stores AddressBook object returned by getModel()
        AddressBook cModelBook = controllerTestBook.getModel();

        // When assertion passes, shows getModel() successfully returned a valid AddressBook object that is not null
        Assertions.assertNotNull(cModelBook);
    }
}