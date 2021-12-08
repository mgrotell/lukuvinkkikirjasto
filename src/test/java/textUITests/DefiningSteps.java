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

    @Given("User enters create to add tip")
    public void userEntersCreateToAddTip() {
        testR.addLine("2");
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
<<<<<<< HEAD

=======
        System.out.println(this.tipHandler.getAllTips());
>>>>>>> 73eb4eca107b98cf4caecc11579915d23f3f52ea
        
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
    public ArrayList<String> getConsoleMessages(){
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