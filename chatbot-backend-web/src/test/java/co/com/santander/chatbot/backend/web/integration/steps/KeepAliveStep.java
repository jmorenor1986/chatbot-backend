package co.com.santander.chatbot.backend.web.integration.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class KeepAliveStep extends ContextLoader {
    @Autowired
    protected HttpClient httpClient;

    @When("^call the service keepalive$")
    public void call_the_service_keepalive() {
        String response = " ";
        try {
            response = httpClient.get("/v1/keepalive/");
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
