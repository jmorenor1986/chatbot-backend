package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.ClienteMapperService;
import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
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
public class ClienteServiceImplTest {

    private ClienteService clienteService;

    private ClienteMapperService clienteMapper;
    @Mock
    private ClienteClient clienteClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clienteMapper = new ClienteMapperServiceImpl();
        clienteService = new ClienteServiceImpl(clienteClient, clienteMapper);
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

    @Test
    public void testObtenerCreditosSUCCESS(){
        String token = "1";
        String telefono = "3005632010";
        List<ClienteViewPayload> lista = new ArrayList<>();
        ClienteViewPayload item = ClienteViewPayload.builder()
                .id(Long.valueOf("1"))
                .nombreCliente("LOPEZ LOPEZ LUIS EMILIO")
                .telefono("3005632010")
                .cedula("56789066")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("12345678998764531213246")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("52")
                .convenio("MARCALI INTERNACIONAL SA")
                .build();
        lista.add(item);
        ResponseEntity<List<ClienteViewPayload>> respuestaMock = new ResponseEntity<>(lista,HttpStatus.OK);
        Mockito.when(clienteClient.getClientsByTel(token, telefono)).thenReturn(respuestaMock);
        Optional<ResponseObtenerCreditosPayload> respuesta = clienteService.obtenerCreditos(token,telefono);
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.isPresent());
    }

    @Test
    public void testObtenerCreditosFAILED(){
        String token = "1";
        String telefono = "3005632010";

        ResponseEntity<List<ClienteViewPayload>> respuestaMock = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Mockito.when(clienteClient.getClientsByTel(token, telefono)).thenReturn(respuestaMock);
        Optional<ResponseObtenerCreditosPayload> respuesta = clienteService.obtenerCreditos(token,telefono);
        Assert.assertNotNull(respuesta);
        Assert.assertFalse(respuesta.isPresent());
    }

    @Test
    public void testObtenerCreditosFAILED_DIFERENT_CLIENT_SAME_TEL(){
        String token = "1";
        String telefono = "3005632010";

        List<ClienteViewPayload> lista = new ArrayList<>();
        ClienteViewPayload item = ClienteViewPayload.builder()
                .id(Long.valueOf("1"))
                .nombreCliente("LOPEZ LOPEZ LUIS EMILIO")
                .telefono("3005632010")
                .cedula("56789066")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("12345678998764531213246")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("52")
                .convenio("MARCALI INTERNACIONAL SA")
                .build();
        lista.add(item);
        ClienteViewPayload item1 = ClienteViewPayload.builder()
                .id(Long.valueOf("1"))
                .nombreCliente("GOMEZ GARCIA LUISA CLRA")
                .telefono("3005632010")
                .cedula("56789098")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("6000000457")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("200")
                .convenio("SIDA S.A.")
                .build();
        lista.add(item1);

        ResponseEntity<List<ClienteViewPayload>> respuestaMock = new ResponseEntity<>(lista,HttpStatus.OK);
        Mockito.when(clienteClient.getClientsByTel(token, telefono)).thenReturn(respuestaMock);
        Optional<ResponseObtenerCreditosPayload> respuesta = clienteService.obtenerCreditos(token,telefono);
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.isPresent());
        Assert.assertEquals(Boolean.FALSE, respuesta.get().getResultadoConsulta());
        Assert.assertEquals("Numero de telefono asociado a dos clientes", respuesta.get().getDescripcionRespuesta());
    }
}