package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ClienteServiceImplTest {

    private ClienteService clienteService;
    @Mock
    private ClienteClient clienteClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clienteService = new ClienteServiceImpl(clienteClient);
    }

    @Test
    public void testConsultarClienteSuccess() {
        String token = "1";
        ClientePayload clientePayload = ClientePayload.builder()
                .cedula("1")
                .telefono("2")
                .build();
        Mockito.when(clienteClient.conusltarCliente(token, clientePayload)).thenReturn(new ResponseEntity<>(ResponsePayload.builder().build(), HttpStatus.OK));
        ResponseEntity<ResponsePayload> result = clienteService.validarCliente(clientePayload, token);
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

}