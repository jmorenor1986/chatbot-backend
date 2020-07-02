package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.ClienteMapperService;
import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.CreditosUsuarioPayload;
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
        Mockito.doReturn(new ResponseEntity<>(ResponsePayload.builder().build(), HttpStatus.OK)).when(clienteClient).conusltarCliente(token,clientePayload);

        ResponseEntity<ResponsePayload> result = clienteService.validarCliente(token, ServiciosEnum.SERVICIO_VALIDA_CLIENTE, clientePayload.getTelefono() , clientePayload );
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testObtenerCreditosSUCCESS(){
        String token = "1";
        String telefono = "3005632010";
        List<ClienteViewPayload> lista = new ArrayList<>();
        ClienteViewPayload item = ClienteViewPayload.builder()
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
                .tipoCredito(TipoCredito.VEHICULO)
                .build();
        lista.add(item);
        CreditosUsuarioPayload credito = CreditosUsuarioPayload.builder()
                .telefono("3229032614")
                .tipoOperacion(1L)
                .build();
        ResponseEntity<List<ClienteViewPayload>> respuestaMock = new ResponseEntity<>(lista,HttpStatus.OK);
        Mockito.doReturn(respuestaMock).when(clienteClient).getClientsByTel(Mockito.any(), Mockito.any());

        Optional<ResponseObtenerCreditosPayload> respuesta = clienteService.obtenerCreditos(token,ServiciosEnum.SERVICIO_OBTENER_CREDITOS, telefono, credito);
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.isPresent());
    }

    @Test
    public void testObtenerCreditosFAILED(){
        String token = "1";
        String telefono = "3005632010";
        CreditosUsuarioPayload credito = CreditosUsuarioPayload.builder()
                .telefono("3229032614")
                .tipoOperacion(1L)
                .build();

        ResponseEntity<List<ClienteViewPayload>> respuestaMock = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Mockito.when(clienteClient.getClientsByTel(token, telefono)).thenReturn(respuestaMock);
        Optional<ResponseObtenerCreditosPayload> respuesta = clienteService.obtenerCreditos(token, ServiciosEnum.SERVICIO_OBTENER_CREDITOS,telefono, credito);
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.isPresent());
        Assert.assertEquals("No existe informacion", respuesta.get().getDescripcionRespuesta() );
    }

    @Test
    public void testObtenerCreditosFAILED_DIFERENT_CLIENT_SAME_TEL(){
        String token = "1";
        String telefono = "3005632010";
        CreditosUsuarioPayload credito = CreditosUsuarioPayload.builder()
                .telefono("3229032614")
                .tipoOperacion(1L)
                .build();

        List<ClienteViewPayload> lista = new ArrayList<>();
        ClienteViewPayload item = ClienteViewPayload.builder()
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
        Optional<ResponseObtenerCreditosPayload> respuesta = clienteService.obtenerCreditos(token, ServiciosEnum.SERVICIO_VALIDA_CLIENTE, telefono, credito);
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.isPresent());
        Assert.assertEquals(Boolean.FALSE, respuesta.get().getResultado());
        Assert.assertEquals("Numero de telefono asociado a dos clientes", respuesta.get().getDescripcionRespuesta());
    }
}