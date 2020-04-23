package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.EnlacePseService;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class EnlacePseServiceImplTest {

    private EnlacePseService enlacePseService;
    @Mock
    private ClienteClient clienteClient;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.enlacePseService = new EnlacePseServiceImpl(clienteClient);
    }

    @Test
    public void testGetEnlacePseSUCCESS(){
        String token = "1";
        String telefono = "3005632015";
        String numCredito = "6000000461";
        String numCreditoEnc = "lsRvIEZpB2EvIOeT7NMtoDPhlF0sTCHtCug=";
        ClienteViewPayload respuesta = ClienteViewPayload.builder()
                .id(Long.valueOf("6"))
                .nombreCliente("OUTEIRO LAMAS FERNANDO")
                .telefono("3005632015")
                .cedula("19977690")
                .email("alfredoparra67@hotmailcom")
                .numerCredito("6000000461")
                .banco("SANTANDER CONSUMER")
                .estado("Al dia")
                .idProducto("1")
                .idBanco("9000")
                .convenio("LOS COCHES F SAS")
                .build();

        ResponseEntity<ClienteViewPayload> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        Mockito.when( clienteClient.getClientByTelefonoAndNumCredito(token,telefono, numCredito) ).thenReturn(response);

        Optional<ResponseEnlacePsePayload> respuestaServicio =  enlacePseService.getEnlacePse(token,telefono,numCreditoEnc);
        Assert.assertNotNull(respuestaServicio);
        Assert.assertTrue(respuestaServicio.isPresent());
        Assert.assertNotNull(respuestaServicio.get());

    }

    @Test
    public void testGetEnlacePseFAILED_DES_ENCRIPT(){
        String token = "1";
        String telefono = "3005632015";
        String numCredito = "6000000461";
        String numCreditoEnc = "lsRvIEZpB2EvIOeT";
        ResponseEntity<ClienteViewPayload> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Mockito.when( clienteClient.getClientByTelefonoAndNumCredito(token,telefono, "") ).thenReturn(response);

        Optional<ResponseEnlacePsePayload> respuestaServicio =  enlacePseService.getEnlacePse(token,telefono,numCreditoEnc);
        Assert.assertNotNull(respuestaServicio);
        Assert.assertTrue(respuestaServicio.isPresent());
        Assert.assertNotNull(respuestaServicio.get());
    }

    @Test
    public void testGetEnlacePseLINK_NOT_PRESENT(){
        String token = "1";
        String telefono = "3005632015";
        String numCredito = "6000000461";
        String numCreditoEnc = "lsRvIEZpB2EvIOeT7NMtoDPhlF0sTCHtCug=";
        ClienteViewPayload respuesta = ClienteViewPayload.builder()
                .id(Long.valueOf("6"))
                .nombreCliente("OUTEIRO LAMAS FERNANDO")
                .telefono("3005632015")
                .cedula("19977690")
                .email("alfredoparra67@hotmailcom")
                .numerCredito("6000000461")
                .banco("BANCO SANTANDER DE NEGOCIOS COLOMBIA S.A. - BANCO SANTANDER")
                .estado("Al dia")
                .idProducto("1")
                .idBanco("9000")
                .convenio("LOS COCHES F SAS")
                .build();

        ResponseEntity<ClienteViewPayload> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        Mockito.when( clienteClient.getClientByTelefonoAndNumCredito(token,telefono, numCredito) ).thenReturn(response);

        Optional<ResponseEnlacePsePayload> respuestaServicio =  enlacePseService.getEnlacePse(token,telefono,numCreditoEnc);
        Assert.assertNotNull(respuestaServicio);
        Assert.assertTrue(respuestaServicio.isPresent());
        Assert.assertNotNull(respuestaServicio.get());

    }

    @Test
    public void testGetEnlacePseFAILED(){
        String token = "1";
        String telefono = "3005632015";
        String numCredito = "6000000461";
        String numCreditoEnc = "lsRvIEZpB2EvIOeT";
        ResponseEntity<ClienteViewPayload> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when( clienteClient.getClientByTelefonoAndNumCredito(token,telefono, "") ).thenReturn(response);

        Optional<ResponseEnlacePsePayload> respuestaServicio =  enlacePseService.getEnlacePse(token,telefono,numCreditoEnc);
        Assert.assertNotNull(respuestaServicio);
        Assert.assertTrue(respuestaServicio.isPresent());
        Assert.assertNotNull(respuestaServicio.get());
    }
}