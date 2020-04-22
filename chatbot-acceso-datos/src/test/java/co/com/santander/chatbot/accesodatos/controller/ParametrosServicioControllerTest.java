package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.ParametrosServicioService;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest
public class ParametrosServicioControllerTest {

    public static final String CANAL = "WhatsApp";
    public static final String SERVICIO = "Paz y Salvo";
    private ParametrosServicioController parametrosServicioController;

    @Mock
    private ParametrosServicioService parametrosServicioService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        parametrosServicioController = new ParametrosServicioController(parametrosServicioService);
    }

    @Test
    public void testConsultarProcesoSUCCESS() {
        Date date = new Date();
        Mockito.when(parametrosServicioService.validarSolicitud(CANAL, SERVICIO, date)).thenReturn(ResponsePayload.builder().build());
        ResponseEntity<ResponsePayload> result = parametrosServicioController.consultarProceso(SERVICIO, CANAL, date);
        Assert.assertNotNull(result);
    }
}

