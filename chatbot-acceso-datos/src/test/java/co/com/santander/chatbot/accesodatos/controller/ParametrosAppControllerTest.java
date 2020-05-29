package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.ParametrosApp;
import co.com.santander.chatbot.accesodatos.service.ParametrosAppService;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosAppPayload;
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

import javax.xml.ws.Response;
import java.util.Optional;

@SpringBootTest
public class ParametrosAppControllerTest {

    private ParametrosAppController parametrosAppController;
    @Mock
    private ParametrosAppService parametrosAppService;
    private ModelMapper modelMapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.modelMapper = new ModelMapper();
        this.parametrosAppController = new ParametrosAppController(parametrosAppService, modelMapper);
    }

    @Test
    public void testGetByClave(){
        Optional<ParametrosApp> responseMockito = Optional.of(ParametrosApp.builder()
                .clave("DIAS_DESEMBOLSO")
                .valor("70")
                .build());
        Mockito.doReturn(responseMockito).when(parametrosAppService).findByClave(Mockito.any());

        ResponseEntity<ParametrosAppPayload> response = parametrosAppController.getByClave("DIAS_DESEMBOLSO");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testGetByClaveNO_CONTENT(){
        ParametrosAppPayload request = ParametrosAppPayload.builder()
                .clave("DIAS_DESEMBOLSO")
                .valor("70")
                .build();
        Optional<ParametrosApp> responseMockito = Optional.of(ParametrosApp.builder()
                .id(1L)
                .clave("DIAS_DESEMBOLSO")
                .valor("70")
                .build());
        Mockito.doReturn(responseMockito).when(parametrosAppService).save(Mockito.any());

        ResponseEntity<ParametrosAppPayload> response = parametrosAppController.save( request );
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}