package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.domain.payload.service.obtenercreditos.CreditosUsuarioPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ObtenerCreditosUsuarioControllerTest {

    private ObtenerCreditosUsuarioController obtenerCreditosUsuarioController;
    @Before
    public void setUp(){
        obtenerCreditosUsuarioController = new ObtenerCreditosUsuarioController();
    }

    @Test
    public void testObtenerSUCCESS(){
        String token = "1";
        CreditosUsuarioPayload credito = CreditosUsuarioPayload.builder()
                .telefono("telefono")
                .tipoOperacion(Long.valueOf("0"))
                .build();

        ResponseEntity<ResponseObtenerCreditosPayload> response = obtenerCreditosUsuarioController.obtener(token, credito);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

}