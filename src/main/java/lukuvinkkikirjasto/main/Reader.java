package lukuvinkkikirjasto.main;

import java.util.Scanner;

public class Reader implements ReaderIO {

    private Scanner reader;

    public Reader() {

        reader = new Scanner(System.in);

    }

    public String nextLine() {
        return reader.nextLine();

    }


}
