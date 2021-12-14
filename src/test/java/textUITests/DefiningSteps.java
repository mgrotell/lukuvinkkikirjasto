package textUITests;


import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lukuvinkkikirjasto.main.ReaderIO;
import lukuvinkkikirjasto.main.Tip;
import lukuvinkkikirjasto.main.TipHandler;
import lukuvinkkikirjasto.storage.Storage;
import lukuvinkkikirjasto.ui.TextUI;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class DefiningSteps {

    Storage storage;
    TipHandler tipHandler;
    TextUI textUi;
    TestReader testR;

    @Before
    public void start() {
        storage = new Storage(true);
        storage.deleteTestDatabase();
        storage.initializeTestDatabase();

        testR = new TestReader();
        tipHandler = new TipHandler(storage);
    }

    @Given("user enters create to add tip")
    public void userEntersCreateToAddTip() {
        testR.addLine("2");
    }

    @Given("the library has two tips in it")
    public void theLibraryHasTwoTipsInIt() {
        Tip firstTip = new Tip("The Mythical Man-Month", "A book about software engineering",
                "Fredrick Brooks", "none", "book", "software engineering, classics",
                "Classic software engineering book", "Ohjelmistotuotanto");

        Tip secondTip = new Tip("The Pratical Test Pyramid", "Recommended in ohtu lecture",
                "Martin Fowler", "https://martinfowler.com/articles/practical-test-pyramid.html",
                "blog", "software engineering, testing", "One proposition of how to test software",
                "Ohjelmistotuotanto");

        storage.addToStorage(firstTip);
        storage.addToStorage(secondTip);
    }

    @When("user enters one to list the tips")
    public void userEntersOnetoListTheTips() {
        testR.addLine("1");
        testR.addLine("0");
        textUi = new TextUI(testR, tipHandler);
        textUi.run();
    }

    @When("user chooses searchs tips")
    public void userChoosesSearchTips() {
        testR.addLine("3");
    }

    @When("user chooses search by author")
    public void userChoosesSearchByAuthor() {
        testR.addLine("3");
    }

    @When("user enters search term {string}")
    public void userEntersSearchTerm(String userInput) {
        testR.addLine(userInput);
        testR.addLine("0");
        textUi = new TextUI(testR, tipHandler);
        textUi.run();
    }

    @When("{string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} are entered")
    public void areEntered(String type, String header, String description, String creator, String url, String tags, String comment, String courses) {
        testR.addLine(type);
        testR.addLine(header);
        testR.addLine(description);
        testR.addLine(creator);
        testR.addLine(url);
        testR.addLine(tags);
        testR.addLine(comment);
        testR.addLine(courses);
        testR.addLine("0");
        textUi = new TextUI(testR, tipHandler);
        textUi.run();
    }

    @Then("printed list has items by {string} and {string}")
    public void printedListHasItemsBy(String firstWriter, String secondWriter) {
        ArrayList<String> appOutput = testR.getConsoleMessages();

        ArrayList<String> consoleMessages = outputToUnifiedArrayList(appOutput);

        assertTrue(consoleMessages.contains("Writer: " + firstWriter));
        assertTrue(consoleMessages.contains("Writer: " + secondWriter));
    }

    @Then("the app has stored an item that has the type {string}")
    public void theAppHasStoredAnItemThatHasTheType(String typeOfItem) {
        ArrayList<String> appOutput = testR.getConsoleMessages();

        ArrayList<String> consoleMessages = outputToUnifiedArrayList(appOutput);

        boolean found = false;
        for(String created:consoleMessages){

            if(created.contains(typeOfItem)){
                found = true;
            }

        }
        assertTrue(found);
    }

    @Then("prints out a list with items by {string} and no entries by {string}")
    public void printsOutsAListWithItemsByAndNoEntriesBy(String firstAuthor, String secondAuthor) {
        ArrayList<String> appOutput = testR.getConsoleMessages();

        ArrayList<String> consoleMessages = outputToUnifiedArrayList(appOutput);

        assertTrue(consoleMessages.contains("Writer: " + firstAuthor));
        assertFalse(consoleMessages.contains("Writer: " + secondAuthor));
    }

    @Then("tip is created")
    public void tipIsCreated() {
        assertEquals(1, this.tipHandler.getAllTips().size());
    }

    private ArrayList<String> outputToUnifiedArrayList(ArrayList<String> messages) {
        ArrayList<String> consoleMessagesAsIndividualStrings = new ArrayList<String>();

        for (String messageArray : messages) {
            String[] splittedMessage = messageArray.split("\\n");
            for (String message : splittedMessage) {
                consoleMessagesAsIndividualStrings.add(message);
            }
        }

        return consoleMessagesAsIndividualStrings;
    }
}

class TestReader implements ReaderIO {

    int currentMessage;
    ArrayList<String> sentMessages;
    ArrayList<String> consoleMessages;
    int tipsCreated;

    public TestReader() {
        sentMessages = new ArrayList<>();
        consoleMessages = new ArrayList<>();
        currentMessage = 0;
        this.tipsCreated = 0;
    }

    public String nextLine() {
        return sentMessages.get(currentMessage++);
    }

    public ArrayList<String> getConsoleMessages() {
        return this.consoleMessages;
    }

    public int getTipsCreated() {
        return this.tipsCreated;
    }

    public void println(String line) {
        consoleMessages.add(line);
        if (line.equals("Tip created!")) {
            this.tipsCreated++;
        }
    }

    public void addLine(String line) {
        sentMessages.add(line);
    }
}