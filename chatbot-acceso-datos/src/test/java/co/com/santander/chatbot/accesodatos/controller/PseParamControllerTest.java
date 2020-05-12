package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.PseParam;
import co.com.santander.chatbot.accesodatos.service.PseParamService;
import co.com.santander.chatbot.domain.payload.accesodatos.PseParamPayload;
import io.swagger.models.Model;
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

import java.util.Optional;

@SpringBootTest
public class PseParamControllerTest {

    private PseParamController pseParamController;
    @Mock
    private PseParamService pseParamService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ModelMapper map = new ModelMapper();
        pseParamController = new PseParamController(pseParamService, map);
    }

    @Test
    public void testGetByIdBancoAndTipoCredito(){
        Long idBanco = 9000L;
        Long tipoCredito = 1L;

        Optional<PseParam> responseMockito = Optional.of(PseParam.builder()
                .id(1L)
                .idBanco(9000L)
                .tipoCredito(1L)
                .url("https://url1")
                .build());

        Mockito.doReturn(responseMockito).when(pseParamService).findByIdBancoAndTipoCredito(idBanco, tipoCredito);

        ResponseEntity<PseParamPayload> response = pseParamController.getByIdBancoAndTipoCredito(idBanco,tipoCredito);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}