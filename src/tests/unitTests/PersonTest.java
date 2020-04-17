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
        testPerson = new Person("John", "Doe", "10501 FGCU Blvd",
                "S, Ft. Myers", "Florida","33965","(239) 590-1000");
    }

    @AfterEach
    public void tearDown(){
        testPerson = null;
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
    }

    @Test
    public void getField() {
        for(int i=0;i<7;i++){
            String result = testPerson.getField(i); // int 1 is the case for returning first name.
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
            } else {
                Assert.assertEquals("(239) 590-1000", result);
            }
        }
    }
}