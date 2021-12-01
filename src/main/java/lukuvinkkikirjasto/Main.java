/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukuvinkkikirjasto;

import lukuvinkkikirjasto.main.Reader;
import lukuvinkkikirjasto.main.ReaderIO;
import lukuvinkkikirjasto.storage.Storage;
import lukuvinkkikirjasto.ui.TextUI;

/**
 * @author miklas
 */

public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        Storage storage = new Storage();

        ReaderIO reader = new Reader();

        TextUI ui = new TextUI(reader, storage);
        ui.run();

    }

}
