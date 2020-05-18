package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.TerminosCondiciones;
import co.com.santander.chatbot.accesodatos.repository.TerminosCondicionesRepository;
import co.com.santander.chatbot.accesodatos.service.TerminosCondicionesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public  class TerminosCondicionesServiceImplTest {

    private TerminosCondicionesService terminosCondicionesService;
    @Mock
    private TerminosCondicionesRepository terminosCondicionesRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        terminosCondicionesService = new TerminosCondicionesServiceImpl(terminosCondicionesRepository);
    }

    @Test
    public void testSave(){
        TerminosCondiciones terminosResponse = TerminosCondiciones.builder()
                .operacion(1L)
                .horaEnviadoTeminos(new Date())
                .telefono(3229032614L)
                .id(1L)
                .build();
        Mockito.doReturn(terminosResponse).when(terminosCondicionesRepository).save(Mockito.any());

        Optional<TerminosCondiciones> response = terminosCondicionesService.save(terminosResponse);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}