package co.com.santander.chatbot.domain.validators.exceptions;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MissingParameterExceptionTest {

    @Test
    public void testMissingParameterException(){
        MissingParameterException exception = new MissingParameterException("test");
        Assert.assertNotNull(exception);
    }
}
