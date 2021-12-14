package storagetests;

import lukuvinkkikirjasto.main.Tip;
import lukuvinkkikirjasto.storage.Storage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StorageTest {


    private Storage testStora;
    static Tip tipNotUniqueHeader1;
    static Tip tipNotUniqueHeader2;
    static Tip tipUniqueHeader;
    static Tip tipUnique;

    @BeforeClass
    public static void initializeTips() {
        tipNotUniqueHeader1 = new Tip("Help", "Us", "Hesus",
                "heaven", "Book", "satanic", "meh", "muumi");
        tipNotUniqueHeader2 = new Tip("Help", "test", "Jesus",
                "https", "Blog", "", "joo", "muumuu");
        tipUniqueHeader = new Tip("Best", "Us", "Hesus",
                "heaven", "Book", "satanic", "meh", "muumi");
        tipUnique = new Tip("Test", "Test", "Test",
                "Test", "Podcast", "Test", "Test", "Test");


    }

    @Before
    public void initialize() {
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
    public void databaseReturnsCorrectTip() {
        testStora.addToStorage(tipUniqueHeader);
        ArrayList<Tip> tips = testStora.getStorage();
        assertTrue(tips.contains(tipUniqueHeader));
    }

    @Test
    public void databaseReturnsAllAddedTips() {
        testStora.addToStorage(tipUniqueHeader);
        testStora.addToStorage(tipNotUniqueHeader1);
        testStora.addToStorage(tipNotUniqueHeader2);

        ArrayList<Tip> tips = testStora.getStorage();
        assertTrue(tips.contains(tipUniqueHeader));
        assertTrue(tips.contains(tipNotUniqueHeader1));
        assertFalse(tips.contains(tipNotUniqueHeader2));
    }

    @Test
    public void addTwoTipsWithUniqueHeader() {

        testStora.addToStorage(tipNotUniqueHeader1);
        testStora.addToStorage(tipUniqueHeader);
        assertEquals(2, testStora.getStorage().size());
    }


    @Test
    public void cannotAddTwoTipsWithSameHeader() {

        testStora.addToStorage(tipNotUniqueHeader2);
        testStora.addToStorage(tipNotUniqueHeader1);

        assertEquals(1, testStora.getStorage().size());
    }

    @Test
    public void getCorrectTipWithHeaderSearch() {
        testStora.addToStorage(tipNotUniqueHeader2);
        testStora.addToStorage(tipUniqueHeader);
        testStora.addToStorage(tipUnique);

        assertTrue(testStora.getTipsWithSearchTerm("header", "Help").contains(tipNotUniqueHeader2));

    }

    @Test
    public void getCorrectTipsWithCreator() {
        testStora.addToStorage(tipNotUniqueHeader1);
        testStora.addToStorage(tipUniqueHeader);
        testStora.addToStorage(tipUnique);
        ArrayList<Tip> tips = testStora.getTipsWithSearchTerm("creator", "Hesus");
        assertTrue(tips.contains(tipUniqueHeader));
        assertTrue(tips.contains(tipNotUniqueHeader1));
        assertEquals(2, tips.size());

    }

    @Test
    public void getCorrectTipsWithType() {
        testStora.addToStorage(tipNotUniqueHeader1);
        testStora.addToStorage(tipUniqueHeader);
        testStora.addToStorage(tipUnique);
        ArrayList<Tip> tips = testStora.getTipsWithSearchTerm("type", "book");

        assertTrue(tips.contains(tipNotUniqueHeader1));
        assertTrue(tips.contains(tipUniqueHeader));
        assertEquals(2, tips.size());
    }

    @Test
    public void getZeroTipsWithType() {
        testStora.addToStorage(tipNotUniqueHeader1);
        testStora.addToStorage(tipUniqueHeader);
        testStora.addToStorage(tipUnique);
        ArrayList<Tip> tips = testStora.getTipsWithSearchTerm("type", "video");
        assertEquals(0, tips.size());
    }

    @Test
    public void getCorrectTipsWithTags() {
        testStora.addToStorage(tipNotUniqueHeader1);
        testStora.addToStorage(tipUniqueHeader);
        testStora.addToStorage(tipUnique);

        ArrayList<Tip> tips = testStora.getTipsWithSearchTerm("tags", "satanic");

        assertTrue(tips.contains(tipNotUniqueHeader1));
        assertTrue(tips.contains(tipUniqueHeader));
        assertEquals(2, tips.size());

    }

    @Test
    public void editTipsHeaderEditsTipInDatabase() {
        testStora.addToStorage(tipNotUniqueHeader2);
        testStora.editTip("Help", "header", "new");

        ArrayList<Tip> tips = testStora.getStorage();
        assertFalse(tips.contains(tipNotUniqueHeader2));
        assertEquals("new", tips.get(0).getHeader());
    }

    @Test
    public void editTipsCreatorEditsTipInDatabase() {
        testStora.addToStorage(tipNotUniqueHeader2);
        testStora.editTip("Help", "creator", "new");

        ArrayList<Tip> tips = testStora.getStorage();
        assertFalse(tips.contains(tipNotUniqueHeader2));
        assertEquals("new", tips.get(0).getCreator());
    }

    @Test
    public void editTipsTypeEditsTipInDatabase() {
        testStora.addToStorage(tipNotUniqueHeader2);
        testStora.editTip("Help", "type", "new");

        ArrayList<Tip> tips = testStora.getStorage();
        assertFalse(tips.contains(tipNotUniqueHeader2));
        assertEquals("new", tips.get(0).getType());
    }

    @Test
    public void editTipsTagsEditsTipInDatabase() {
        testStora.addToStorage(tipNotUniqueHeader2);
        testStora.editTip("Help", "tags", "new");

        ArrayList<Tip> tips = testStora.getStorage();
        assertFalse(tips.contains(tipNotUniqueHeader2));
        assertEquals("new", tips.get(0).getTags());
    }
}
