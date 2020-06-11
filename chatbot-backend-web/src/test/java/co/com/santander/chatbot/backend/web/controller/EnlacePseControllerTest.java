package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.EnlacePseService;
import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.backend.web.service.impl.MapperTelServiceImpl;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.enlacePse.EnlacePsePayload;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


@SpringBootTest
public class EnlacePseControllerTest {

    private EnlacePseController enlacePseController;
    @Mock
    private EnlacePseService enlacePseService;

    private MapperTelService mapperTelService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mapperTelService = new MapperTelServiceImpl();
        enlacePseController = new EnlacePseController(enlacePseService, mapperTelService);

    }

    @Test
    public void testGetEnlacePseSUCCESS() {
        String token = "1";
        EnlacePsePayload request = EnlacePsePayload.builder()
                .telefono("3005632015")
                .numeroCreditoOfuscado("xxxxx00463")
                .numeroVerificador("lsRvIEZpB2EvIOeT7NMtoDPhlF0sTCHtCug=")
                .build();
        ResponseEnlacePsePayload response = ResponseEnlacePsePayload.builder()
                .resultadoOperacion("true")
                .enlace("https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328")
                .idRespuesta("0")
                .descripcionRespuesta("Servicio consumido de forma exitosa")
                .build();
        Mockito.when( enlacePseService.getEnlacePse(token, ServiciosEnum.SERVICIO_ENLACE_PSE, request.getTelefono(), request.getNumeroVerificador()) )
                .thenReturn(Optional.of(response));
        ResponseEntity<ResponseEnlacePsePayload> responseController = enlacePseController.getEnlacePse(token, request);
        Assert.assertNotNull(responseController);
        Assert.assertEquals(HttpStatus.OK, responseController.getStatusCode());
        Assert.assertNotNull(responseController.getBody());
    }

    @Test
    public void testGetEnlacePseFAILED() {
        String token = "1";
        EnlacePsePayload request = EnlacePsePayload.builder()
                .telefono("3005632015")
                .numeroCreditoOfuscado("xxxxx00463")
                .numeroVerificador("lsRvIEZpB2EvIOeT7NMtoDPhlF0sTCHtCug=")
                .build();

        Mockito.when( enlacePseService.getEnlacePse(token, ServiciosEnum.SERVICIO_ENLACE_PSE, request.getTelefono(), request.getNumeroVerificador()) )
                .thenReturn(Optional.empty());
        ResponseEntity<ResponseEnlacePsePayload> responseController = enlacePseController.getEnlacePse(token, request);
        Assert.assertNotNull(responseController);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseController.getStatusCode());
    }
}