package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.ParametrosServicio;
import co.com.santander.chatbot.accesodatos.repository.ParametrosServicioRepository;
import co.com.santander.chatbot.accesodatos.service.ParametrosServicioService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ParametrosServicioServiceTest {
    public static final String CANAL = "WhatsApp";
    private ParametrosServicioService parametrosServicioService;

    @Mock
    private ParametrosServicioRepository parametrosServicioRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        parametrosServicioService = new ParametrosServicioServiceImpl(parametrosServicioRepository);
    }

    @Test
    public void testBuscarParametrosXServicioSUCCESS() {
        Date date = new Date();
        List<ParametrosServicio> parametrosServicioList = new ArrayList<>();
        parametrosServicioList.add(ParametrosServicio.builder()
                .id(1L)
                .canal(CANAL)
                .numeroIntentos(2)
                .servicio(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage())
                .tiempoIntentos(-5)
                .build());
        Mockito.when(parametrosServicioRepository.findByNameService(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage())).thenReturn(parametrosServicioList);
        ResponsePayload respuesta = parametrosServicioService.validarSolicitud(CANAL, ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage(), date);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(Boolean.TRUE, respuesta.getResultadoValidacion());
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testBuscarParametrosXServicioBUSINESSERROR() {
        Date date = new Date();
        List<ParametrosServicio> parametrosServicioList = new ArrayList<>();
        parametrosServicioList.add(ParametrosServicio.builder()
                .id(1L)
                .canal(CANAL)
                .numeroIntentos(2)
                .servicio(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage())
                .tiempoIntentos(5)
                .build());
        Mockito.when(parametrosServicioRepository.findByNameService(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage())).thenReturn(parametrosServicioList);
        ResponsePayload respuesta = parametrosServicioService.validarSolicitud(CANAL, ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage(), date);
    }


    @Test(expected = ValidateStateCertificateException.class)
    public void testBuscarParametrosXServicioNOTFOUND() {
        Date date = new Date();
        List<ParametrosServicio> parametrosServicioList = new ArrayList<>();
        Mockito.when(parametrosServicioRepository.findByNameService(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage())).thenReturn(parametrosServicioList);
        ResponsePayload respuesta = parametrosServicioService.validarSolicitud(CANAL, ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage(), date);
    }


}
