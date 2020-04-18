package tests.unitTests;

import AddressBook.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import static org.mockito.Mockito.when;

public class PersonTest {

    Person testPerson;

    @BeforeEach
    public void setUp(){
        testPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","(239) 590-1000");
    }

    @AfterEach
    public void tearDown(){
        testPerson = null;
    }

    @Test
    public void buildPerson(){
        Boolean firstNameNull = false;
        Boolean firstNameEmpty = false;
        Boolean lastNameNull = false;
        Boolean lastNameEmpty = false;
        try {
            Person buildPerson1 = new Person(null,"lname",null,null,null,
                    null,null);
        } catch (IllegalArgumentException e){
            firstNameNull = true;
        }
        try {
            Person buildPerson2 = new Person("","lname",null,null,null,
                    null,null);
        }catch (IllegalArgumentException e){
            firstNameEmpty = true;
        }
        try {
            Person buildPerson3 = new Person("fname",null,null,null,null,
                    null,null);
        } catch (IllegalArgumentException e){
            lastNameNull = true;
        }
        try {
            Person buildPerson4 = new Person("fName","",null,null,null,
                    null,null);
        } catch (IllegalArgumentException e){
            lastNameEmpty = true;
        }
        Assertions.assertTrue(firstNameNull);
        Assertions.assertTrue(firstNameEmpty);
        Assertions.assertTrue(lastNameNull);
        Assertions.assertTrue(lastNameEmpty);
    }

    @Test
    public void getFirstName() {
        String result = testPerson.getFirstName();
        Assert.assertEquals(result,"John");
    }

    @Test
    public void getLastName() {
        String result = testPerson.getLastName();
        Assert.assertEquals(result,"Doe");
    }

    @Test
    public void getAddress() {
        String result = testPerson.getAddress();
        Assert.assertEquals(result,"10501 FGCU Blvd");
    }

    @Test
    public void getCity() {
        String result = testPerson.getCity();
        Assert.assertEquals(result,"S, Ft. Myers");
    }

    @Test
    public void getState() {
        String result = testPerson.getState();
        Assert.assertEquals(result,"Florida");
    }

    @Test
    public void getZip() {
        String result = testPerson.getZip();
        Assert.assertEquals(result,"33965");
    }

    @Test
    public void getPhone() {
        String result = testPerson.getPhone();
        Assert.assertEquals(result,"(239) 590-1000");
    }

    @Test
    public void containsString(){
        Assertions.assertTrue(testPerson.containsString("John"));
        Assertions.assertTrue(testPerson.containsString("Doe"));
        Assertions.assertTrue(testPerson.containsString("10501 FGCU Blvd"));
        Assertions.assertTrue(testPerson.containsString("S, Ft. Myers"));
        Assertions.assertTrue(testPerson.containsString("Florida"));
        Assertions.assertTrue(testPerson.containsString("33965"));
        Assertions.assertTrue(testPerson.containsString("(239) 590-1000"));
        Assertions.assertFalse(testPerson.containsString("Empty"));
    }

    @Test
    public void getField() {
        String result = "";
        Boolean thrown = false;
        for(int i=0;i<8;i++){
            try {
                result = testPerson.getField(i); // int 1 is the case for returning first name.
            } catch (IllegalArgumentException e){
                thrown = true;
            }
            if(i==0){
                Assert.assertEquals("Doe", result);
            } else if (i==1){
                Assert.assertEquals("John", result);
            } else if (i==2){
                Assert.assertEquals("10501 FGCU Blvd", result);
            } else if (i==3){
                Assert.assertEquals("S, Ft. Myers", result);
            } else if (i==4){
                Assert.assertEquals("Florida", result);
            } else if (i==5){
                Assert.assertEquals("33965", result);
            } else if (i==6){
                Assert.assertEquals("(239) 590-1000", result);
            } else {
                Assertions.assertTrue(thrown);
            }
        }
    }
}