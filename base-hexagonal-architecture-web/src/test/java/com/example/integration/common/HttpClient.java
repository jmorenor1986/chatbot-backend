package com.example.integration.common;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class HttpClient {
    private final String SERVER_URL = "http://localhost";
    private final String EXAMPLE_ENDPOINT = "/example";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String exampleEndPoint() {
        return SERVER_URL + ":" + port + EXAMPLE_ENDPOINT;
    }

    public String get(String resource) {
        return restTemplate.getForEntity(exampleEndPoint().concat(resource), String.class).getBody();
    }
}
