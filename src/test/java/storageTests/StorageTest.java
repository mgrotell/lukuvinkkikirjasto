package storageTests;

import lukuvinkkikirjasto.main.Tip;
import lukuvinkkikirjasto.storage.Storage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StorageTest {


    private Storage testStora;

    private int compareValueAddedTips = 0;


    @Before
    public void Initialize() {
        testStora = new Storage(true);
        testStora.deleteTestDatabase();
        testStora.initializeTestDatabase();


    }


    @Test
    public void addOneToStorage() {

        Tip tipOne = new Tip("Help", "Us", "Hesus",
                "heaven", "Book", "satanic", "meh", "muumi");

        compareValueAddedTips++;
        testStora.addToStorage(tipOne);

        assertEquals(1, testStora.getStorage().size());

    }


    @Test
    public void addTwoAlmostSameToStorage() {

        Tip tipOne = new Tip("Help", "Us", "Hesus",
                "heaven", "Book", "satanic", "meh", "muumi");
        compareValueAddedTips++;

        Tip tipTwo = new Tip("Help", "Us", "Hesus",
                "heaven", "Book", "satanic", "meh", "Narnia");
        compareValueAddedTips++;

        testStora.addToStorage(tipOne);
        testStora.addToStorage(tipTwo);

        assertEquals(compareValueAddedTips, testStora.getStorage().size());

    }


    @Test
    public void addTwoSameToStorage() {

        Tip tipOne = new Tip("Help", "Us", "Hesus",
                "heaven", "Book", "satanic", "meh", "muumi");
        compareValueAddedTips++;
        Tip tipTwo = new Tip("Help", "Us", "Hesus",
                "heaven", "Book", "satanic", "meh", "muumi");
        compareValueAddedTips++;
        testStora.addToStorage(tipOne);

        testStora.addToStorage(tipTwo);

        assertEquals(compareValueAddedTips, testStora.getStorage().size());

    }


}
