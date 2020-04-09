package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.controller.payload.RespuestaPayload;
import co.com.santander.chatbot.backend.web.controller.payload.UsuarioPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
        UsuarioPayload usuario = UsuarioPayload.builder()
                .colaIdentificacion("4562")
                .telefono(Long.parseLong("3105235467"))
                .build();
        ResponseEntity<RespuestaPayload> result = validarUsuarioController.validar(usuario);
        RespuestaPayload respuesta = RespuestaPayload.builder()
                .resultadoValidacion(true)
                .idRespuesta(0L)
                .descripcionRespuesta("Servicio consumido de forma exitosa")
                .build();
        ResponseEntity<RespuestaPayload> respuestaPayload = new ResponseEntity<>(respuesta, HttpStatus.OK);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getStatusCodeValue(), respuestaPayload.getStatusCodeValue());
        //TODO John deberia corregir esto
        //Assert.assertEquals(result.getBody(), respuestaPayload.getBody());
    }

}
