package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.Canal;
import co.com.santander.chatbot.accesodatos.entity.Log;
import co.com.santander.chatbot.accesodatos.entity.Servicio;
import co.com.santander.chatbot.accesodatos.repository.LogClienteRepository;
import co.com.santander.chatbot.accesodatos.repository.ServicioRepository;
import co.com.santander.chatbot.accesodatos.service.LogClienteService;
import co.com.santander.chatbot.domain.common.utilities.GenericLogPayload;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class LogClienteServiceImplTest {

    private LogClienteService logClienteService;
    @Mock
    private LogClienteRepository logClienteRepository;
    @Mock
    private ServicioRepository servicioRepository;
    private ModelMapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mapper = new ModelMapper();
        this.logClienteService = new LogClienteServiceImpl(logClienteRepository, servicioRepository, mapper);
    }

    @Test
    public void testSaveLogServiceSUCCES() {
        GenericLogPayload genericLogPayload = GenericLogPayload
                .builder()
                .credito("12345678")
                .identificacion("1234")
                .serviciosEnum(ServiciosEnum.SERVICIO_OBTENER_CREDITOS)
                .telefono("3229032614")
                .build();
        Servicio servicioRta = Servicio.builder()
                .id(1L)
                .nombre(ServiciosEnum.SERVICIO_OBTENER_CREDITOS.name())
                .logs(new ArrayList<>())
                .build();
        Log logReq = Log.builder()
                .canal(Canal.builder().id(1L).build())
                .feha(new Date())
                .traza("traza")
                .servicio(Servicio.builder().id(1L).build())
                .telefono("3229032614")
                .nombreCliente("Jesus Nicolas Sierra Chaparro")
                .build();
        Log logRes = Log.builder()
                .id(1L)
                .canal(Canal.builder().id(1L).build())
                .feha(new Date())
                .traza("traza")
                .servicio(Servicio.builder().id(1L).build())
                .telefono("3229032614")
                .nombreCliente("Jesus Nicolas Sierra Chaparro")
                .build();
        Mockito.when(servicioRepository.findByNombre(ServiciosEnum.SERVICIO_OBTENER_CREDITOS.name()))
                .thenReturn(Optional.of(servicioRta));
        Mockito.when(logClienteRepository.save(logReq)).thenReturn(logRes);

        Optional<Boolean> response = logClienteService.saveLogService(genericLogPayload);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertNotNull(response.get());
    }

    @Test
    public void testSaveLogServiceFAILED() {
        GenericLogPayload genericLogPayload = GenericLogPayload
                .builder()
                .credito("12345678")
                .identificacion("1234")
                .serviciosEnum(ServiciosEnum.SERVICIO_OBTENER_CREDITOS)
                .telefono("3229032614")
                .build();

        Log logReq = Log.builder()
                .canal(Canal.builder().id(1L).build())
                .feha(new Date())
                .traza("traza")
                .servicio(Servicio.builder().id(1L).build())
                .telefono("3229032614")
                .nombreCliente("Jesus Nicolas Sierra Chaparro")
                .build();
        Log logRes = Log.builder()
                .id(1L)
                .canal(Canal.builder().id(1L).build())
                .feha(new Date())
                .traza("traza")
                .servicio(Servicio.builder().id(1L).build())
                .telefono("3229032614")
                .nombreCliente("Jesus Nicolas Sierra Chaparro")
                .build();
        Mockito.when(servicioRepository.findByNombre(ServiciosEnum.SERVICIO_OBTENER_CREDITOS.name()))
                .thenReturn(Optional.empty());
        Mockito.when(logClienteRepository.save(logReq)).thenReturn(logRes);

        Optional<Boolean> response = logClienteService.saveLogService(genericLogPayload);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertEquals(Boolean.FALSE, response.get());
    }
}