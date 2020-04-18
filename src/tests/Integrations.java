package tests;

import AddressBook.AddressBook;
import AddressBook.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class Integrations {

    private AddressBook addressBookMock;
    private Person personMock;
    private Person aPerson;
    private List<Person> testBook;
    private Person[] testPersonArray = new Person[5];

    @BeforeEach
    public void setUp(){
        addressBookMock = Mockito.mock(AddressBook.class);
        personMock = Mockito.mock(Person.class);
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


    // Person Integration Tests

    @Test
    public void getFieldIntegrationTest() {
        Boolean thrown = false;
        String result = "";

        when(personMock.getField(0)).thenReturn("Doe");
        when(personMock.getField(1)).thenReturn("John");
        when(personMock.getField(2)).thenReturn("10501 FGCU Blvd");
        when(personMock.getField(3)).thenReturn("S, Ft. Myers");
        when(personMock.getField(4)).thenReturn("Florida");
        when(personMock.getField(5)).thenReturn("33965");
        when(personMock.getField(6)).thenReturn("(239) 590-1000");
        when(personMock.getField(7)).thenThrow(IndexOutOfBoundsException.class);

        for(int i=0;i<8;i++){
            try {
                result = personMock.getField(i); // int 1 is the case for returning first name.
            } catch (IndexOutOfBoundsException e){
                thrown = true;
            }
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
            } else if (i==6){
                Assertions.assertEquals("(239) 590-1000", result);
            } else {
                Assertions.assertTrue(thrown);
            }
        }
    }

    @Test
    public void containsStringIntegration(){
        for (int i = 0; i < 7; i++){
            switch(i){
                case 0:
                    personMock.containsString("fName");
                    verify(personMock,atLeast(1)).containsString("fName");
                    doThrow(new RuntimeException("ContainsString(fName) operation not implemented")).when(personMock)
                            .containsString("fName");
                    break;
                case 1:
                    personMock.containsString("lName");
                    verify(personMock,atLeast(1)).containsString("lName");
                    doThrow(new RuntimeException("ContainsString(lName) operation not implemented")).when(personMock)
                            .containsString("lName");
                    break;
                case 2:
                    personMock.containsString("address");
                    verify(personMock,atLeast(1)).containsString("address");
                    doThrow(new RuntimeException("ContainsString(address) operation not implemented")).when(personMock)
                            .containsString("address");
                    break;
                case 3:
                    personMock.containsString("city");
                    verify(personMock,atLeast(1)).containsString("city");
                    doThrow(new RuntimeException("ContainsString(city) operation not implemented")).when(personMock)
                            .containsString("city");
                    break;
                case 4:
                    personMock.containsString("state");
                    verify(personMock,atLeast(1)).containsString("state");
                    doThrow(new RuntimeException("ContainsString(state) operation not implemented")).when(personMock)
                            .containsString("state");
                    break;
                case 5:
                    personMock.containsString("zip");
                    verify(personMock,atLeast(1)).containsString("zip");
                    doThrow(new RuntimeException("ContainsString(zip) operation not implemented")).when(personMock)
                            .containsString("zip");
                    break;
                case 6:
                    personMock.containsString("phone");
                    verify(personMock,atLeast(1)).containsString("phone");
                    doThrow(new RuntimeException("ContainsString(phone) operation not implemented")).when(personMock)
                            .containsString("phone");
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    public void getFirstNameIntegration(){
        personMock.getFirstName();
        verify(personMock,atLeast(1)).getFirstName();
        doThrow(new RuntimeException("getFirstName operation not implemented.")).when(personMock).getFirstName();
    }

    @Test
    public void getLastNameIntegration(){
        personMock.getLastName();
        verify(personMock,atLeast(1)).getLastName();
        doThrow(new RuntimeException("getFirstName operation not implemented.")).when(personMock).getLastName();
    }

    @Test
    public void getAddressIntegration(){
        personMock.getAddress();
        verify(personMock,atLeast(1)).getAddress();
        doThrow(new RuntimeException("getFirstName operation not implemented.")).when(personMock).getAddress();
    }

    @Test
    public void getCityIntegration(){
        personMock.getCity();
        verify(personMock,atLeast(1)).getCity();
        doThrow(new RuntimeException("getFirstName operation not implemented.")).when(personMock).getCity();
    }

    @Test
    public void getStateIntegration(){
        personMock.getState();
        verify(personMock,atLeast(1)).getState();
        doThrow(new RuntimeException("getFirstName operation not implemented.")).when(personMock).getState();
    }

    @Test
    public void getZipIntegration(){
        personMock.getZip();
        verify(personMock,atLeast(1)).getZip();
        doThrow(new RuntimeException("getFirstName operation not implemented.")).when(personMock).getZip();
    }

    @Test
    public void getPhoneIntegration(){
        personMock.getPhone();
        verify(personMock,atLeast(1)).getPhone();
        doThrow(new RuntimeException("getFirstName operation not implemented.")).when(personMock).getPhone();
    }


    // AddressBook Controller Integration Tests

    @Test
    public void addIntegrationTest() {
        addressBookMock.add(aPerson);
        verify(addressBookMock, atLeast(1)).add(aPerson);
        doThrow(new RuntimeException("Add Person operation not implemented")).when(addressBookMock).add(aPerson);
    }

    @Test
    public void setIntegrateTest(){
        addressBookMock.set(0, aPerson); //index == 0 because first and only Person object
        verify(addressBookMock, atLeast(1)).set(0, aPerson);
        doThrow(new RuntimeException("Set Person operation not implemented")).when(addressBookMock).set(0, aPerson);
    }

    @Test
    public void removeIntegrationTest(){
        addressBookMock.remove(0);
        verify(addressBookMock, atLeast(1)).remove(0);
        doThrow(new RuntimeException("Remove Person operation not implemented")).when(addressBookMock).remove(0);
    }

}
