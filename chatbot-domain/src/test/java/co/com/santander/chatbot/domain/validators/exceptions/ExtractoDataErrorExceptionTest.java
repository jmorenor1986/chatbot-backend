package co.com.santander.chatbot.domain.validators.exceptions;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExtractoDataErrorExceptionTest {

    @Test
    public void testExtractoDataErrorException(){
        ExtractoDataErrorException objeto = new  ExtractoDataErrorException("test");
        Assert.assertNotNull(objeto);
    }
}
