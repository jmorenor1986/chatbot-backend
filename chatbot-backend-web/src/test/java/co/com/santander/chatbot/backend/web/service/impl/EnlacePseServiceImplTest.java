package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.PseParamClient;
import co.com.santander.chatbot.backend.web.service.EnlacePseService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.PseParamPayload;
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
    @Mock
    private PseParamClient pseParamClient;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.enlacePseService = new EnlacePseServiceImpl(clienteClient, pseParamClient);
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
                .tipoCredito(TipoCredito.CONSUMO)
                .valorPagar(100000L)
                .build();

        ResponseEntity<ClienteViewPayload> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        Mockito.when( clienteClient.getClientByTelefonoAndNumCredito(Mockito.any(),Mockito.any(), Mockito.any()) ).thenReturn(response);

        ResponseEntity<PseParamPayload> responseMock = new ResponseEntity<>(PseParamPayload.builder()
                .id(1L)
                .idBanco(9000L)
                .tipoCredito(2L)
                .url("https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328")
                .build(), HttpStatus.OK);
        Mockito.doReturn(responseMock).when(pseParamClient).getByIdBancoAndTipoCredito(Mockito.any(), Mockito.any(), Mockito.any());
        Optional<ResponseEnlacePsePayload> respuestaServicio =  enlacePseService.getEnlacePse(token, ServiciosEnum.SERVICIO_ENLACE_PSE,telefono,numCreditoEnc);
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

        Optional<ResponseEnlacePsePayload> respuestaServicio =  enlacePseService.getEnlacePse(token,ServiciosEnum.SERVICIO_ENLACE_PSE, telefono,numCreditoEnc);
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
                .tipoCredito(TipoCredito.CONSUMO)
                .valorPagar(700000L)
                .build();

        ResponseEntity<ClienteViewPayload> response = new ResponseEntity<>(respuesta, HttpStatus.OK);

        Mockito.when( clienteClient.getClientByTelefonoAndNumCredito(token,telefono, numCredito) ).thenReturn(response);
        ResponseEntity<PseParamPayload> responseMock = new ResponseEntity<>(PseParamPayload.builder()
                .id(1L)
                .idBanco(9001L)
                .tipoCredito(2L)
                .url("https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328")
                .build(), HttpStatus.OK);
        Mockito.doReturn(responseMock).when(pseParamClient).getByIdBancoAndTipoCredito(Mockito.any(), Mockito.any(), Mockito.any());

        Optional<ResponseEnlacePsePayload> respuestaServicio =  enlacePseService.getEnlacePse(token, ServiciosEnum.SERVICIO_ENLACE_PSE, telefono,numCreditoEnc);
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

        Optional<ResponseEnlacePsePayload> respuestaServicio =  enlacePseService.getEnlacePse(token, ServiciosEnum.SERVICIO_ENLACE_PSE, telefono,numCreditoEnc);
        Assert.assertNotNull(respuestaServicio);
        Assert.assertTrue(respuestaServicio.isPresent());
        Assert.assertNotNull(respuestaServicio.get());
    }
}