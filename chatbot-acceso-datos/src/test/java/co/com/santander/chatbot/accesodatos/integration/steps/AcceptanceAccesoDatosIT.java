package co.com.santander.chatbot.accesodatos.integration.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
        plugin = {"pretty", "html:target/cucumber/accesodatos"},
        glue = "co.com.santander.chatbot.accesodatos.integration.steps")
public class AcceptanceAccesoDatosIT {
}
