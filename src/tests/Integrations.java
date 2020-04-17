package tests;

import AddressBook.AddressBook;
import AddressBook.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class Integrations {

    private AddressBook addressBookMock;
    private Person aPerson;
    private List<Person> testBook;
    private Person[] testPersonArray = new Person[5];

    @BeforeEach
    public void setUp(){
        addressBookMock = Mockito.mock(AddressBook.class);
        aPerson = new Person("fName","lName","address","city",
                "state","zip","phone");
         testBook = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        addressBookMock = null;
        aPerson = null;
        testBook = null;
    }

    @Test
    public void addIntegrationTest() {
        addressBookMock.add(aPerson);
        verify(addressBookMock, atLeast(1)).add(aPerson);
        doThrow(new RuntimeException("Add Person operation not implemented")).when(addressBookMock).add(aPerson);
    }

    @Test
    public void setIntegrateTestS(){
        addressBookMock.set(0, aPerson); //index == 0 because first and only Person object
        verify(addressBookMock, atLeast(1)).set(0, aPerson);
        doThrow(new RuntimeException("Set Person operation not implemented")).when(addressBookMock).set(0, aPerson);
    }

    @Test
    public void getFieldIntegrationTest(){
        Person personMock = Mockito.mock(Person.class);
        when(personMock.getField(0)).thenReturn("Doe");
        when(personMock.getField(1)).thenReturn("John");
        when(personMock.getField(2)).thenReturn("10501 FGCU Blvd");
        when(personMock.getField(3)).thenReturn("S, Ft. Myers");
        when(personMock.getField(4)).thenReturn("Florida");
        when(personMock.getField(5)).thenReturn("33965");
        when(personMock.getField(6)).thenReturn("(239) 590-1000");
        for(int i=0;i<7;i++){
            String result = personMock.getField(i); // int 1 is the case for returning first name.
            if(i==0){
                Assertions.assertEquals("Doe", result);
            } else if (i==1){
                Assertions.assertEquals("John", result);
            } else if (i==2){
                Assertions.assertEquals("10501 FGCU Blvd", result);
            } else if (i==3){
                Assertions.assertEquals("S, Ft. Myers", result);
            } else if (i==4){
                Assertions.assertEquals("Florida", result);
            } else if (i==5){
                Assertions.assertEquals("33965", result);
            } else {
                Assertions.assertEquals("(239) 590-1000", result);
            }
        }
    }

    @Test
    public void removeIntegrationTest(){
        boolean personPresent = true;
        addressBookMock.remove(0);
        verify(addressBookMock, atLeast(1)).remove(0);
        doThrow(new RuntimeException("Remove Person operation not implemented")).when(addressBookMock).remove(0);
    }

}
