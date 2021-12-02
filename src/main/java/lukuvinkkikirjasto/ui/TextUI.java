package lukuvinkkikirjasto.ui;

import lukuvinkkikirjasto.main.ReaderIO;
import lukuvinkkikirjasto.main.Tip;
import lukuvinkkikirjasto.main.TipHandler;
import lukuvinkkikirjasto.storage.Storage;

public class TextUI {


    private ReaderIO input;
    private TipHandler tipHandler;

    public TextUI(ReaderIO scanner, TipHandler tipHandler) {
        this.input = scanner;
        this.tipHandler = tipHandler;
    }

    public void run() {


        while (true) {

            System.out.println("1. List all tips.\n" +
                    "2. Create new tip\n" +
                    "0. Exit\n" +
                    "expected input (1/2/0)");

            String operation = this.input.nextLine();

            if (operation.equals("1")) {
                System.out.println("1");
                System.out.println(tipHandler.getStorage());
            } else if (operation.equals("2")) {
                addTip();
            } else if (operation.equals("0")) {
                break;
            }


        }
    }

    private void addTip() {

        String type = getTipType();

        System.out.println("Insert header");

        String header = this.input.nextLine();

        System.out.println("Insert a description:\n");

        String description = this.input.nextLine();

        System.out.println("Insert the creator´s name:\n");

        String creator = this.input.nextLine();

        System.out.println("Insert url:\n");

        String url = this.input.nextLine();

        System.out.println("Insert a tags and separate with a ',':\n");

        String tags = this.input.nextLine();

        System.out.println("Insert a comment:\n ");

        String comment = this.input.nextLine();

        System.out.println("Insert a courses and separate with a ',':\n");

        String courses = this.input.nextLine();

        this.tipHandler.createTip(header, description, creator, url, type, tags, comment, courses);
    }


    public String getTipType() {

        System.out.println("Let´s create a tip. \n\n" +
                "Select type:\n" +
                "1. Book \n" +
                "2. Video \n" +
                "3. Podcast \n" +
                "4. Blog \n");

        String type = input.nextLine();


        if (type.equals("1")) {
            type = "book";
        } else if (type.equals("2")) {
            type = "video";
        } else if (type.equals("3")) {
            type = "podcast";
        } else if (type.equals("4")) {
            type = "blog";
        }

        return type;
    }

}