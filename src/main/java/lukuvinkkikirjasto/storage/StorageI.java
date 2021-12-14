package lukuvinkkikirjasto.storage;

import lukuvinkkikirjasto.main.Tip;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface StorageI {
    void addToStorage(Tip tip);

    void deleteTestDatabase();

    void initializeTestDatabase();

    ArrayList<Tip> getStorage();

    ArrayList<Tip> getTipsWithSearchTerm(String column, String term);

    ArrayList<Tip> getTipsFromQuery(ResultSet queryResult);

    void editTip(String header, String column, String term);
}
