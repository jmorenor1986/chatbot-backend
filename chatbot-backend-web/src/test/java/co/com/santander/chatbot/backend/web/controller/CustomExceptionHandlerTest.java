package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.domain.validators.exceptions.MandatoryFieldException;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStatusAfterProcess;
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

    @Test
    public void testHandlerValidateStateCertificateException() {
        ValidateStateCertificateException validateStateCertificateException = new ValidateStateCertificateException("test", 0L);
        ResponseEntity<?> result = customExceptionHandler.validateStateCertificateException(validateStateCertificateException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testHandlerValidateStateAfter() {
        ValidateStatusAfterProcess validateStatusAfterProcessException = new ValidateStatusAfterProcess("test", "xxxxxa@gmail.com", "xxxxx12345","LOS COCHES S.A.", 0L);
        ResponseEntity<?> result = customExceptionHandler.validateStateAfter(validateStatusAfterProcessException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

}