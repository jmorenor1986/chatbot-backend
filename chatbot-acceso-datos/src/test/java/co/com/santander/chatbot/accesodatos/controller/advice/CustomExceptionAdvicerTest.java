package co.com.santander.chatbot.accesodatos.controller.advice;

import co.com.santander.chatbot.domain.validators.exceptions.MandatoryFieldException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CustomExceptionAdvicerTest {

    private CustomExceptionAdvicer customExceptionAdvicer;

    @Before
    public void setUp() {
        customExceptionAdvicer = new CustomExceptionAdvicer();
    }

    @Test
    public void testHandlerMandatoryFieldException() {
        MandatoryFieldException mandatoryFieldException = new MandatoryFieldException("test");
        ResponseEntity<?> result = customExceptionAdvicer.handlerMandatoryFieldException(mandatoryFieldException, null);
        Assert.assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    public void testHandlerGenericException() {
        Exception exception = new Exception("test");
        ResponseEntity<?> result = customExceptionAdvicer.handlerGenericException(exception, null);
        Assert.assertEquals(500, result.getStatusCodeValue());
    }

}