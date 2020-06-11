package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.backend.web.service.ProxyInformacionCredito;
import co.com.santander.chatbot.backend.web.service.impl.MapperTelServiceImpl;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.GenericCertificatePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
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
    @Mock
    private ProxyInformacionCredito proxyInformacionCredito;
    private GenerarCertificadosController generarCertificadosController;
    private MapperTelService mapperTelService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mapperTelService = new MapperTelServiceImpl();
        generarCertificadosController = new GenerarCertificadosController(generarCertificadosService, proxyInformacionCredito, mapperTelService);
    }

    @Test
    public void testGenerarCertificadoPazYSalvo() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificadoEstandar(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_PAZ_Y_SALVO), Mockito.any(), Mockito.eq(3L))).thenReturn(Optional.of(InformacionCreditoResponsePayload.builder().build()));
        ResponseEntity<InformacionCreditoResponsePayload> result = generarCertificadosController.generarCertificadoPazYSalvo(token, GenericCertificatePayload.builder().telefono("3229032840").build());
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarCertificadoPazYSalvoError() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificadoEstandar(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_PAZ_Y_SALVO), Mockito.any(), Mockito.eq(3L))).thenReturn(Optional.empty());
        ResponseEntity<InformacionCreditoResponsePayload> result = generarCertificadosController.generarCertificadoPazYSalvo(token, GenericCertificatePayload.builder().telefono("3229032840").build());

    }

    @Test
    public void testGenerarAutorizacionDebito() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificadoEstandar(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_DEBITO_AUTOMATICO), Mockito.any(), Mockito.eq(4L))).thenReturn(Optional.of(InformacionCreditoResponsePayload.builder().build()));
        ResponseEntity<InformacionCreditoResponsePayload> result = generarCertificadosController.autorizacionDebito(token, GenericCertificatePayload.builder().telefono("3229032840").build());
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarAutorizacionDebitoError() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificadoEstandar(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_DEBITO_AUTOMATICO), Mockito.any(), Mockito.eq(4L))).thenReturn(Optional.empty());
        ResponseEntity<InformacionCreditoResponsePayload> result = generarCertificadosController.autorizacionDebito(token, GenericCertificatePayload.builder().telefono("3229032840").build());

    }

    @Test
    public void testGenerarDeclaracionRenta() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificadoEstandar(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_DECLARACION_RENTA), Mockito.any(), Mockito.eq(5L))).thenReturn(Optional.of(InformacionCreditoResponsePayload.builder().build()));
        ResponseEntity<InformacionCreditoResponsePayload> result = generarCertificadosController.certificacionDeclaracionRenta(token, GenericCertificatePayload.builder().telefono("3229032840").build());
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarDeclaracionRentaError() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificadoEstandar(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_DECLARACION_RENTA), Mockito.any(), Mockito.eq(5L))).thenReturn(Optional.empty());
        ResponseEntity<InformacionCreditoResponsePayload> result = generarCertificadosController.autorizacionDebito(token, GenericCertificatePayload.builder().telefono("3229032840").build());
    }

    @Test
    public void testGenerarInformacionCredito() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(proxyInformacionCredito.generarInformacionCredito(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_INFORMACION_CREDITO), Mockito.any())).thenReturn(Optional.of(InformacionCreditoResponsePayload.builder().build()));
        ResponseEntity<InformacionCreditoResponsePayload> result = generarCertificadosController.informacionCredito(token, InformacionCreditoPayload.builder().telefono("3229032840").build());
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarInformacionCreditoError() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarInformacionCredito(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_INFORMACION_CREDITO), Mockito.any())).thenReturn(Optional.empty());
        ResponseEntity<InformacionCreditoResponsePayload> result = generarCertificadosController.informacionCredito(token, InformacionCreditoPayload.builder().telefono("3229032840").build());
    }


}