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
import static org.junit.Assert.assertTrue;

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

        assertEquals(20, tipH.getAllTips().size());

    }


    @Test
    public void addOneList() {
        tipH.createTip("Ada", "Lovelace",
                "Isaac", "Asimov", "Book", "RickROll",
                "Mormoni", "Lama");

        assertEquals(21, tipH.getAllTips().size());

    }

    @Test
    public void addOneListFoundByHeader() {
        tipH.createTip("Ada", "Lovelace",
                "Isaac", "Asimov", "book", "RickROll",
                "Mormoni", "Lama");


        assertTrue(tipH.searchTipsByTerm("", "Ada").contains("Ada"));


    }

    @Test
    public void addTwoListFoundByHeader() {

        tipH.createTip("Ada", "Lovelace",
                "Isaac", "Asimov", "book", "RickROll",
                "Mormoni", "Lama");

        tipH.createTip("BDA", "Lovelace",
                "Isaac", "Asimov", "blog", "RickROll",
                "Mormoni", "Lama");


        assertTrue(tipH.searchTipsByTerm("", "Ada").contains("Ada"));
        assertTrue(tipH.searchTipsByTerm("", "BDA").contains("BDA"));

    }


    @Test
    public void addOneListFoundByType() {
        tipH.createTip("Ada", "Lovelace",
                "Isaac", "Asimov", "book", "RickROll",
                "Mormoni", "Lama");


        assertTrue(tipH.searchTipsByTerm("", "Ada").contains("Ada"));


    }

    @Test
    public void addTwoListFoundByType() {

        tipH.createTip("Ada", "Lovelace",
                "Isaac", "Asimov", "book", "RickROll",
                "Mormoni", "Lama");

        tipH.createTip("BDA", "Lovelace",
                "Isaac", "Asimov", "blog", "RickROll",
                "Mormoni", "Lama");


        assertTrue(tipH.searchTipsByTerm("", "Ada").contains("Ada"));
        assertTrue(tipH.searchTipsByTerm("", "BDA").contains("BDA"));

    }


    class StorageForTest implements StorageI {


        private ArrayList<Tip> tips = new ArrayList<>();
        private ArrayList<String> words;
        private HashMap<String, String> tipOptions;

        @Override
        public void addToStorage(Tip tip) {

            boolean check = true;
            for (Tip tipToBeAdded : tips) {
                if (tipToBeAdded.getHeader().equals(tip.getHeader())) {
                    check = false;
                }
            }

            if (check) {
                tips.add(tip);
            }

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

                String type = "" + randomWord.nextInt(5);

                Tip tipN = new Tip(words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)),
                        words.get(randomWord.nextInt(11)),
                        tipOptions.get(type),
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
            ArrayList<Tip> tipOne = new ArrayList();
            if (column.equals("")) {

                for (Tip tip : tips) {
                    if (tip.getHeader().contains(term)) {
                        tipOne.add(tip);
                    }
                }

            } else {

                for (Tip tip : tips) {
                    if (tip.getType().contains(term)) {
                        tipOne.add(tip);
                    }
                }
            }
            return tipOne;
        }

        @Override
        public ArrayList<Tip> getTipsFromQuery(ResultSet queryResult) {
            return null;
        }

        @Override
        public void editTip(String header, String column, String term) {

        }
    }


}
