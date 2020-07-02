package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.Cliente;
import co.com.santander.chatbot.accesodatos.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClienteControllerTest {

    private ClienteController clienteController;
    private ModelMapper mapper;
    @Mock
    private ClienteService clienteService;
    private ClientePayload clientePayload;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        clienteController = new ClienteController(clienteService, mapper);
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
        Assert.assertEquals(Boolean.FALSE, result.getBody().getResultado());
        Assert.assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    public void testControllerInternalServerError() {
        Mockito.when(clienteService.consultarCliente("12345", "5270")).thenReturn(Optional.empty());
        ResponseEntity<ResponsePayload> result = clienteController.consultaCliente(clientePayload);

        Assert.assertNotNull(result);
        Assert.assertEquals(500, result.getStatusCodeValue());
    }

    @Test
    public void testGetClientsByTelSUCCES() {
        String telefono = "3005632010";
        List<Cliente> listClients = new ArrayList<>();
        Cliente item = Cliente.builder()
                .nombreCliente("LOPEZ LOPEZ LUIS EMILIO")
                .telefono("3005632010")
                .cedula("56789066")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("6000000456")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("52")
                .convenio("MARCALI INTERNACIONAL SA")
                .build();
        listClients.add(item);
        Optional<List<Cliente>> result = Optional.of(listClients);
        Mockito.when(clienteService.consultarClienteByTelefono(telefono)).thenReturn(result);
        ResponseEntity<List<ClienteViewPayload>> response = clienteController.getClientsByTel(telefono);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetClientsByTelFAILED() {
        String telefono = "3005632010";
        Optional<List<Cliente>> result = Optional.empty();
        Mockito.when(clienteService.consultarClienteByTelefono(telefono)).thenReturn(result);
        ResponseEntity<List<ClienteViewPayload>> response = clienteController.getClientsByTel(telefono);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetClientByTelefonoAndNumCredito(){
        String telefono = "3005632010";
        String numCredito = "123456789";

        Cliente item = Cliente.builder()
                .nombreCliente("LOPEZ LOPEZ LUIS EMILIO")
                .telefono("3005632010")
                .numerCredito("123456789")
                .cedula("56789066")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("6000000456")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("52")
                .convenio("MARCALI INTERNACIONAL SA")
                .build();

        Mockito.doReturn(Optional.of(item)).when(clienteService).consultarClienteByTelefonoAndNumCredito(Mockito.any(), Mockito.any());

        ResponseEntity<ClienteViewPayload> response = clienteController.getClientByTelefonoAndNumCredito(telefono,numCredito);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());

    }

    @Test
    public void testGetClientByTelefonoAndNumCreditoNO_CONTENT(){
        String telefono = "3005632010";
        String numCredito = "123456789";

        Mockito.when( clienteService.consultarClienteByTelefonoAndNumCredito(telefono,numCredito) ).thenReturn(Optional.empty());
        ResponseEntity<ClienteViewPayload> response = clienteController.getClientByTelefonoAndNumCredito(telefono,numCredito);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


}
