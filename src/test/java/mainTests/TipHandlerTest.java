package mainTests;


import lukuvinkkikirjasto.main.Tip;
import lukuvinkkikirjasto.main.TipHandler;
import lukuvinkkikirjasto.main.TipHandlerI;
import lukuvinkkikirjasto.storage.StorageI;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TipHandlerTest {

    private TipHandlerI tipH;

    private StorageI storage;


    @Before
    public void initialize() {


        storage = new StorageForTest();
        storage.initializeTestDatabase();


        tipH = new TipHandler(storage);


    }

    @Test
    public void tipsLoadedWithRightNumber() {

        assertEquals(20, storage.getStorage().size());

    }

    class StorageForTest implements StorageI {

        private final ArrayList<Tip> tips = new ArrayList<>();
        private ArrayList<String> words;
        private HashMap<String, String> tipOptions;

        @Override
        public void addToStorage(Tip tip) {


            tips.add(tip);

        }

        @Override
        public void deleteTestDatabase() {

        }

        @Override
        public void initializeTestDatabase() {
            this.tipOptions = new HashMap<>();
            this.tipOptions.put("1", "book");
            this.tipOptions.put("2", "video");
            this.tipOptions.put("3", "podcast");
            this.tipOptions.put("4", "blog");
            this.tipOptions.put("5", "https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley");
            words = new ArrayList<>();

            words.add("Kaapon");
            words.add("Robotti");
            words.add("Jeesus");
            words.add("Ada Lovelace");
            words.add("Lovelace");
            words.add("google");
            words.add("Isaac");
            words.add("Asimov");
            words.add("Lama 2");
            words.add("LaMan paluu");
            words.add("LaMa");
            words.add("JoLo");

            Random randomWord = new Random();

            for (int i = 0; i < 20; i++) {

                Tip tipN = new Tip(words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)));

                tips.add(tipN);
            }


        }

        @Override
        public ArrayList<Tip> getStorage() {
            return tips;
        }

        @Override
        public ArrayList<Tip> getTipsWithSearchTerm(String column, String term) {
            return null;
        }

        @Override
        public ArrayList<Tip> getTipsFromQuery(ResultSet queryResult) {
            return null;
        }
    }


}
