package tests.unitTests;

import AddressBook.AddressBook;
import AddressBook.Person;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddressBookTest {

    AddressBook testBook;
    Person testPerson;

    @BeforeEach
    public void setUp(){
        testPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","(239) 590-1000");
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
        Boolean personsFound = false;
        Person[] personsTest;
        personsBook.add(testPerson);
        personsTest = personsBook.getPersons();
        Assertions.assertEquals(1, personsTest.length);
    }

    @Test
    public void addTest() {
        testBook.add(testPerson);
        String result = testBook.get(0).toString();
        Assertions.assertEquals(result,"Doe, John");
    }

    @Test
    public void setTest() {
        Person testSetPerson = new Person("Jane", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","(239) 590-1000");
        testBook.add(testPerson);
        int testIndex = testBook.getRowCount();
        testBook.set(testIndex-1, testSetPerson);
        Person setPerson = testBook.get(testIndex-1);
        String testFName = setPerson.getFirstName();
        Assertions.assertEquals(testFName,"Jane");
    }

    @Test
    public void removeTest() {
        Person[] personArray;
        AddressBook removeTestBook = new AddressBook();
        removeTestBook.add(testPerson);
        removeTestBook.remove(0);
        personArray = removeTestBook.getPersons();
        Assertions.assertEquals(0, personArray.length);
    }

    @Test
    public void getTest() {
        testBook.add(testPerson);
        String result = testBook.get(0).getLastName();
        Assertions.assertEquals("Doe", result);
    }

    @Test
    public void clearTest() throws NullPointerException{
        testBook.clear();
        testBook.add(testPerson);
        testBook.clear();
    }

    @Test
    public void getColumnCountTest() {
        Assertions.assertEquals(7, testBook.getColumnCount());
    }

    @Test
    public void getValueAtTest(){
        testBook.add(testPerson);
        Assertions.assertEquals("John",testBook.getValueAt(0,1));
    }

    @Test
    public void getColumnNameTest(){
        int fName = 1;
        Assertions.assertEquals("First Name", testBook.getColumnName(fName));
    }
}