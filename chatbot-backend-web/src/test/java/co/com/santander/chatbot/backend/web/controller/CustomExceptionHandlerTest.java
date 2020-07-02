package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.domain.validators.exceptions.*;
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
        Assert.assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    public void testHandlerValidateStateCertificateException() {
        ValidateStateCertificateException validateStateCertificateException = new ValidateStateCertificateException("test", 0L);
        ResponseEntity<?> result = customExceptionHandler.validateStateCertificateException(validateStateCertificateException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testHandlerValidateStateAfter() {
        ValidateStatusAfterProcess validateStatusAfterProcessException = new ValidateStatusAfterProcess("test", "xxxxxa@gmail.com", "xxxxx12345","LOS COCHES S.A.", 0L, "7", "1");
        ResponseEntity<?> result = customExceptionHandler.validateStateAfter(validateStatusAfterProcessException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
    @Test
    public void testLengthValuesException(){
        LengthValuesException lengthValuesException = new LengthValuesException("test");
        ResponseEntity<?> result = customExceptionHandler.lengthValuesException(lengthValuesException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
    @Test
    public void testHandlerAllowedValuesException(){
        AllowedValuesException allowedValuesException = new AllowedValuesException("test");
        ResponseEntity<?> result = customExceptionHandler.handlerAllowedValuesException(allowedValuesException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testHandlerInvalidNumVerificadorException(){
        InvalidNumVerificadorException allowedValuesException = new InvalidNumVerificadorException("test");
        ResponseEntity<?> result = customExceptionHandler.handlerInvalidNumVerificadorException(allowedValuesException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testHandlerMailConstraintException(){
        MailConstraintException allowedValuesException = new MailConstraintException("test");
        ResponseEntity<?> result = customExceptionHandler.handlerMailConstraintException(allowedValuesException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testHandlerIdExtractoNotFound(){
        IdExtractoNotFoundException idExtractoNotFoundException = new IdExtractoNotFoundException("test");
        ResponseEntity<?> result = customExceptionHandler.handlerIdExtractoNotFound(idExtractoNotFoundException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testHandlerExtractoDataErrorException(){
        ExtractoDataErrorException extractoDataErrorException = new ExtractoDataErrorException("test");
        ResponseEntity<?> result = customExceptionHandler.handlerExtractoDataErrorException(extractoDataErrorException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testHandlerMissingParameterException(){
        MissingParameterException missingParameterException = new MissingParameterException("test");
        ResponseEntity<?> result = customExceptionHandler.handlerMissingParameterException(missingParameterException, null);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

}