package co.com.santander.chatbot.accesodatos.integration.steps;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

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

    public String get(String resource, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        return restTemplate.exchange(exampleEndPoint().concat(resource),HttpMethod.GET, request,String.class ).getBody();
    }

    public ResponseEntity<String> postWithRequest(String resource, Object payload, String token){
        System.out.println(exampleEndPoint().concat(resource));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Object> request = new HttpEntity<>(new Gson().toJson(payload));
        RequestEntity<String> requestEntity = RequestEntity.post(URI.create( exampleEndPoint().concat(resource) )  )
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Gson().toJson(payload));
        return restTemplate.exchange(requestEntity, String.class);
    }

    public ResponseEntity<String> postWithRequest(String resource, Object payload){
        HttpEntity<Object> request = new HttpEntity<>(payload);
        return restTemplate.exchange(exampleEndPoint().concat(resource),HttpMethod.POST, request, String.class);
    }

}
