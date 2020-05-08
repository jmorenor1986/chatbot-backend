package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosServiceClient;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.ValidarProcesoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
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
public class ValidarProcesoServiceImplTest {
    @Mock
    private InfoWhatsAppWSClient infoWhatsAppWSClient;
    @Mock
    private ParametrosServiceClient parametrosServiceClient;

    private ValidarProcesoService validarProcesoService;
    @Mock
    private ClienteClient clienteClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validarProcesoService = new ValidarProcesoServiceImpl(infoWhatsAppWSClient, parametrosServiceClient, clienteClient);
    }

    @Test
    public void testValidateExistingProcesss() throws GeneralSecurityException {
        Date date = new Date();
        String token = "1212122";
        CertificadoPayload certificado = CertificadoPayload.builder()
                .identificacion("12345")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU")
                .build();

        InfoWhatsAppWSPayload infoWhatsAppWSPayload = InfoWhatsAppWSPayload.builder()
                .numPeticionServicio(1L)
                .numeroIdentificacion(certificado.getIdentificacion())
                .numCreditoBanco(SecurityUtilities.desencriptar(certificado.getNumeroCredito()))
                .fechaEnvio(date)
                .estado(1L)
                .build();
        Object[] args = {token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificado,  new Date(), 1L};
        Mockito.when(infoWhatsAppWSClient.validateExistingProcess((String) args[0], SecurityUtilities.desencriptar(certificado.getNumeroCredito()),
                certificado.getIdentificacion(), 1L)).thenReturn(new ResponseEntity<>(infoWhatsAppWSPayload, HttpStatus.OK));
        Mockito.when(parametrosServiceClient.consultarProcesoParametros(token, ValidarProcesoPayload.builder()
                .canal("WhatsApp")
                .servicio(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage())
                .fechaUltimaSolicitud(date)
                .build())).thenReturn(new ResponseEntity<>(ResponsePayload.builder()
                .resultadoValidacion(Boolean.TRUE)
                .descripcionRespuesta("test")
                .build(), HttpStatus.OK));

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
        ResponseEntity<ClienteViewPayload> response = new ResponseEntity<>(respuesta,HttpStatus.OK);

        Mockito.doReturn(response).when(clienteClient).getClientByCedulaAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());
        Boolean result = validarProcesoService.validateExistingProcesss(args);
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testValidateExistingError() throws GeneralSecurityException {
        Date date = new Date();
        String token = "1212122";
        CertificadoPayload certificado = CertificadoPayload.builder()
                .identificacion("12345")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU")
                .build();

        InfoWhatsAppWSPayload infoWhatsAppWSPayload = InfoWhatsAppWSPayload.builder()
                .numPeticionServicio(1L)
                .numeroIdentificacion(certificado.getIdentificacion())
                .numCreditoBanco(SecurityUtilities.desencriptar(certificado.getNumeroCredito()))
                .fechaEnvio(date)
                .estado(1L)
                .build();
        Object[] args = {token, certificado, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, new Date(), 1L};
        Mockito.when(infoWhatsAppWSClient.validateExistingProcess((String) args[0], SecurityUtilities.desencriptar(certificado.getNumeroCredito()),
                certificado.getIdentificacion(), 1L)).thenReturn(new ResponseEntity<>(infoWhatsAppWSPayload, HttpStatus.INTERNAL_SERVER_ERROR));
        Mockito.when(parametrosServiceClient.consultarProcesoParametros(token, ValidarProcesoPayload.builder()
                .canal("WhatsApp")
                .servicio(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage())
                .fechaUltimaSolicitud(date)
                .build())).thenReturn(new ResponseEntity<>(ResponsePayload.builder()
                .resultadoValidacion(Boolean.TRUE)
                .descripcionRespuesta("test")
                .build(), HttpStatus.OK));
        Boolean result = Boolean.FALSE;
        try {
            result = validarProcesoService.validateExistingProcesss(args);
        } catch (Exception e) {
        }
        Assert.assertEquals(Boolean.FALSE, result);
    }


    @Test
    public void testValidateExistingNO_CONTENT() throws GeneralSecurityException {
        Date date = new Date();
        String token = "1212122";
        CertificadoPayload certificado = CertificadoPayload.builder()
                .identificacion("12345")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU")
                .build();

        InfoWhatsAppWSPayload infoWhatsAppWSPayload = InfoWhatsAppWSPayload.builder()
                .numPeticionServicio(1L)
                .numeroIdentificacion(certificado.getIdentificacion())
                .numCreditoBanco(SecurityUtilities.desencriptar(certificado.getNumeroCredito()))
                .fechaEnvio(date)
                .estado(1L)
                .build();
        Object[] args = {token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificado,  new Date(), 1L};
        Mockito.when(infoWhatsAppWSClient.validateExistingProcess((String) args[0], SecurityUtilities.desencriptar(certificado.getNumeroCredito()),
                certificado.getIdentificacion(), 1L)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        Mockito.when(parametrosServiceClient.consultarProcesoParametros(token, ValidarProcesoPayload.builder()
                .canal("WhatsApp")
                .servicio(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage())
                .fechaUltimaSolicitud(date)
                .build())).thenReturn(new ResponseEntity<>(ResponsePayload.builder()
                .resultadoValidacion(Boolean.TRUE)
                .descripcionRespuesta("test")
                .build(), HttpStatus.OK));
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
        ResponseEntity<ClienteViewPayload> response = new ResponseEntity<>(respuesta,HttpStatus.OK);

        Mockito.doReturn(response).when(clienteClient).getClientByCedulaAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());

        Boolean result = validarProcesoService.validateExistingProcesss(args);
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testValidateExisting_validate_EXCEPTION() throws GeneralSecurityException {
        Date date = new Date();
        String token = "1212122";
        CertificadoPayload certificado = CertificadoPayload.builder()
                .identificacion("12345")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU")
                .build();

        InfoWhatsAppWSPayload infoWhatsAppWSPayload = InfoWhatsAppWSPayload.builder()
                .numPeticionServicio(1L)
                .numeroIdentificacion(certificado.getIdentificacion())
                .numCreditoBanco(SecurityUtilities.desencriptar(certificado.getNumeroCredito()))
                .fechaEnvio(date)
                .estado(0L)
                .build();
        Object[] args = {token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificado,  new Date(), 1L};
        Mockito.when(infoWhatsAppWSClient.validateExistingProcess((String) args[0], SecurityUtilities.desencriptar(certificado.getNumeroCredito()),
                certificado.getIdentificacion(), 1L)).thenReturn(new ResponseEntity<>(infoWhatsAppWSPayload, HttpStatus.INTERNAL_SERVER_ERROR));
        Mockito.when(parametrosServiceClient.consultarProcesoParametros(token, ValidarProcesoPayload.builder()
                .canal("WhatsApp")
                .servicio(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage())
                .fechaUltimaSolicitud(date)
                .build())).thenReturn(new ResponseEntity<>(ResponsePayload.builder()
                .resultadoValidacion(Boolean.TRUE)
                .descripcionRespuesta("test")
                .build(), HttpStatus.OK));

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
        ResponseEntity<ClienteViewPayload> response = new ResponseEntity<>(respuesta,HttpStatus.OK);

        Mockito.doReturn(response).when(clienteClient).getClientByCedulaAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());

        Boolean result = validarProcesoService.validateExistingProcesss(args);
    }
}