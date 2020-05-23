package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ConsultaExtractoService;
import co.com.santander.chatbot.backend.web.service.EnvioExtractoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.enviarextracto.response.ResponseExtractosDisponibles;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@SpringBootTest
public class EnvioExtractoControllerTest {

    private EnvioExtractoController envioExtractoController;
    @Mock
    private ConsultaExtractoService consultaExtractoService;
    @Mock
    private EnvioExtractoService envioExtractoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        envioExtractoController = new EnvioExtractoController(consultaExtractoService, envioExtractoService);
    }

    @Test
    public void testGenerateExtract() {
        String token = "1";
        EnvioExtractoPayload envioExtractoPayload = EnvioExtractoPayload.builder()
                .telefono("3105235467")
                .numeroCreditoOfuscado("XXXXXXXX02123")
                .numeroVerificador("kjfaitufdsjlvjfñlg")
                .mes(7)
                .vigencia(2020)
                .build();

        Optional<ResponseEnvioExtractoPayload> responseMocito = Optional.of(ResponseEnvioExtractoPayload
                .builder()
                .descripcionRespuesta("Servicio consumido de forma correcta")
                .resultadoEnvio(true)
                .emailOfuscado("XXXXX.maya@santander.co")
                .tipoCredito(1)
                .convenio("JEEP")
                .idRespuesta(0)
                .build());
        Mockito.doReturn(responseMocito).when(envioExtractoService).envioExtracto(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        ResponseEntity<ResponseEnvioExtractoPayload> response = envioExtractoController.generateExtract(token, envioExtractoPayload);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void testGenerateExtractSUCCESS(){
        String token = "1";
        EnvioExtractoPayload envioExtractoPayload = EnvioExtractoPayload.builder()
                .telefono("3105235467")
                .numeroCreditoOfuscado("XXXXXXXX02123")
                .numeroVerificador("kjfaitufdsjlvjfñlg")
                .mes(7)
                .vigencia(2020)
                .build();
        Optional<ResponseEnvioExtractoPayload> respuestaEnvio = Optional.empty();

        Mockito.doReturn(respuestaEnvio).when(envioExtractoService).envioExtracto(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<ResponseEnvioExtractoPayload> response = envioExtractoController.generateExtract(token, envioExtractoPayload);
        Assert.assertNotNull(response);
    }

    @Test
    public void testConsultaMesesDisponiblesSUCCESS(){
        String token = "1";
        EnvioExtractoPayload envioExtractoPayload = EnvioExtractoPayload.builder()
                .telefono("3105235467")
                .numeroCreditoOfuscado("XXXXXXXX02123")
                .numeroVerificador("kjfaitufdsjlvjfñlg")
                .mes(7)
                .vigencia(2020)
                .build();
        Optional<ResponseExtractosDisponibles> respuestaEnvio = Optional.empty();

        Mockito.doReturn(respuestaEnvio).when(consultaExtractoService).consultaDocumentos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<ResponseExtractosDisponibles> response = envioExtractoController.consultaMesesDisponibles(token, envioExtractoPayload);
        Assert.assertNotNull(response);
    }

}