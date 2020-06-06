package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.backend.web.service.GuardarTransaccionCertificadoService;
import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
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
import java.util.Optional;

@SpringBootTest
public class GuardarTransaccionCertificadoServiceTest {

    private GuardarTransaccionCertificadoService guardarTransaccionCertificadoService;
    @Mock
    private InfoWhatsAppWSClient infoWhatsAppWSClient;

    private String token;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        token = "123123123123";
        guardarTransaccionCertificadoService = new GuardarTransaccionCertificadoServiceImpl(infoWhatsAppWSClient);
    }

    @Test
    public void testGenerarCertificadoSUCCESS() throws GeneralSecurityException {
        Date date = new Date();
        CertificadoPayload certificadoPayload = CertificadoPayload.builder()
                .identificacion("1234")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU").build();
        InfoWhatsAppWSPayload infoWhatsAppWSPayload = InfoWhatsAppWSPayload.builder()
                .estado(0L)
                .fechaEnvio(date)
                .numCreditoBanco(SecurityUtilities.desencriptar(certificadoPayload.getNumeroCredito()))
                .numeroIdentificacion(certificadoPayload.getIdentificacion())
                .numPeticionServicio(3L)
                .build();
        Mockito.when(infoWhatsAppWSClient.save(token, infoWhatsAppWSPayload)).thenReturn(new ResponseEntity<>(infoWhatsAppWSPayload, HttpStatus.OK));
        Optional<ResponsePayload> result = guardarTransaccionCertificadoService.generarCertificado(token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificadoPayload, date, 3L);
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarCertificadoErrorConsultarInformacion() throws GeneralSecurityException {
        Date date = new Date();
        CertificadoPayload certificadoPayload = CertificadoPayload.builder()
                .identificacion("1234")
                .numeroCredito("kcZsJENvAG0jcwpr5cqdQIYfYdOXHLTU").build();
        InfoWhatsAppWSPayload infoWhatsAppWSPayload = InfoWhatsAppWSPayload.builder()
                .estado(0L)
                .fechaEnvio(date)
                .numCreditoBanco(SecurityUtilities.desencriptar(certificadoPayload.getNumeroCredito()))
                .numeroIdentificacion(certificadoPayload.getIdentificacion())
                .numPeticionServicio(3L)
                .build();
        Mockito.when(infoWhatsAppWSClient.save(token, infoWhatsAppWSPayload))
                .thenReturn(new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
        Optional<ResponsePayload> result = guardarTransaccionCertificadoService.generarCertificado(token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificadoPayload, date, 3L);
    }

    @Test(expected = GeneralSecurityException.class)
    public void testGenerarCertificadoDesencriptar() throws GeneralSecurityException {
        Date date = new Date();
        CertificadoPayload certificadoPayload = CertificadoPayload.builder()
                .identificacion("1234")
                .numeroCredito("121112").build();
        InfoWhatsAppWSPayload infoWhatsAppWSPayload = InfoWhatsAppWSPayload.builder()
                .estado(0L)
                .fechaEnvio(date)
                .numCreditoBanco(SecurityUtilities.desencriptar(certificadoPayload.getNumeroCredito()))
                .numeroIdentificacion(certificadoPayload.getIdentificacion())
                .numPeticionServicio(3L)
                .build();
        Mockito.when(infoWhatsAppWSClient.save(token, infoWhatsAppWSPayload))
                .thenReturn(new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
        Optional<ResponsePayload> result = guardarTransaccionCertificadoService.generarCertificado(token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, certificadoPayload, date, 3L);
    }
}
