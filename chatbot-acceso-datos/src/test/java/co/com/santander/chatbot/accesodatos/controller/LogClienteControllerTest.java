package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.LogClienteService;
import co.com.santander.chatbot.domain.common.utilities.GenericLogPayload;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
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
public class LogClienteControllerTest {

    private LogClienteController logClienteController;
    @Mock
    private LogClienteService logClienteService;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.logClienteController = new LogClienteController(logClienteService);
    }

    @Test
    public void testInsertarLogSUCCESS() {
        GenericLogPayload genericLogPayload = GenericLogPayload
                .builder()
                .credito("12345678")
                .identificacion("1234")
                .serviciosEnum(ServiciosEnum.SERVICIO_OBTENER_CREDITOS)
                .telefono("3229032614")
                .build();
        Mockito.when(logClienteService.saveLogService(genericLogPayload)).thenReturn(Optional.of(Boolean.TRUE));
        ResponseEntity<Boolean> response = this.logClienteController.insertarLog(genericLogPayload);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testInsertarLogFAILED() {
        GenericLogPayload genericLogPayload = GenericLogPayload
                .builder()
                .credito("12345678")
                .identificacion("1234")
                .serviciosEnum(ServiciosEnum.SERVICIO_OBTENER_CREDITOS)
                .telefono("3229032614")
                .build();
        Mockito.when(logClienteService.saveLogService(genericLogPayload)).thenReturn(Optional.empty());
        ResponseEntity<Boolean> response = this.logClienteController.insertarLog(genericLogPayload);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}