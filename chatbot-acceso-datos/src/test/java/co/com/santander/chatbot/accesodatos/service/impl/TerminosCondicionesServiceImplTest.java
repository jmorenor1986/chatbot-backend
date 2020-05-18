package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.TerminosCondiciones;
import co.com.santander.chatbot.accesodatos.repository.TerminosCondicionesRepository;
import co.com.santander.chatbot.accesodatos.service.TerminosCondicionesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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
}