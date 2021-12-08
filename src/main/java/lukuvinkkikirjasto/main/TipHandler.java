package lukuvinkkikirjasto.main;

import lukuvinkkikirjasto.main.Tip;
import lukuvinkkikirjasto.storage.Storage;

import java.util.ArrayList;

public class TipHandler implements TipHandlerI {
    private Storage storage;
    private ArrayList<Tip> tipsList;

    public TipHandler(Storage storage) {
        this.storage = storage;
    }

    // createTip tänne, koska sen oleminen UI:ssä rikkoo single principle -periaatetta.
    // myöskin UI:n vaihtaminen esimerkiksi JavaFX:ksi onnistuu helpommin, jos 
    // UI-olio kutsuu ainoastaan handleria

    public void createTip(String header, String description, String creator, String url, 
                          String type, String tags, String comment, String courses) {

        Tip newTip = new Tip(header, description, creator, url, type, tags, comment, courses);
        storage.addToStorage(newTip);
    }


    public ArrayList<Tip> getAllTips() {
        // mitä täällä tehdään riippuu siitä missä muodossa getStorage-luokka palauttaa
        // tipsit mikäli kyse on ArrayList<String>:ejä sisältävästä ArrayLististä, niin 
        // sitten täällä tapahtuisi muunnosta ArrayList<String> -> ArrayList<Tip>. 
        // sitten tämä lista palautetaan UI:lle 

        ArrayList<Tip> tipsList = storage.getStorage();
        return tipsList;
    }

    public void deleteTip(String id) {
        // storage.deleteTip(id);
    }

    public ArrayList<Tip> searchTips(String searchString, String searchField) {
        // tipsList = storage.search(seachString, searchField);

        // samoin kuin getStoredTips-metodin kanssa. mitä täällä tehdään riippuu
        // siitä missä muodossa storage palauttaa löydetyt tipit

        // return foundTips;
        return null;
    }
}