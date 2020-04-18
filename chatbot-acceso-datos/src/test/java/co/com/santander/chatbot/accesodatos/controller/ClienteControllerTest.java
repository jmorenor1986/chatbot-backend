package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.UsuarioInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Optional;

@SpringBootTest
public class ClienteControllerTest {

    private ClienteController clienteController;
    @Mock
    private ClienteService clienteService;
    private UsuarioInput usuarioInput;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clienteController = new ClienteController(clienteService);
        usuarioInput = UsuarioInput.builder()
                .cedula("5270")
                .celular("12345")
                .build();
    }

    @Test
    public void testControllerSuccess() {
        Mockito.when(clienteService.consultarCliente("12345", "5270")).thenReturn(Optional.of(Boolean.TRUE));
        ResponseEntity<Boolean> result = clienteController.consultaCliente(usuarioInput);

        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.TRUE, result.getBody());
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testControllerNotContent() {
        Mockito.when(clienteService.consultarCliente("12345", "5270")).thenReturn(Optional.of(Boolean.FALSE));
        ResponseEntity<Boolean> result = clienteController.consultaCliente(usuarioInput);

        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.FALSE, result.getBody());
        Assert.assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    public void testControllerInternalServerError() {
        Mockito.when(clienteService.consultarCliente("12345", "5270")).thenReturn(Optional.empty());
        ResponseEntity<Boolean> result = clienteController.consultaCliente(usuarioInput);

        Assert.assertNotNull(result);
        Assert.assertEquals(500, result.getStatusCodeValue());
    }

}
