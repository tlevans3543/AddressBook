package tests.unitTests;

import AddressBook.AddressBook;
import AddressBook.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddressBookTest {

    private List<Person> testBook;
    Person testPerson;

    @BeforeEach
    public void setUp(){
        testPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","(239) 590-1000");
        testBook = new ArrayList<>();
    }

    @AfterEach
    public void tearDown(){
        testPerson = null;
        testBook = null;
    }

    @Test
    public void getPersons() {
        AddressBook personsBook = new AddressBook();
        Boolean personsFound = false;
        Person[] personsTest;
        personsBook.add(testPerson);
        personsTest = personsBook.getPersons();
        if (personsTest.length > 0){
            personsFound = true;
        }
        Assertions.assertTrue(personsFound);
    }

    @Test
    public void add() {
        testBook.add(testPerson);
        String result = testBook.get(0).toString();
        Assertions.assertEquals(result,"Doe, John");
    }

    @Test
    public void set() {
        Person testSetPerson = new Person("Jane", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","(239) 590-1000");
        testBook.add(testPerson);
        int testIndex = testBook.size();
        testBook.set(testIndex-1, testSetPerson);
        Person setPerson = testBook.get(testIndex-1);
        String testFName = setPerson.getFirstName();
        Assertions.assertEquals(testFName,"Jane");
    }

    @Test
    public void remove() {
        Person[] personArray;
        boolean personRemoved = false;
        AddressBook removeTestBook = new AddressBook();
        removeTestBook.add(testPerson);
        removeTestBook.remove(0);
        personArray = removeTestBook.getPersons();
        if(personArray.length == 0){
            personRemoved = true;
        }
        Assertions.assertTrue(personRemoved);
    }

    @Test
    public void get() {
        testBook.add(testPerson);
        String result = testBook.get(0).getLastName();
        Assertions.assertEquals("Doe", result);
    }

    @Test
    public void clear() {
        AddressBook clearTestBook = new AddressBook();
        boolean bookCleared = false;
        clearTestBook.add(testPerson);
        clearTestBook.add(testPerson);
        clearTestBook.clear();
        if (clearTestBook.getRowCount() == 0){
            bookCleared = true;
        }
        Assertions.assertEquals(true, bookCleared);
    }

    @Test
    public void getColumnCount() {
        int result = Person.fields.length;
        Assertions.assertEquals(7, result);
    }
}