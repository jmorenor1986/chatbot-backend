package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.TerminosCondicionesService;
import co.com.santander.chatbot.domain.payload.accesodatos.TerminosCondicionesPayload;
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
public class TerminosCondicionesControllerTest {

    private TerminosCondicionesController terminosCondicionesController;
    @Mock
    private TerminosCondicionesService terminosCondicionesService;
    private ModelMapper mapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mapper = new ModelMapper();
        terminosCondicionesController = new TerminosCondicionesController(terminosCondicionesService, mapper);
    }

    @Test
    public void testSave(){

        TerminosCondicionesPayload terminos = TerminosCondicionesPayload.builder()
                .id(1L)
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
        Mockito.doReturn(responseMockito).when(terminosCondicionesService).save(Mockito.any());

        ResponseEntity<TerminosCondicionesPayload> response =  terminosCondicionesController.save(terminos);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}