package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosServiceClient;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.ValidateProcessAfterService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosServicioPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStatusAfterProcess;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.GeneralSecurityException;
import java.util.Date;

@SpringBootTest
public class ValidateProcessAfterServiceImplTest {

    @Mock
    private ClienteClient clienteClient;
    @Mock
    private InfoWhatsAppWSClient infoWhatsAppWSClient;
    @Mock
    private ParametrosServiceClient parametrosServiceClient;

    private ValidateProcessAfterService validateProcessAfterService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        validateProcessAfterService = new ValidateProcessAfterServiceImpl(clienteClient, infoWhatsAppWSClient, parametrosServiceClient);
    }

    @Test
    public void testValidateExistingAfterProcessNOT_FOUND_CLIENT(){
        String token = "1212122";
        Date date = new Date();
        CertificadoPayload certificado = CertificadoPayload.builder()
                .identificacion("12345")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU")
                .build();
        Object[] args = {token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificado,  date, 1L};

        ResponseEntity<ClienteViewPayload> responseMockClient = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Mockito.doReturn(responseMockClient).when(clienteClient).getClientByCedulaAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());
        Boolean response = validateProcessAfterService.validateExistingAfterProcess(args);

        Assert.assertNotNull(response);
        Assert.assertFalse(response);
    }

    @Test
    public void testValidateExistingAfterProcessNOT_INFO_WS(){
        String token = "1212122";
        Date date = new Date();
        CertificadoPayload certificado = CertificadoPayload.builder()
                .identificacion("12345")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU")
                .build();
        Object[] args = {token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificado,  date, 1L};

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

        ResponseEntity<ClienteViewPayload> responseMockClient = new ResponseEntity<>(respuesta, HttpStatus.OK);
        Mockito.doReturn(responseMockClient).when(clienteClient).getClientByCedulaAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());



        ResponseEntity<InfoWhatsAppWSPayload> responseMock = new ResponseEntity<InfoWhatsAppWSPayload>(HttpStatus.NO_CONTENT);
        Mockito.doReturn(responseMock).when(infoWhatsAppWSClient).validateExistingProcess(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());


        Boolean response = validateProcessAfterService.validateExistingAfterProcess(args);

        Assert.assertNotNull(response);
        Assert.assertTrue(response);
    }

    @Test
    public void testValidateExistingAfterProcessEXCEPTION_INFO_WS(){
        String token = "1212122";
        Date date = new Date();
        CertificadoPayload certificado = CertificadoPayload.builder()
                .identificacion("12345")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU")
                .build();
        Object[] args = {token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificado,  date, 1L};

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

        ResponseEntity<ClienteViewPayload> responseMockClient = new ResponseEntity<>(respuesta, HttpStatus.OK);
        Mockito.doReturn(responseMockClient).when(clienteClient).getClientByCedulaAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());



        ResponseEntity<InfoWhatsAppWSPayload> responseMock = new ResponseEntity<InfoWhatsAppWSPayload>(HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.doReturn(responseMock).when(infoWhatsAppWSClient).validateExistingProcess(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());


        Boolean response = validateProcessAfterService.validateExistingAfterProcess(args);

        Assert.assertNotNull(response);
        Assert.assertFalse(response);
    }

    @Test
    public void testValidateExistingAfterProcessINFO_WS_ZERO()throws GeneralSecurityException{
        String token = "1212122";
        Date date = new Date();
        CertificadoPayload certificado = CertificadoPayload.builder()
                .identificacion("12345")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU")
                .build();
        Object[] args = {token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificado,  date, 1L};

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

        ResponseEntity<ClienteViewPayload> responseMockClient = new ResponseEntity<>(respuesta, HttpStatus.OK);
        Mockito.doReturn(responseMockClient).when(clienteClient).getClientByCedulaAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());


        InfoWhatsAppWSPayload infoWhatsAppWSPayload = InfoWhatsAppWSPayload.builder()
                .numPeticionServicio(1L)
                .numeroIdentificacion(certificado.getIdentificacion())
                .numCreditoBanco(SecurityUtilities.desencriptar(certificado.getNumeroCredito()))
                .fechaEnvio(date)
                .estado(0L)
                .build();
        ResponseEntity<InfoWhatsAppWSPayload> responseMock = new ResponseEntity<InfoWhatsAppWSPayload>(infoWhatsAppWSPayload, HttpStatus.OK);
        Mockito.doReturn(responseMock).when(infoWhatsAppWSClient).validateExistingProcess(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());


        Boolean response = validateProcessAfterService.validateExistingAfterProcess(args);

        Assert.assertNotNull(response);
        Assert.assertTrue(response);
    }

    @Test(expected = ValidateStatusAfterProcess.class)
    public void testValidateExistingAfterProcessEXCEPTION_TIME()throws GeneralSecurityException{
        String token = "1212122";
        Date date = new Date();
        CertificadoPayload certificado = CertificadoPayload.builder()
                .identificacion("12345")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU")
                .build();
        Object[] args = {token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificado,  date, 1L};

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

        ResponseEntity<ClienteViewPayload> responseMockClient = new ResponseEntity<>(respuesta, HttpStatus.OK);
        Mockito.doReturn(responseMockClient).when(clienteClient).getClientByCedulaAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());


        InfoWhatsAppWSPayload infoWhatsAppWSPayload = InfoWhatsAppWSPayload.builder()
                .numPeticionServicio(1L)
                .numeroIdentificacion(certificado.getIdentificacion())
                .numCreditoBanco(SecurityUtilities.desencriptar(certificado.getNumeroCredito()))
                .fechaEnvio(date)
                .estado(1L)
                .build();
        ResponseEntity<InfoWhatsAppWSPayload> responseMock = new ResponseEntity<InfoWhatsAppWSPayload>(infoWhatsAppWSPayload, HttpStatus.OK);
        Mockito.doReturn(responseMock).when(infoWhatsAppWSClient).validateExistingProcess(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<ParametrosServicioPayload> responseParamMock = new ResponseEntity<ParametrosServicioPayload>(ParametrosServicioPayload.builder()
                .id(1L)
                .numeroIntentos(1)
                .tiempoIntentos(1)
                .tiempoPosterior(100)
                .build() ,HttpStatus.OK);
        Mockito.doReturn(responseParamMock).when(parametrosServiceClient).getParametroService(Mockito.any(), Mockito.any());

        Boolean response = validateProcessAfterService.validateExistingAfterProcess(args);

        Assert.assertNotNull(response);
        Assert.assertTrue(response);
    }

}