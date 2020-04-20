package tests;

import AddressBook.AddressBook;
import AddressBook.AddressBookController;
import AddressBook.FileSystem;
import AddressBook.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Integrations {

    /**
     * AddressBook integration tests
     */

    @Test
    public void getPersonsITest(){
        // Initialize new AddressBook object
        AddressBook getPersonsBook = new AddressBook();

        // Create Person object mock
        Person getPerson = Mockito.mock(Person.class);

        // Add mock to AddressBook
        getPersonsBook.add(getPerson);

        // Call getPerson(), test will succeed if function does not fail
        getPersonsBook.getPersons();
    }

    @Test
    public void addITest() {
        // Create Person object mock
        Person aPerson = Mockito.mock(Person.class);

        // Initialize new AddressBook object, add mock Person to AddressBook
        AddressBook addBook = new AddressBook();
        addBook.add(aPerson);

        // When assertion passes, shows a Person was added to the AddressBook
        Assertions.assertEquals(1, addBook.getRowCount());
    }

    @Test
    public void setITest(){
        // Initialize new AddressBook object
        AddressBook setBook = new AddressBook();

        // Create two mock Person objects
        Person aPerson = Mockito.mock(Person.class);
        Person aNewPerson = Mockito.mock(Person.class);

        // Add initial mock Person to AddressBook
        setBook.add(aPerson);
        // Call set(int, Person) on AddressBook
        setBook.set(0, aNewPerson); //index == 0 because first and only Person object
    }

    @Test
    public void removeITest(){
        // Initialize new AddressBook object
        AddressBook removeBook = new AddressBook();

        // Create Person object mock
        Person aPerson = Mockito.mock(Person.class);

        // Add  mock Person to AddressBook
        removeBook.add(aPerson);

        // Assertion passes if a person was successfully added to AddressBook, rowCount increases
        Assertions.assertEquals(1, removeBook.getRowCount());

        // Calls remove(int) on AddressBook
        removeBook.remove(0);

        // Assertion passes if the row count decreases from initial assertion
        Assertions.assertEquals(0,removeBook.getRowCount());
    }

    @Test
    public void getITest(){
        // Initialize new AddressBook object
        AddressBook getBook = new AddressBook();

        // Create Person object mock
        Person getPerson = Mockito.mock(Person.class);

        // Add mock Person to AddressBook
        getBook.add(getPerson);

        // Call get(int) on AddressBook
        getBook.get(0);
    }

    @Test
    public void clearITest() throws NullPointerException{
        // Create mock AddressBook object
        AddressBook mockbook = Mockito.mock(AddressBook.class);

        // Call clear() on mock AddressBook, attempt to check null condition in AddressBook.clear()
        // Condition does not get hit
        mockbook.clear();

        // Initialize new AddressBook object
        AddressBook clearBook = new AddressBook();

        // Create mock Person object
        Person clearPerson = Mockito.mock(Person.class);

        // Call clear() on empty AddressBook object, covers empty condition
        clearBook.clear();

        // Add mock Person to AddressBook
        clearBook.add(clearPerson);

        // Assert passes if AddressBook is not empty, rows > 0
        Assertions.assertEquals(1,clearBook.getRowCount());

        // Call clear() on non-empty AddressBook
        clearBook.clear();

        // Assert passes if AddressBook is empty after calling clear()
        Assertions.assertEquals(0,clearBook.getRowCount());
    }

    @Test
    public void getRowCountITest(){
        // Initialize new AddressBook object
        AddressBook grcBook = new AddressBook();

        // Add mock Person to AddressBook
        Person grcMockPerson = Mockito.mock(Person.class);

        // Assert passes when returned int from getRowCount() is 0, showing AddressBook is empty
        Assertions.assertEquals(0, grcBook.getRowCount());

        // Add mock Person to Addressbook
        grcBook.add(grcMockPerson);

        // Assert passes when returned int from getRowCount() is 1, showing AddressBook has a new entry
        Assertions.assertEquals(1, grcBook.getRowCount());
    }

    @Test
    public void getColumnCountITest(){
        // Initialize new AddressBook object
        AddressBook gccBook = new AddressBook();

        // When assert passes, shows AddressBook contains a column for each field of Person object
        Assertions.assertEquals(7, gccBook.getColumnCount());
    }

    @Test
    public void getValueAtITest(){
        // Initialize new AddressBook object
        AddressBook gvaBook = new AddressBook();

        // Create mock Person object
        Person gvaPerson = Mockito.mock(Person.class);

        // Add mock Person to AddressBook
        gvaBook.add(gvaPerson);

        // Call getValueAt(int, int) on AddressBook
        gvaBook.getValueAt(0,1);
    }

    @Test
        public void getColumnNameITest(){
        // Initialize new AddressBook object
        AddressBook gcnBook = new AddressBook();

        // Create mock Person object
        Person gcnPerson = Mockito.mock(Person.class);

        // Add mock Person to AddressBook
        gcnBook.add(gcnPerson);

        // Call getColumnName(int) on index referencing field firstName
        // Assert passes if returned string is "First Name"
        Assertions.assertEquals("First Name", gcnBook.getColumnName(1));
    }

    /**
     * AddressBookController tests
     */

    @Test
    public void cAddITest(){
        // Create mock AddressBook object
        AddressBook caBook = Mockito.mock(AddressBook.class);

        // Initialize new AddressBookController object with mock AddressBook
        AddressBookController caController = new AddressBookController(caBook);

        // Create mock Person object
        Person caPerson = Mockito.mock(Person.class);

        // Add caPerson to caController
        caController.add(caPerson);
    }

    @Test
    public void cSetITest(){
        // Create mock AddressBook object
        AddressBook csBook = Mockito.mock(AddressBook.class);

        // Initialize new AddressBookController object with mock AddressBook
        AddressBookController csController = new AddressBookController(csBook);

        // Create 2 mock Person objects
        Person csPerson1 = Mockito.mock(Person.class);
        Person csPerson2 = Mockito.mock(Person.class);

        // Add Person to AddressBookController
        // Call set(int, Person) on AddressBookController
        csController.add(csPerson1);
        csController.set(0,csPerson2);
    }

    @Test
    public void cRemoveITest(){
        AddressBook crBook = Mockito.mock(AddressBook.class);

        // Initialize new AddressBookController object with mock AddressBook
        AddressBookController crController = new AddressBookController(crBook);

        // Create mock Person object
        Person crPerson1 = Mockito.mock(Person.class);

        // Add mock Person to AddressBookController
        // Call remove(int) on AddressBookController
        crController.add(crPerson1);
        crController.remove(0);
    }

    @Test
    public void cGetITest(){
        // Create mock AddressBook object
        AddressBook cgBook = Mockito.mock(AddressBook.class);

        // Initialize new AddressBookController object with mock AddressBook
        AddressBookController cgController = new AddressBookController(cgBook);

        // Call get(int) on AddressBookController
        cgController.get(0);
    }

    @Test
    public void cClearITest(){
        // Create mock AddressBook object
        AddressBook ccBook = Mockito.mock(AddressBook.class);

        // Initialize new AddressBookController object with mock AddressBook
        AddressBookController ccController = new AddressBookController(ccBook);

        // Create 2 mock Person objects
        Person ccPerson1 = Mockito.mock(Person.class);
        Person ccPerson2 = Mockito.mock(Person.class);

        // Add two mock Person objects to AddressBookController
        ccController.add(ccPerson1);
        ccController.add(ccPerson2);

        // Call clear() on AddressBookController
        // Assert passes if rows in AddressBookController's AddressBook is 0, AddressBook is empty
        ccController.clear();
        Assertions.assertEquals(0, ccController.getModel().getRowCount());
    }

    @Test
    public void cOpenITest() {
        // Initialize new AddressBook object
        AddressBook coBook = Mockito.mock(AddressBook.class);

        // Java.io.File mock
        File coFile = Mockito.mock(File.class);

        // Initialize new AddressBookController object with mock AddressBook
        AddressBookController coController = new AddressBookController(coBook);

        // Assert passes
        Assertions.assertThrows(FileNotFoundException.class,()->{
            coController.open(coFile);
        });
    }

    @Test
    public void cSaveITest(){
        // Initialize new AddressBook object
        AddressBook csaBook = new AddressBook();

        // Java.io.File csaFile - new File object with valid path
        File csaFile = new File("src/tests/resources/rfFile");

        // Initialize new AddressBookController object with AddressBook csaBook
        AddressBookController csaController = new AddressBookController(csaBook);

        // Test attempts to call AddressBookController.save(File), handles exceptions with stack trace
        try {
            csaController.save(csaFile);
        } catch (SQLException e){
            e.printStackTrace();
        }

        // Assert passes if it finds the File object exists
        Assertions.assertTrue(csaFile.exists());
    }

    @Test
    public void getModelITest(){
        // Create mock AddressBook object
        AddressBook mBook = Mockito.mock(AddressBook.class);

        // Initialize new AddressBookController object with mock AddressBook
        AddressBookController mController = new AddressBookController(mBook);

        // Call getModel() on AddressBookController
        mController.getModel();
    }

    /**
     * FileSystem tests
     */

    @Test
    public void readFileITest() throws IOException, SQLException {
        // Initialize new AddressBook object
        AddressBook rfBook = new AddressBook();

        // Initialize new valid Person object
        Person rfPerson = new Person("fName","lName","address","city",
                "state","00000","0001112222");

        // Add Person to AddressBook
        rfBook.add(rfPerson);

        // Java.io.File rfFile - new File object with valid path
        File rfFile = new File("src/tests/resources/rfFile");

        // new FileSystem object calls .saveFile(AddressBook, File), cannot read a file that doesn't exist
        // Assert passes if file is found
        new FileSystem().saveFile(rfBook,rfFile);
        Assertions.assertTrue(rfFile.exists());

        // new FileSystem object calls readFile(AddressBook, File)
        // Assert passes when the file is found and has open read permissions
        new FileSystem().readFile(rfBook,rfFile);
        Assertions.assertTrue(rfFile.canRead());
    }

    @Test
    public void saveFileITest() throws SQLException{
        // Initialize new AddressBook object
        AddressBook sfBook = new AddressBook();

        // Create mock Person object
        Person sfPerson = Mockito.mock(Person.class);

        // Add mock Person to AddressBook
        sfBook.add(sfPerson);

        // Java.io.File sfFile - new File object with valid path
        // Call saveFile(AddressBook, File)
        File sfFile = new File("src/tests/resources/sfFile");
        new FileSystem().saveFile(sfBook,sfFile);

        // Assert passes if file is found after saving
        Assertions.assertTrue(sfFile.exists());
    }
}
