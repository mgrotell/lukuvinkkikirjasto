package databaseTests;

import lukuvinkkikirjasto.storage.Database;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    private Database testDatabase;

    @Before
    public void Initialize() {
        testDatabase = new Database(true);
    }

    @Test
    public void testConnectionCreatesConnectionForDatabase() {
        Connection connection = testDatabase.getConnection();
        try {
            boolean con = connection.isClosed();
            assertFalse(con);
        } catch (Exception e) {
            System.out.println("virhe");
        }
    }

    @Test
    public void testConnectionCreatesNewDatabaseFile() {
        File myObj = new File("test.db");
        if (myObj.exists()) myObj.delete();
        myObj = new File("test.db");
        assertFalse("testDatabase should be removed", myObj.exists());
        Connection connection = testDatabase.getConnection();
        myObj = new File("test.db");
        assertTrue(myObj.exists());
    }
}
