package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
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
public class ValidarClienteControllerTest {

    private ValidarClienteController validarClienteController;
    @Mock
    private ClienteService clienteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validarClienteController = new ValidarClienteController(clienteService);
    }

    @Test
    public void testValidarUsuarioSuccess() {
        String token = "1";
        ClientePayload clientePayload = ClientePayload.builder()
                .cedula("1")
                .telefono("2")
                .build();
        Mockito.when(clienteService.validarCliente(token, ServiciosEnum.SERVICIO_VALIDA_CLIENTE, clientePayload.getTelefono(), clientePayload )).thenReturn(new ResponseEntity<>(ResponsePayload.builder().build(), HttpStatus.OK));
        ResponseEntity<ResponsePayload> result = validarClienteController.validar(token, clientePayload);
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testValidarUsuarioNO_CONTENT() {
        String token = "1";
        ClientePayload clientePayload = ClientePayload.builder()
                .cedula("1")
                .telefono("2")
                .build();
        Mockito.when(clienteService.validarCliente(token, ServiciosEnum.SERVICIO_VALIDA_CLIENTE, clientePayload.getTelefono(), clientePayload )).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        ResponseEntity<ResponsePayload> result = validarClienteController.validar(token, clientePayload);
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

}
