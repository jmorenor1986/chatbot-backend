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
        Mockito.when(generarCertificadosService.generarCertificadoPazYSalvo(Mockito.eq(token), Mockito.eq(CertificadoPayload.builder().build()), Mockito.eq(ServiciosEnum.SERVICIO_PAZ_Y_SALVO), Mockito.any())).thenReturn(Optional.of(ResponsePayload.builder().build()));
        ResponseEntity<ResponsePayload> result = generarCertificadosController.generarCertificadoPazYSalvo(token, CertificadoPayload.builder().build());
        Assert.assertNotNull(result);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarCertificadoPazYSalvoError() {
        String token = "1221221";
        Date date = new Date();
        Mockito.when(generarCertificadosService.generarCertificadoPazYSalvo(token, CertificadoPayload.builder().build(), ServiciosEnum.SERVICIO_PAZ_Y_SALVO, date)).thenReturn(Optional.empty());
        ResponseEntity<ResponsePayload> result = generarCertificadosController.generarCertificadoPazYSalvo(token, CertificadoPayload.builder().build());
    }

}