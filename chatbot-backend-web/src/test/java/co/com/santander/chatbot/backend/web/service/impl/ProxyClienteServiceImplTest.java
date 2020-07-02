package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.CreditosUsuarioPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseCreditosPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProxyClienteServiceImplTest {

    private ClienteService proxyClienteService;
    @Mock
    private ClienteService clienteService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.proxyClienteService  = new ProxyClienteServiceImpl(clienteService);
    }

    @Test
    public void testValidarCliente(){
        ResponseEntity<ResponsePayload> responseMock = new ResponseEntity<>(HttpStatus.OK);
        Mockito.doReturn(responseMock).when(clienteService).validarCliente(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any());

        ResponseEntity<ResponsePayload> response = proxyClienteService.validarCliente("1", ServiciosEnum.SERVICIO_OBTENER_CREDITOS, "123", ClientePayload.builder().build());
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testObtenerCreditosFAILED_EMPTY(){
        Optional<ResponseObtenerCreditosPayload> responseMock = Optional.empty();
        Mockito.doReturn(responseMock).when(clienteService).obtenerCreditos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        Optional<ResponseObtenerCreditosPayload> response = proxyClienteService.obtenerCreditos("1", ServiciosEnum.SERVICIO_OBTENER_CREDITOS, "123", CreditosUsuarioPayload.builder().build());
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isPresent());
    }

    @Test
    public void testObtenerCreditosSUCCESS_WITHOUT_FILTER(){
        List<ResponseCreditosPayload> creditos = new ArrayList<>();
        creditos.add(ResponseCreditosPayload.builder()
                .numeroCreditoOfuscado("XXX10016")
                .numeroVerificador("k81uIUZpBmPTn2XRcwZly3j+iKOO35ZS")
                .tipoCredito(2L)
                .banco(2L)
                .convenio("AUTOMOTORES LA FLORESTA")
                .build());



        Optional<ResponseObtenerCreditosPayload> responseMock = Optional.of(
                ResponseObtenerCreditosPayload.builder()
                        .resultado(Boolean.TRUE)
                        .idRespuesta(0L)
                        .descripcionRespuesta("Servicio consumido de forma exitosa")
                        .creditos(creditos)
                .build()
        );
        Mockito.doReturn(responseMock).when(clienteService).obtenerCreditos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        Optional<ResponseObtenerCreditosPayload> response = proxyClienteService.obtenerCreditos("1", ServiciosEnum.SERVICIO_OBTENER_CREDITOS, "123", CreditosUsuarioPayload.builder().tipoOperacion(0L).build());
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testObtenerCreditosSUCCESS_WITH_FILTER(){
        List<ResponseCreditosPayload> creditos = new ArrayList<>();
        creditos.add(ResponseCreditosPayload.builder()
                .numeroCreditoOfuscado("XXX10016")
                .numeroVerificador("k81uIUZpBmPTn2XRcwZly3j+iKOO35ZS")
                .tipoCredito(2L)
                .banco(2L)
                .convenio("AUTOMOTORES LA FLORESTA")
                .build());
        creditos.add(ResponseCreditosPayload.builder()
                .numeroCreditoOfuscado("3241")
                .numeroVerificador("k8ZrIdGbvyevgCHvD1K+nKdlxIM=")
                .tipoCredito(1L)
                .banco(1L)
                .convenio("CENTRAL MOTOR AMERICA S.A.S.")
                .build());
        creditos.add(ResponseCreditosPayload.builder()
                .numeroCreditoOfuscado("XXXXX01654")
                .numeroVerificador("k81uIUZpBmMsJWTVf+XGr10v1nWhDm1DKDE=")
                .tipoCredito(1L)
                .banco(1L)
                .convenio("LOS COCHES F SAS")
                .build());



        Optional<ResponseObtenerCreditosPayload> responseMock = Optional.of(
                ResponseObtenerCreditosPayload.builder()
                        .resultado(Boolean.TRUE)
                        .idRespuesta(0L)
                        .descripcionRespuesta("Servicio consumido de forma exitosa")
                        .creditos(creditos)
                        .build()
        );
        Mockito.doReturn(responseMock).when(clienteService).obtenerCreditos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        Optional<ResponseObtenerCreditosPayload> response = proxyClienteService.obtenerCreditos("1", ServiciosEnum.SERVICIO_OBTENER_CREDITOS, "123", CreditosUsuarioPayload.builder().tipoOperacion(2L).build());
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testObtenerCreditosSUCCESS_WITHOUT_CREDITS(){
        List<ResponseCreditosPayload> creditos = new ArrayList<>();
        creditos.add(ResponseCreditosPayload.builder()
                .numeroCreditoOfuscado("XXX10016")
                .numeroVerificador("k81uIUZpBmPTn2XRcwZly3j+iKOO35ZS")
                .tipoCredito(2L)
                .banco(2L)
                .convenio("AUTOMOTORES LA FLORESTA")
                .build());

        Optional<ResponseObtenerCreditosPayload> responseMock = Optional.of(
                ResponseObtenerCreditosPayload.builder()
                        .resultado(Boolean.TRUE)
                        .idRespuesta(0L)
                        .descripcionRespuesta("Servicio consumido de forma exitosa")
                        .creditos(creditos)
                        .build()
        );
        Mockito.doReturn(responseMock).when(clienteService).obtenerCreditos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        Optional<ResponseObtenerCreditosPayload> response = proxyClienteService.obtenerCreditos("1", ServiciosEnum.SERVICIO_OBTENER_CREDITOS, "123", CreditosUsuarioPayload.builder().tipoOperacion(2L).build());
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}