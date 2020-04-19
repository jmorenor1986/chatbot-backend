package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class ClienteControllerTest {

    private ClienteController clienteController;
    @Mock
    private ClienteService clienteService;
    private ClientePayload clientePayload;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clienteController = new ClienteController(clienteService);
        clientePayload = ClientePayload.builder()
                .cedula("5270")
                .telefono("12345")
                .build();
    }

    @Test
    public void testControllerSuccess() {
        Mockito.when(clienteService.consultarCliente("12345", "5270")).thenReturn(Optional.of(Boolean.TRUE));
        ResponseEntity<ResponsePayload> result = clienteController.consultaCliente(clientePayload);

        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.TRUE.toString(), result.getBody().getDescripcionRespuesta());
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testControllerNotContent() {
        Mockito.when(clienteService.consultarCliente("12345", "5270")).thenReturn(Optional.of(Boolean.FALSE));
        ResponseEntity<ResponsePayload> result = clienteController.consultaCliente(clientePayload);

        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.FALSE, result.getBody().getResultadoValidacion());
        Assert.assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    public void testControllerInternalServerError() {
        Mockito.when(clienteService.consultarCliente("12345", "5270")).thenReturn(Optional.empty());
        ResponseEntity<ResponsePayload> result = clienteController.consultaCliente(clientePayload);

        Assert.assertNotNull(result);
        Assert.assertEquals(500, result.getStatusCodeValue());
    }

}
