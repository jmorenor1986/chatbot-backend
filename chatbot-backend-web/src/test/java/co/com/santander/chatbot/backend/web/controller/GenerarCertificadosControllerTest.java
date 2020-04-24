package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
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
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class GenerarCertificadosControllerTest {
    @Mock
    private GenerarCertificadosService generarCertificadosService;
    private GenerarCertificadosController generarCertificadosController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        generarCertificadosController = new GenerarCertificadosController(generarCertificadosService);
    }

    @Test
    public void testGenerarCertificadoPazYSalvo() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificado(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_PAZ_Y_SALVO), Mockito.eq(CertificadoPayload.builder().build()), Mockito.any(), Mockito.eq(3L))).thenReturn(Optional.of(ResponsePayload.builder().build()));
        ResponseEntity<ResponsePayload> result = generarCertificadosController.generarCertificadoPazYSalvo(token, CertificadoPayload.builder().build());
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarCertificadoPazYSalvoError() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificado(token, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, CertificadoPayload.builder().build(),  date, 3L)).thenReturn(Optional.empty());
        ResponseEntity<ResponsePayload> result = generarCertificadosController.generarCertificadoPazYSalvo(token, CertificadoPayload.builder().build());
    }

    @Test
    public void testGenerarAutorizacionDebito() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificado(Mockito.eq(token),  Mockito.eq(ServiciosEnum.SERVICIO_DEBITO_AUTOMATICO), Mockito.eq(CertificadoPayload.builder().build()), Mockito.any(), Mockito.eq(4L))).thenReturn(Optional.of(ResponsePayload.builder().build()));
        ResponseEntity<ResponsePayload> result = generarCertificadosController.autorizacionDebito(token, CertificadoPayload.builder().build());
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarAutorizacionDebitoError() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificado(token,  ServiciosEnum.SERVICIO_DEBITO_AUTOMATICO,CertificadoPayload.builder().build(), date, 4L)).thenReturn(Optional.empty());
        ResponseEntity<ResponsePayload> result = generarCertificadosController.autorizacionDebito(token, CertificadoPayload.builder().build());
    }

    @Test
    public void testGenerarDeclaracionRenta() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificado(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_DECLARACION_RENTA),  Mockito.eq(CertificadoPayload.builder().build()), Mockito.any(), Mockito.eq(5L))).thenReturn(Optional.of(ResponsePayload.builder().build()));
        ResponseEntity<ResponsePayload> result = generarCertificadosController.certificacionDeclaracionRenta(token, CertificadoPayload.builder().build());
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarDeclaracionRentaError() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificado(token, ServiciosEnum.SERVICIO_DECLARACION_RENTA, CertificadoPayload.builder().build(), date, 5L)).thenReturn(Optional.empty());
        ResponseEntity<ResponsePayload> result = generarCertificadosController.certificacionDeclaracionRenta(token, CertificadoPayload.builder().build());
    }

    @Test
    public void testGenerarInformacionCredito() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificado(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_INFORMACION_CREDITO), Mockito.eq(CertificadoPayload.builder().build()), Mockito.any(), Mockito.eq(1L))).thenReturn(Optional.of(ResponsePayload.builder().build()));
        ResponseEntity<ResponsePayload> result = generarCertificadosController.informacionCredito(token, CertificadoPayload.builder().build());
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarInformacionCreditoError() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificado(token,  ServiciosEnum.SERVICIO_INFORMACION_CREDITO, CertificadoPayload.builder().build(), date, 1L)).thenReturn(Optional.empty());
        ResponseEntity<ResponsePayload> result = generarCertificadosController.informacionCredito(token, CertificadoPayload.builder().build());
    }


}