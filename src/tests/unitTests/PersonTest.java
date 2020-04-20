package tests.unitTests;

import AddressBook.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class PersonTest {

    Person testPerson;

    @BeforeEach
    public void setUp(){
        // Valid Person object
        testPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","2395901000");
    }

    @AfterEach
    public void tearDown(){
        testPerson = null;
    }

    // Exception test
    // Building a Person Object
    @Test
    public void buildPerson(){
        // String firstName cannot be null
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            Person nullFName = new Person(null,"lname",null,null,null,
                    null,null);
        });

        // String firstName cannot be empty
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            Person emptyFName = new Person("","lname",null,null,null,
                    null,null);
        });

        // String lastName cannot be null
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            Person nullLName = new Person("fname",null,null,null,null,
                    null,null);
        });

        // String lastName cannot be empty
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            Person emptyLName = new Person("fName","",null,null,null,
                    null,null);
        });

        // String zip cannot contain letters, only numbers
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            Person letterZip = new Person("fName","lName",null,null,null,
                    "a0000",null);
        });

        // String phone cannot contain letters, only numbers
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            Person letterPhone = new Person("fName","lName",null,null,null,
                    "0","a001112222");
        });
    }

    @Test
    public void getFirstName() {
        // String result calls getFirstName() on the Person object created in @BeforeEach method
        String result = testPerson.getFirstName();
        // Checks that the Person object contains the string "John" in the field firstName
        Assert.assertEquals(result,"John");
    }

    @Test
    public void getLastName() {
        // String result calls getLastName() on Person object created in @BeforeEach method
        String result = testPerson.getLastName();
        // Checks that the Person object contains the string "Doe" in the field lastName
        Assert.assertEquals(result,"Doe");
    }

    @Test
    public void getAddress() {
        // String result calls getAddress() on Person object created in @BeforeEach method
        String result = testPerson.getAddress();
        // Checks that the Person object contains the string "10501 FGCU Blvd" in the field address
        Assert.assertEquals(result,"10501 FGCU Blvd");
    }

    @Test
    public void getCity() {
        // String result calls getCity() on Person object created in @BeforeEach method
        String result = testPerson.getCity();
        // Checks that the Person object contains the string "S, Ft. Myers" in the field city
        Assert.assertEquals(result,"S, Ft. Myers");
    }

    @Test
    public void getState() {
        // String result calls getState() on Person object created in @BeforeEach method
        String result = testPerson.getState();
        // Checks that the Person object contains the string "Florida" in the field state
        Assert.assertEquals(result,"Florida");
    }

    @Test
    public void getZip() {
        // String result calls getZip() on Person object created in @BeforeEach method
        String result = testPerson.getZip();
        // Checks that the Person object contains the string "33965" in the field zip
        Assert.assertEquals(result,"33965");
    }

    @Test
    public void getPhone() {
        // String result calls getPhone() on Person object created in @BeforeEach method
        String result = testPerson.getPhone();
        // Checks that the Person boject contains the string "2395901000" in the field phone
        Assert.assertEquals(result,"2395901000");
    }

    @Test
    public void containsString(){
        // Person testPerson instantiated in @BeforeEach method
        // Returns true if after iterating through testPerson's fields it finds the string "John"
        Assertions.assertTrue(testPerson.containsString("John"));

        // Returns true if after iterating through testPerson's fields it finds the string "Doe"
        Assertions.assertTrue(testPerson.containsString("Doe"));

        // Returns true if after iterating through testPerson's fields it finds the string "10501 FGCU Blvd"
        Assertions.assertTrue(testPerson.containsString("10501 FGCU Blvd"));

        // Returns true if after iterating through testPerson's fields it finds the string "S, Ft. Myers"
        Assertions.assertTrue(testPerson.containsString("S, Ft. Myers"));

        // Returns true if after iterating through testPerson's fields it finds the string "Florida"
        Assertions.assertTrue(testPerson.containsString("Florida"));

        // Returns true if after iterating through testPerson's fields it finds the string "33965"
        Assertions.assertTrue(testPerson.containsString("33965"));

        // Returns true if after iterating through testPerson's fields it finds the string "2395901000"
        Assertions.assertTrue(testPerson.containsString("2395901000"));

        // Returns false if after iterating through testPerson's fields it cannot find the string "Empty"
        // This unfound string is a 'hidden' branch when looking at the code for containsString()
        Assertions.assertFalse(testPerson.containsString("Empty"));
    }

    @Test
    public void getField() {
        // Test Person instantiated in @BeforeEach method

        // String result - holds the returned string from Person.getField(int)
        String result = "";

        // Boolean thrown - initialized as false, will turn true if the IllegalArgumentException is thrown
        Boolean thrown = false;

        // Loop to iterate through each field as well as the boundary outside of the fields.
        // Starts at -1 to hopefully throw an exception that the index is out of range
        for(int i = -1; i<8; i++){
            // Attempts to call the Person.getField(int) function
            // Will throw an IllegalArgumentException if the int is out of range
            try {
                result = testPerson.getField(i); // int 1 is the case for returning first name.
            } catch (IllegalArgumentException e){
                thrown = true;
            }
            // Int 0: index corresponds to field lastName
            if(i==0){
                Assert.assertEquals("Doe", result);
            }
            // Int 1: index corresponds to field firstName
            else if (i==1){
                Assert.assertEquals("John", result);
            }
            // Int 2: index corresponds to field address
            else if (i==2){
                Assert.assertEquals("10501 FGCU Blvd", result);
            }
            // Int 3: index corresponds to field city
            else if (i==3){
                Assert.assertEquals("S, Ft. Myers", result);
            }
            // Int 4: index corresponds to field state
            else if (i==4){
                Assert.assertEquals("Florida", result);
            }
            // Int 5: index corresponds to field zip
            else if (i==5){
                Assert.assertEquals("33965", result);
            }
            // Int 6: index corresponds to field phone
            else if (i==6){
                Assert.assertEquals("2395901000", result);
            }
            // Checks that the boolean is true, if true, an IllegalArgumentException was thrown
            else {
                Assertions.assertTrue(thrown);
                System.out.println("PersonTest.Java - getField() - Exception Thrown");
            }
        }
    }
}