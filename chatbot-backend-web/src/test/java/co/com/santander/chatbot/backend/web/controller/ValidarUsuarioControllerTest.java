package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.controller.payload.RespuestaPayload;
import co.com.santander.chatbot.backend.web.controller.payload.UsuarioPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ValidarUsuarioControllerTest {

    private ValidarUsuarioController validarUsuarioController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validarUsuarioController = new ValidarUsuarioController();
    }

    @Test
    public void testValidarUsuarioSuccess() {
        ResponseEntity<RespuestaPayload> result = validarUsuarioController.validar(UsuarioPayload.builder()
                .colaIdentificacion("4562")
                .telefono(Long.parseLong("3105235467"))
                .build());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getStatusCodeValue(), 200);

    }

}
