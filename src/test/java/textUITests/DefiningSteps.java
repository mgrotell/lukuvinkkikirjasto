package textUITests;


import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lukuvinkkikirjasto.main.ReaderIO;
import lukuvinkkikirjasto.storage.Storage;
import lukuvinkkikirjasto.ui.TextUI;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DefiningSteps {

    Storage storage;
    TextUI textUi;
    TestReader testR;

  @Before
    public void start() {
        storage = new Storage();
        testR = new TestReader();

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
        textUi = new TextUI(testR, storage);

    }
    @Then("tip is created")
    public void tipIsCreated() {
       assertTrue(true);// korjaa loppu osa
    }



}

class TestReader implements ReaderIO {

    int currentMessage;
    ArrayList<String> sentMessages;

    public TestReader() {
        sentMessages = new ArrayList<>();
        currentMessage = 0;
    }

    public String nextLine() {

        return sentMessages.get(currentMessage++);

    }

    public void addLine(String line) {
        sentMessages.add(line);

    }
}




