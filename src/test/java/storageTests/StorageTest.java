package storageTests;

import lukuvinkkikirjasto.main.Tip;
import lukuvinkkikirjasto.storage.Storage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class StorageTest {


    private Storage testStora;
    static Tip tipNotUniqueHeader1;
    static Tip tipNotUniqueHeader2;
    static Tip tipUniqueHeader;

    @BeforeClass
    public static void initializeTips() {
        tipNotUniqueHeader1 = new Tip("Help", "Us", "Hesus",
                "heaven", "Book", "satanic", "meh", "muumi");
        tipNotUniqueHeader2 = new Tip("Help", "test", "Jesus",
                "https", "Blog", "", "joo", "muumuu");
        tipUniqueHeader = new Tip("Best", "Us", "Hesus",
                "heaven", "Book", "satanic", "meh", "muumi");

    }
    @Before
    public void Initialize() {
        testStora = new Storage(true);
        testStora.deleteTestDatabase();
        testStora.initializeTestDatabase();

    }

    @Test
    public void deleteTestDatabaseRemovesTestDatabase() {
        File testDatabaseFile = new File("test.db");
        if (testDatabaseFile.exists()) {
            testStora.initializeTestDatabase();
            testDatabaseFile = new File("test.db");
        }
        assertTrue(testDatabaseFile.exists());
        testStora.deleteTestDatabase();
        testDatabaseFile = new File("test.db");
        assertFalse(testDatabaseFile.exists());

        testStora.initializeTestDatabase();
        testDatabaseFile = new File("test.db");
        assertTrue(testDatabaseFile.exists());
    }

    @Test
    public void addOneToStorage() {

        testStora.addToStorage(tipNotUniqueHeader1);

        assertEquals(1, testStora.getStorage().size());
    }


    @Test
    public void addTwoTipsWithUniqueHeader() {

        testStora.addToStorage(tipNotUniqueHeader1);
        testStora.addToStorage(tipUniqueHeader);
        System.out.println(testStora.getStorage());
        assertEquals(2, testStora.getStorage().size());
    }


    @Test
    public void cannotAddTwoTipsWithSameHeader() {

        testStora.addToStorage(tipNotUniqueHeader2);
        testStora.addToStorage(tipNotUniqueHeader1);

        assertEquals(1, testStora.getStorage().size());
    }


}
