package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.backend.web.service.TerminosCondicionesService;
import co.com.santander.chatbot.backend.web.service.impl.MapperTelServiceImpl;
import co.com.santander.chatbot.domain.payload.accesodatos.TerminosCondicionesPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class TerminosCondicionesControllerTest {

    private TerminosCondicionesController terminosCondicionesController;
    @Mock
    private TerminosCondicionesService terminosCondicionesService;
    private MapperTelService mapperTelService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mapperTelService = new MapperTelServiceImpl();
        terminosCondicionesController = new TerminosCondicionesController(terminosCondicionesService, mapperTelService);
    }

    @Test
    public void testSave(){
        String token = "1";
        TerminosCondicionesPayload terminosCondicionesPayload = TerminosCondicionesPayload.builder()
                .horaEnviadoTeminos(new Date())
                .horaOperacion(new Date())
                .operacion(1L)
                .telefono("3229032614")
                .build();

        Optional<TerminosCondicionesPayload> responseMockito = Optional.of(TerminosCondicionesPayload.builder()
                .id(1L)
                .horaEnviadoTeminos(new Date())
                .horaOperacion(new Date())
                .operacion(1L)
                .telefono("3229032614")
                .build());

        Mockito.doReturn(responseMockito).when(terminosCondicionesService).save(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<TerminosCondicionesPayload> response = terminosCondicionesController.save(token, terminosCondicionesPayload);

        Assert.assertNotNull(response);
        Assert.assertEquals( HttpStatus.OK, response.getStatusCode() );
    }

}