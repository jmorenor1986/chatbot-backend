package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class EnvioExtractoControllerTest {

    private EnvioExtractoController envioExtractoController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        envioExtractoController = new EnvioExtractoController();
    }

    @Test
    public void testGenerateExtract(){
        String token = "1";
        EnvioExtractoPayload envioExtractoPayload = EnvioExtractoPayload.builder()
                .telefono("3105235467")
                .numeroCreditoOfuscado("XXXXXXXX02123")
                .numeroVerificador("kjfaitufdsjlvjfñlg")
                .mes(7)
                .vigencia(2020)
                .build();

        ResponseEntity<ResponseEnvioExtractoPayload> response = envioExtractoController.generateExtract(token, envioExtractoPayload);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());



    }

}