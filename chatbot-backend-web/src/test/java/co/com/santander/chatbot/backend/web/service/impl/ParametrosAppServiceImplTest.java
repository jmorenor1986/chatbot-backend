package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosAppClient;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosAppPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class ParametrosAppServiceImplTest {

    private ParametrosAppService parametrosAppService;

    @Mock
    private ParametrosAppClient parametrosAppClient;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.parametrosAppService = new ParametrosAppServiceImpl(parametrosAppClient);
    }
    @Test
    public void testGetParamByKey(){
        ResponseEntity<ParametrosAppPayload> responseMockito = new ResponseEntity<>(
                ParametrosAppPayload.builder()
                        .id(1L)
                        .clave("MESES_EXTRACTO")
                        .valor("12")
                        .build(), HttpStatus.OK
        );

        Mockito.doReturn(responseMockito).when(parametrosAppClient).getByClave(Mockito.any());

        Optional<String> response = parametrosAppService.getParamByKey("MESES_EXTRACTO");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}