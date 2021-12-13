package textUITests;


import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lukuvinkkikirjasto.main.ReaderIO;
import lukuvinkkikirjasto.storage.Storage;
import lukuvinkkikirjasto.main.TipHandler;
import lukuvinkkikirjasto.ui.TextUI;

import java.util.ArrayList;
import java.util.Arrays;
import lukuvinkkikirjasto.main.Tip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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
   
    @Then("in the list printed by the app there are writers {string} and {string}")
    public void inTheListPrintedByTheAppThereAreWriters(String firstWriter, String secondWriter) {
        ArrayList<String> appOutput = testR.getConsoleMessages();

        String firstTip = appOutput.get(2);
        String secondTip = appOutput.get(3);

        String[] firstTipAsArray = firstTip.split("\\n");
        String[] secondTipAsArray = secondTip.split("\\n");

        assertTrue(Arrays.asList(firstTipAsArray).contains("Writer: " + firstWriter));
        assertTrue(Arrays.asList(secondTipAsArray).contains("Writer: " + secondWriter));
    }

    @When("{string}, {string},  {string}, {string}, {string}, {string}, {string}  {string} are entered")
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

    @Then("tip is created")
    public void tipIsCreated() {        
        assertEquals(1, this.testR.tipsCreated);
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