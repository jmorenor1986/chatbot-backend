package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.TerminosCondicionesClient;
import co.com.santander.chatbot.backend.web.service.TerminosCondicionesService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
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
public class TerminosCondicionesServiceImplTest {

    private TerminosCondicionesService terminosCondicionesService;
    @Mock
    private TerminosCondicionesClient terminosCondicionesClient;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        terminosCondicionesService = new TerminosCondicionesServiceImpl(terminosCondicionesClient);
    }

    @Test
    public void testSave(){
        ResponseEntity<TerminosCondicionesPayload> responseMockito = new ResponseEntity<>(TerminosCondicionesPayload.builder()
                .id(1L)
                .horaEnviadoTeminos(new Date())
                .horaOperacion(new Date())
                .operacion(1L)
                .telefono("3229032614")
                .build(),HttpStatus.OK);

        Mockito.doReturn(responseMockito).when(terminosCondicionesClient).save(Mockito.any(), Mockito.any());

        Optional<TerminosCondicionesPayload> response = terminosCondicionesService.save("12321afd", ServiciosEnum.SERVICIO_TERMINOS_CONDICIONES,"3229032614",responseMockito.getBody());

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}