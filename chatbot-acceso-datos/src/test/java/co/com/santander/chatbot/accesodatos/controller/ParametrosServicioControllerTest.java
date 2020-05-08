package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.Canal;
import co.com.santander.chatbot.accesodatos.entity.ParametrosServicio;
import co.com.santander.chatbot.accesodatos.entity.Servicio;
import co.com.santander.chatbot.accesodatos.service.ParametrosServicioService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosServicioPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ParametrosServicioControllerTest {

    public static final String CANAL = "WhatsApp";
    public static final String SERVICIO = "Paz y Salvo";
    private ParametrosServicioController parametrosServicioController;
    private ModelMapper mapper;

    @Mock
    private ParametrosServicioService parametrosServicioService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mapper = new ModelMapper();
        parametrosServicioController = new ParametrosServicioController(parametrosServicioService, mapper);
    }

    @Test
    public void testConsultarProcesoSUCCESS() {
        Date date = new Date();
        Mockito.when(parametrosServicioService.validarSolicitud(CANAL, SERVICIO, date)).thenReturn(ResponsePayload.builder().build());
        ResponseEntity<ResponsePayload> result = parametrosServicioController.consultarProceso(ValidarProcesoPayload.builder()
                .canal(CANAL)
                .servicio(SERVICIO)
                .fechaUltimaSolicitud(date)
                .build());
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetParametroService(){
        Optional<ParametrosServicio> response = Optional.of(ParametrosServicio.builder()
                .id(1L)
                .canal(Canal.builder().build())
                .servicio(Servicio.builder().build())
                .numeroIntentos(1)
                .tiempoIntentos(2)
                .tiempoPosterior(2)
                .build());
        Mockito.doReturn(response).when(parametrosServicioService).findByServicio(Mockito.any());

        ResponseEntity<ParametrosServicioPayload> responseParam = parametrosServicioController.getParametroService(ServiciosEnum.SERVICIO_PAZ_Y_SALVO);

        Assert.assertNotNull(responseParam);
        Assert.assertEquals(HttpStatus.OK, responseParam.getStatusCode());
    }
}

