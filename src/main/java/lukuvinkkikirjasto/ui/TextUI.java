package lukuvinkkikirjasto.ui;

import lukuvinkkikirjasto.main.ReaderIO;
import lukuvinkkikirjasto.main.Tip;
import lukuvinkkikirjasto.main.TipHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TextUI {


    private ReaderIO input;
    private TipHandler tipHandler;
    private Map<String, String> tipOptions;
    private Map<String, String> linesToEdit;

    public TextUI(ReaderIO scanner, TipHandler tipHandler) {
        this.input = scanner;
        this.tipHandler = tipHandler;
        this.tipOptions = new HashMap<>();
        this.tipOptions.put("1", "book");
        this.tipOptions.put("2", "video");
        this.tipOptions.put("3", "podcast");
        this.tipOptions.put("4", "blog");
        this.linesToEdit = new HashMap<>();
        this.linesToEdit.put("1", "header");
        this.linesToEdit.put("2", "creator");
        this.linesToEdit.put("3", "description");
        this.linesToEdit.put("4", "url");
        this.linesToEdit.put("5", "type");
        this.linesToEdit.put("6", "tags");
        this.linesToEdit.put("7", "comment");
        this.linesToEdit.put("8", "courses");


    }

    public void run() {

        while (true) {

            String operation = startPrint();

            if (operation.equals("1")) {
                this.input.println(tipHandler.listAllTips());
            } else if (operation.equals("2")) {
                addTip();
            } else if (operation.equals("3")) {
                this.input.println("Choose one of the following options:\n");
                searchTips();
            } else if (operation.equals("4")) {
                editTips();
            } else if (operation.equals("4")) {
                deleteTips();
            } else if (operation.equals("0")) {
                break;
            }
        }
    }

    public String startPrint() {
        
        this.input.println("1. List all tips.\n" +
                "2. Create a new tip\n" +
                "3. Search tips\n" +
                "4. Edit tips\n" +
                "5. Delete a tip\n" +
                "0. Exit\n" +
                "expected input (1/2/3/4/5/0)");

        String operation = this.input.nextLine();

        return operation;
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

        this.tipHandler.createTip(header, description, creator, url, type, tags, comment, courses);

        this.input.println("Tip created!");
    }


    private String getTipType() {

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

    private void searchTips() {

        this.input.println("1. Search by type.\n" +
                "2. Search by title.\n" +
                "3. Search by author.\n" +
                "4. Search by tag(s).\n" +
                "expected input (1/2/3/4/0)");

        String option = input.nextLine();

        if (option.equals("1")) {
            selectType();
        } else if (option.equals("2")) {
            selectTitle();
        } else if (option.equals("3")) {
            selectAuthor();
        } else if (option.equals("4")) {
            selectTags();
        } else {
            this.input.println("Select search option");
            searchTips();
        }
    }

    private void selectType() {
        this.input.println("Select the type:\n");
        this.input.println("1. Book\n" +
                "2. Video\n" +
                "3. Podcast\n" +
                "4. Blog\n" +
                "expected input (1/2/3/4/0)");

        String typeNumber = input.nextLine();

        this.input.println(tipHandler.searchTipsByType("type", typeNumber));
    }

    private void selectTitle() {
        this.input.println("Give the title:");
        String titleName = input.nextLine();
        this.input.println(tipHandler.searchTipsByTerm("header", titleName));
    }

    private void selectAuthor() {
        this.input.println("Give the name of the author:");
        String author = input.nextLine();
        this.input.println(tipHandler.searchTipsByTerm("creator", author));
    }

    private void selectTags() {
        this.input.println("Give a tag or tags separated by ',':");
        String tag = input.nextLine();
        this.input.println(tipHandler.searchTipsByTerm("tags", tag));
    }

    private void editTips() {

        String option = editType();

        if (option.equals("1")) {
            this.input.println("Give the title:");
            String titleName = input.nextLine();
            ArrayList<Tip> tips = tipHandler.getTipsByTerm("header", titleName);
            if (tips.isEmpty()) {
                this.input.println("\n" + tips.size() + " tips found.\n\n");
            }
            for (Tip tip : tips) {
                this.input.println(tip.toString());
                this.input.println("Edit? (y/n)");
                String answer = input.nextLine();
                if (answer.equals("y")) {


                    String editLine = editOptionsByField();

                } else if (answer.equals("n")) {
                    break;
                }
            }
        } else if (option.equals("2")) {
            selectAuthor();
        } else {
            editTips();
        }
    }


    public String editType() {

        this.input.println("Select the tip to be edited\n" +
                "1. Select by title\n" +
                "2. Select by author\n" +
                "3. Select by xxxxx");

        String option = input.nextLine();
        return option;
    }

    public String editOptionsByField() {


        this.input.println("Let's edit!\n" +
                "1. Edit title?\n" +
                "2. ...author?\n" +
                "3. ...description?\n" +
                "4. ...URL?\n" +
                "5. ...type?\n" +
                "6. ...tags?\n" +
                "7. ...comments?\n" +
                "8. ...related courses?\n");

        String option = input.nextLine();
        return option;
    }

    private void deleteTips() {
        this.input.println("Select the tip to delete");
        //this.input.println(getTipsAsShortList);
    }

}
