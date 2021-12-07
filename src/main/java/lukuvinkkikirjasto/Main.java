package lukuvinkkikirjasto;

import lukuvinkkikirjasto.main.Reader;
import lukuvinkkikirjasto.main.ReaderIO;
import lukuvinkkikirjasto.storage.Storage;
import lukuvinkkikirjasto.main.TipHandler;
import lukuvinkkikirjasto.ui.TextUI;



public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        Storage storage = new Storage(false);

        TipHandler tipHandler = new TipHandler(storage);

        ReaderIO reader = new Reader();

        TextUI ui = new TextUI(reader, tipHandler);
        ui.run();

    }

}
