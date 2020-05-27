package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.ParametrosApp;
import co.com.santander.chatbot.accesodatos.repository.ParametrosAppRepository;
import co.com.santander.chatbot.accesodatos.service.ParametrosAppService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ParametrosAppServiceImplTest {

    private ParametrosAppService parametrosAppService;
    @Mock
    private ParametrosAppRepository parametrosAppRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        parametrosAppService = new ParametrosAppServiceImpl(parametrosAppRepository);
    }
    @Test
    public void testFindByClave(){
        Optional<ParametrosApp> responseMockito = Optional.of(ParametrosApp.builder()
                .clave("DIAS_DESEMBOLSO")
                .valor("70")
                .build());

        Mockito.doReturn(responseMockito).when(parametrosAppRepository).findByClave(Mockito.any());

        Optional<ParametrosApp> response = parametrosAppService.findByClave("DIAS_DESEMBOLSO");

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }
}