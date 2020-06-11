package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.ValidateClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.validators.exceptions.NonExistentCustomerException;
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

@SpringBootTest
public class ValidateClienteServiceImplTest {

    private ValidateClienteService validateClienteService;
    @Mock
    private ClienteClient clienteClient;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.validateClienteService = new ValidateClienteServiceImpl(clienteClient);
    }

    @Test
    public void testCallServiceSUCCESS(){
        List<ClienteViewPayload> listResponse = new ArrayList<>();
        listResponse.add(ClienteViewPayload.builder().build());

        ResponseEntity<List<ClienteViewPayload>> response = new ResponseEntity<>(listResponse ,HttpStatus.OK);

        Mockito.doReturn(response).when(clienteClient).getClientsByTel(Mockito.any(), Mockito.any());

        validateClienteService.validateClient("123", "3229062514");
        Assert.assertTrue(true);

    }

    @Test(expected = NonExistentCustomerException.class)
    public void testCallServiceFAILED(){
        ResponseEntity<List<ClienteViewPayload>> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Mockito.doReturn(response).when(clienteClient).getClientsByTel(Mockito.any(), Mockito.any());

        validateClienteService.validateClient("123", "3229062514");
    }

}