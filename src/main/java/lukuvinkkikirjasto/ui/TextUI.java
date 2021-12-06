package lukuvinkkikirjasto.ui;

import lukuvinkkikirjasto.main.ReaderIO;
import lukuvinkkikirjasto.main.Tip;
import lukuvinkkikirjasto.storage.Storage;


public class TextUI {


    private ReaderIO input;
    private Storage storage;

    public TextUI(ReaderIO scanner, Storage storage) {
        this.input = scanner;
        this.storage = storage;
    }

    public void run() {


        while (true) {

            this.input.println("1. List all tips.\n" +
                    "2. Create new tip\n" +
                    "0. Exit\n" +
                    "expected input (1/2/0)");

            String operation = this.input.nextLine();

            if (operation.equals("1")) {
                this.input.println("1");
                for( Tip tip : storage.getStorage()){
                    this.input.println(tip.toString());
                }
            } else if (operation.equals("2")) {
                addTip();
            } else if (operation.equals("0")) {
                break;
            }


        }
    }

    private void addTip() {

        String type = getTipType();

        this.input.println("Insert header");

        String header = this.input.nextLine();

        this.input.println("Insert a description:\n");

        String description = this.input.nextLine();

        this.input.println("Insert the creator´s name:\n");

        String creator = this.input.nextLine();

        this.input.println("Insert url:\n");

        String url = this.input.nextLine();

        this.input.println("Insert a tags and separate with a ',':\n");

        String tags = this.input.nextLine();

        this.input.println("Insert a comment:\n ");

        String comment = this.input.nextLine();

        this.input.println("Insert a courses and separate with a ',':\n");

        String courses = this.input.nextLine();

        createTip(storage, header, description, creator, url, type, tags, comment, courses);
    }

    public void createTip(Storage storage, String header, String description,
                          String creator, String url, String type, String tags, String comment,
                          String courses) {

        Tip tiptap = new Tip(header, description, creator, url, type, tags, comment, courses);
        this.input.println("Tip created!");
        storage.addToStorage(tiptap);
    }


    public String getTipType() {

        this.input.println("Let´s create a tip. \n\n" +
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