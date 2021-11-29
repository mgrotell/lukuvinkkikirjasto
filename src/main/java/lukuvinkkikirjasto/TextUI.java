package lukuvinkkikirjasto;

import java.util.Scanner;

public class TextUI {


    private  Scanner input;
    private  Storage storage;

    public TextUI(Scanner scanner, Storage storage) {
        this.input = scanner;
        this.storage = storage;
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
                System.out.println(storage.getStorage());
            } else if (operation.equals("2")) {
                addTip();
            } else if (operation.equals("0")) {
                break;
            }


        }
    }

    private void addTip() {
        System.out.println("Let´s create a tip. Select type: (Book)");
        String type = this.input.nextLine();

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

        Tip tiptap = new Tip(header, description, creator, url, type, tags, comment, courses);

        storage.addToStorage(tiptap);

    }
}
