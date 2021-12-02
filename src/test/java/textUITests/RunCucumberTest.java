package textUITests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/cucu/",
        snippets = CucumberOptions.SnippetType.CAMELCASE


)

public class RunCucumberTest{


}