package textUITests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/cucu/"},
        plugin = {"pretty"},
        glue = {"src/test/java/textUITests/"},
        snippets = CucumberOptions.SnippetType.CAMELCASE


)

public class RunCucumberTest{


}