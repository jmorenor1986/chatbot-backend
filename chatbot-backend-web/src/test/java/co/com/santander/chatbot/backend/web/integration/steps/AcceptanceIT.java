package co.com.santander.chatbot.backend.web.integration.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber/example"},
        extraGlue = "co.com.santander.chatbot.backend.web.integration.common")
public class AcceptanceIT {
}
