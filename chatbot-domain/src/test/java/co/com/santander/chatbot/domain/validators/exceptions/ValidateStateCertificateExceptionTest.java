package co.com.santander.chatbot.domain.validators.exceptions;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidateStateCertificateExceptionTest {

    @Test
    public void test(){
        ValidateStateCertificateException exception = new ValidateStateCertificateException("test", 10L);
        Assert.assertNotNull(exception);
    }

    @Test
    public void testGETmin(){
        ValidateStateCertificateException exception = new ValidateStateCertificateException("test", 10L);
        Assert.assertNotNull(exception);
        Assert.assertNotNull(exception.getMinutos());
    }

    @Test
    public void testExc(){
        ValidateStatusAfterProcess exception = new ValidateStatusAfterProcess("test", "correo@gmail.com", "123","123",10L, "123");
        Assert.assertNotNull(exception);
    }

    @Test
    public void testExcGets(){
        ValidateStatusAfterProcess exception = new ValidateStatusAfterProcess("test", "correo@gmail.com", "123","123",10L, "123");
        Assert.assertNotNull(exception);
        Assert.assertNotNull(exception.getMessage());
        Assert.assertNotNull(exception.getConvenio());
        Assert.assertNotNull(exception.getMinutos());
        Assert.assertNotNull(exception.getEmail());

    }
}