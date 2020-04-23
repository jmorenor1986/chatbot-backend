package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosServiceClient;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.ValidarProcesoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validarProcesoService = new ValidarProcesoServiceImpl(infoWhatsAppWSClient, parametrosServiceClient);
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
        Object[] args = {token, certificado, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, 1L};
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
        Object[] args = {token, certificado, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, 1L};
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
}