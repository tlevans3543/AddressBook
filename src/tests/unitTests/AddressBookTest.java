package tests.unitTests;

import AddressBook.AddressBook;
import AddressBook.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressBookTest {

    // Initialized in @BeforeEach method
    AddressBook testBook;
    Person testPerson;

    @BeforeEach
    public void setUp(){
        // Valid Person object
        testPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","2395901000");
        testBook = new AddressBook();
    }

    @AfterEach
    public void tearDown(){
        testPerson = null;
        testBook = null;
    }

    @Test
    public void getPersonsTest() {
        AddressBook personsBook = new AddressBook();
        // Person array, used to hold the result from AddressBook.getPersons()
        Person[] personsTest;

        // Person testPerson is added to book so getPersons() has something to return
        personsBook.add(testPerson);
        personsTest = personsBook.getPersons();

        // getPersons() is expected to return one Person object from the AddressBook to the Person array
        // Test passes if equal
        Assertions.assertEquals(1, personsTest.length);
    }

    @Test
    public void addTest() {
        // AddressBook testBook, Person testPerson initialized in @BeforeEach method
        testBook.add(testPerson);

        // String result uses AddressBook.toString() to get the first and last name of the Person object
        String result = testBook.get(0).toString();

        // Test passes if result is what we expect from the Person object, "Doe, John"
        Assertions.assertEquals("Doe, John", result);
    }

    @Test
    public void setTest() {
        // AddressBook testBook, Person testPerson initialized in @BeforeEach method
        // Person testSetPerson used to replace testPerson in AddressBook testBook, firstName field is changed
        Person testSetPerson = new Person("Jane", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","2395901000");

        // Add initial person to be changed, firstName field contains "John"
        testBook.add(testPerson);

        // Get # of rows, subtract 1 to get index of Person to change, replace with testSetPerson
        int testIndex = testBook.getRowCount();
        testBook.set(testIndex-1, testSetPerson);

        // New Person object setPerson, holds information of the Person object currently in testBook
        Person setPerson = testBook.get(testIndex-1);

        // Return and store field firstName
        // Test passes if stored firstName is testSetPerson's firstName above
        String testFName = setPerson.getFirstName();
        Assertions.assertEquals("Jane", testFName);
    }

    @Test
    public void removeTest() {
        // Person testPerson initialized in @BeforeEach method
        // Person array, used to check if AddressBook is empty after remove()
        Person[] personArray;
        AddressBook removeTestBook = new AddressBook();

        // Add person to AddressBook removeTestBook
        removeTestBook.add(testPerson);

        // When assertion passes, proves the Person was added
        Assertions.assertEquals(1, removeTestBook.getRowCount());

        // Call AddressBook.remove() on index of only Person
        removeTestBook.remove(0);

        // Store Person object in array if exists
        // Assertion passes if array is empty showing successful remove
        personArray = removeTestBook.getPersons();
        Assertions.assertEquals(0, personArray.length);
    }

    @Test
    public void getTest() {
        // AddressBook testBook, Person testPerson initialized in @BeforeEach method
        // Add testPerson to testBook
        testBook.add(testPerson);

        // String result - stores lastName field of only Person in testBook
        // Assertion passes if field lastName is that of the Person stored in testBook
        String result = testBook.get(0).getLastName();
        Assertions.assertEquals("Doe", result);
    }

    @Test
    public void clearTest() throws NullPointerException{
        // AddressBook testBook initialized in @BeforeEach method
        // Initial clear() tests function when AddressBook is empty
        testBook.clear();

        // Add testPerson twice to testBook
        // When assertion passes, shows testPerson was added to testBook
        testBook.add(testPerson);
        testBook.add(testPerson);
        Assertions.assertTrue(testBook.getRowCount() > 0);

        // Call clear() again
        // Assertion passes if testBook returns 0 rows showing it's empty
        testBook.clear();
        Assertions.assertEquals(0, testBook.getRowCount());
    }

    @Test
    public void getColumnCountTest() {
        // Assertion passes if AddressBook has 7 columns, 1 for each field of a Person object
        Assertions.assertEquals(7, testBook.getColumnCount());
    }

    @Test
    public void getValueAtTest(){
        // AddressBook testBook, Person testPerson initialized in @BeforeEach method
        // Add testPerson to testBook
        testBook.add(testPerson);

        // Assertion passes if the field at AddressBook location 0,1 is "John", firstName field of testPerson
        Assertions.assertEquals("John",testBook.getValueAt(0,1));
    }

    @Test
    public void getColumnNameTest(){
        // AddressBook testBook, Person testPerson initialized in @BeforeEach method
        // int fName - set to 1, corresponds to AddressBook column that holds a Person's firstName
        // field in AddressBook object
        int fName = 1;

        // Assertion passes if column name is "First Name" as expected
        Assertions.assertEquals("First Name", testBook.getColumnName(fName));
    }
}