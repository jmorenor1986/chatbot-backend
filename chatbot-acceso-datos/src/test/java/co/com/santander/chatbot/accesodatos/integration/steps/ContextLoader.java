package co.com.santander.chatbot.accesodatos.integration.steps;

import co.com.santander.chatbot.domain.dto.security.TokenDto;
import co.com.santander.chatbot.domain.dto.security.UsuarioDto;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ContextLoader {
    private final String USER = "jnsierrac@gmail.com";
    private final String PASS = "1234";
    @Setter
    @Getter
    private String token;

    @Before
    public void setUp() {
    }

    public void generateTokenLogin(HttpClient httpClient) {
        ResponseEntity<String> responseEntity = httpClient.postWithRequest("/login/", UsuarioDto.builder()
                .correo(USER)
                .contrasena(PASS)
                .build());
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            TokenDto token = new Gson().fromJson(responseEntity.getBody(), TokenDto.class);
            setToken("Bearer ".concat(token.getToken()));
        }

    }
}
