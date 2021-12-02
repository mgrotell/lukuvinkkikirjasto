package textUITests;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lukuvinkkikirjasto.main.ReaderIO;
import lukuvinkkikirjasto.storage.Storage;
import lukuvinkkikirjasto.ui.TextUI;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class DefiningSteps {

    Storage storage;
    TextUI textUi;
    TestReader testR;

    @Before
    public void start() {
        storage = new Storage();
        testR = new TestReader();

    }


    @Given("User enters {int} to add tip")
    public void user_enters_to_add_tip(Integer path) {
        testR.addLine(path+"");

    }

    @When("{string}, {string},  {string}, {string}, {string}, {string}, {string}  {string} are entered")
    public void are_entered(String type, String header, String description, String creator, String url, String tags, String comment, String courses) {
        testR.addLine(type);
        testR.addLine(header);
        testR.addLine(description);
        testR.addLine(creator);
        testR.addLine(url);
        testR.addLine(tags);
        testR.addLine(comment);
        testR.addLine(courses);

    }

    @Then("tip is created")
    public void tip_is_created() {
        textUi = new TextUI(testR, storage);

        assertEquals(1, storage.getStorage().size());


    }




}

class TestReader implements ReaderIO {

    int currentMessage;
    ArrayList<String> sentMessages;

    public TestReader() {
        new TestReader(new ArrayList<>());
    }

    public TestReader(ArrayList<String> messages) {
        sentMessages = messages;
        currentMessage = 0;
    }

    public String nextLine() {
        return sentMessages.get(currentMessage++);
    }

    public void addLine(String line) {
        sentMessages.add(line);

    }
}




