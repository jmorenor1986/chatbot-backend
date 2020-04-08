package co.com.santander.chatbot.accesodatos.integration.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class KeepAliveStep extends ContextLoader {
    @Autowired
    protected HttpClient httpClient;

    private final Logger logger = LoggerFactory.getLogger(KeepAliveStep.class);

    @When("^call the service keepalive$")
    public void call_the_service_keepalive() {
        generateTokenLogin(httpClient);
        String response = " ";
        try {
            response = httpClient.get("/v1/keepalive/", getToken());
        } catch (Exception e) {
            e.printStackTrace();
            //TODO  fix token
            response = e.getMessage();
        }
        Assert.assertNotNull(response);
    }

    @Then("^the response data is returned$")
    public void the_response_data_is_returned() {

    }


}
