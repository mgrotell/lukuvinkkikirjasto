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

            this.input.println("\n1. List all tips.\n" +
                    "2. Create a new tip\n" +
                    "3. Search tips\n" +
                    "4. Edit or delete tips\n" +
                    "0. Exit\n" +
                    "expected input (1/2/3/4/0)");

            String operation = this.input.nextLine();

            if (operation.equals("1")) {
                this.input.println(tipHandler.listAllTips());
            } else if (operation.equals("2")) {
                addTip();
            } else if (operation.equals("3")) {
                searchTips();  
	        } else if (operation.equals("4")) {
                editAndDeleteTips();
            } else if (operation.equals("0")) {
                break;
            }
        }
    }


    private void addTip() {

        String type = getTipType();

        this.input.println("Insert header");
        String header = this.input.nextLine();

        this.input.println("Insert a description:");
        String description = this.input.nextLine();

        this.input.println("Insert the creator´s name:");
        String creator = this.input.nextLine();

        this.input.println("Insert url:");
        String url = this.input.nextLine();

        this.input.println("Insert tags and separate with a ',':");
        String tags = this.input.nextLine();

        this.input.println("Insert a comment: ");
        String comment = this.input.nextLine();

        this.input.println("Insert courses and separate with a ',':");
        String courses = this.input.nextLine();

        this.tipHandler.createTip(header, description, creator, url, type, tags, comment, courses);

        this.input.println("\nTip created!");
    }


    private String getTipType() {

        this.input.println("Let´s create a tip. \n\n" +
                "Select type:\n" +
                "1. Book \n" +
                "2. Video \n" +
                "3. Podcast \n" +
                "4. Blog \n");

        String type = this.input.nextLine();


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

        this.input.println("Select one of the following options:\n");
        this.input.println("1. Search by type.\n" +
                "2. Search by title.\n" +
                "3. Search by author.\n" +
                "4. Search by tag(s).\n" +
                "expected input (1/2/3/4/0)");

        String option = this.input.nextLine();

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


        String typeNumber = this.input.nextLine();
        this.input.println(tipHandler.searchTipsByType("type", typeNumber));     
    }

    private void selectTitle() {
        this.input.println("Give the title:");
        String titleName = this.input.nextLine();
        this.input.println(tipHandler.searchTipsByTerm("header", titleName));
    }

    private void selectAuthor() {
        this.input.println("Give the name of the author:");
        String author = this.input.nextLine();
        this.input.println(tipHandler.searchTipsByTerm("creator", author));
    }

    private void selectTags() {
        this.input.println("Give a tag or tags separated by ',':");
        String tag = this.input.nextLine();
        this.input.println(tipHandler.searchTipsByTerm("tags", tag));
    }

    private void editAndDeleteTips() {

        this.input.println("Select the tip to be edited\n" +
                "1. Select by title\n" +
                "2. Select by author\n");

	    String option = this.input.nextLine();

        if (option.equals("1")) {
            this.input.println("Give the title:");
            String titleName = this.input.nextLine();
            ArrayList<Tip> tips = tipHandler.getTipsByTerm("header", titleName);
            this.input.println("\n" + tips.size() + " tips found.\n\n");

            int counter = 0;
            printSearchResults(tips, counter);


        } else if (option.equals("2")) {
            this.input.println("Give the name of the author:");
            String author = this.input.nextLine();
            ArrayList<Tip> tips = tipHandler.getTipsByTerm("creator", author);
            this.input.println("\n" + tips.size() + " tips found.\n\n");

            int counter = 0;
            printSearchResults(tips, counter);
            
        } else {
            editAndDeleteTips();
        }
    }

    private void printSearchResults(ArrayList<Tip> tips, int counter) {

        for (int i = counter; i < counter + 3; i++) {
            if (i == tips.size()) {
                break;
            }
            this.input.println("\n" + (i + 1) + ".\n" + tips.get(i).toString());
            if (i != 0 && i != tips.size() - 1 && (i + 1) % 3 == 0) {
                this.input.println("Press 'm' for more results or select the number of the tip:");
            }
        }
        input.println("Select the number of the tip to be edited:");
        String answer = this.input.nextLine();
        if (answer.equals("m")) {
            counter += 3;
            printSearchResults(tips, counter);
        } else if (answer.matches("[1-9][0-9]*[0-9]*")) {
            int number = Integer.valueOf(answer);
            Tip editable = tips.get(number - 1);
            edit(editable);
        }
    }

    private void edit(Tip tip) {
        
        this.input.println("\nLet's edit!\n" +
                    "1. Edit title?\n" +
                    "2. ...author?\n" +
                    "3. ...description?\n" + 
                    "4. ...URL?\n" +
                    "5. ...type?\n" +
                    "6. ...tags?\n" +
                    "7. ...comments?\n" +
                    "8. ...related courses?\n\n" +
                    "9. ...delete the tip?\n");

        String editLine = this.input.nextLine();  //numero
        if (editLine.equals("9")) {
            this.input.println("Are you sure you want to delete the tip? (y/n)");
            String answer = this.input.nextLine();
            if (answer.equals("y")) {
                tipHandler.deleteTip(tip.getHeader());
            }             
        } else {
            String column = linesToEdit.get(editLine);   //numeroon liittyvä kenttä
            input.println("Insert new " + column + ":");
            String newTerm = this.input.nextLine();
            if (newTerm != null) {
                tipHandler.editTip(tip.getHeader(), column, newTerm);
            }
        }	       		    
    }
}
