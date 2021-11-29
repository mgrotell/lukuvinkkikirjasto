package Lukuvinkkikirjasto;
import java.util.ArrayList;

import java.util.ArrayList;

public class Storage {
    private ArrayList<Tip> tips;

    public Storage() {
        this.tips = new ArrayList<>();
    }

    public void addToStorage(Tip tip) {
        this.tips.add(tip);
    }

    public ArrayList getStorage() {
        return this.tips;
    }

}
