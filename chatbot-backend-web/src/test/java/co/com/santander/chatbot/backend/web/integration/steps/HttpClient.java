package co.com.santander.chatbot.backend.web.integration.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class HttpClient {
    private final String SERVER_URL = "https://localhost";
    private final String EXAMPLE_ENDPOINT = "/chatbot-backend";

    @LocalServerPort
    private int port;
    @Autowired
    private  RestTemplate restTemplate;

    private String exampleEndPoint() {
        return SERVER_URL + ":" + port + EXAMPLE_ENDPOINT;
    }

    public String get(String resource) {
        return restTemplate.getForEntity(exampleEndPoint().concat(resource), String.class).getBody();
    }

}
