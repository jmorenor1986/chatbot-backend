package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.domain.validators.exceptions.MandatoryFieldException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CustomExceptionHandlerTest {


    private CustomExceptionHandler customExceptionHandler;

    @Before
    public void setUp() {
        customExceptionHandler = new CustomExceptionHandler();
    }

    @Test
    public void testHandlerMandatoryFieldException() {
        MandatoryFieldException mandatoryFieldException = new MandatoryFieldException("test");
        ResponseEntity<?> result = customExceptionHandler.handlerMandatoryFieldException(mandatoryFieldException, null);
        Assert.assertEquals(400, result.getStatusCodeValue());

    }

}