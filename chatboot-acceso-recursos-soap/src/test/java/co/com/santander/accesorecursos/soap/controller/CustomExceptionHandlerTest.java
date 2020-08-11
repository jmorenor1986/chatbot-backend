package co.com.santander.accesorecursos.soap.controller;

import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import co.com.santander.accesorecursos.soap.common.exception.EnvioExtractoMailException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler;
    @Before
    public void setUp(){
        customExceptionHandler = new CustomExceptionHandler();
    }
    @Test
    public void testHandlerEnvioExtractoMailException(){
        EnvioExtractoMailException ex = new EnvioExtractoMailException("test");
        ResponseEntity<Object> response = customExceptionHandler.handlerEnvioExtractoMailException(ex, null);
        Assert.assertNotNull(response);
    }

    @Test
    public void testhandlerBusinessExceptionSuccess(){
        BusinessException ex = new BusinessException("test");
        ResponseEntity<Object> response = customExceptionHandler.handlerBusinessException(ex, null);
        Assert.assertNotNull(response);
    }


}