package co.com.santander.chatbot.domain.validators.exceptions;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IdExtractoNotFoundExceptionTest {

    @Test
    public void testIdExtractoNotFoundException(){
        IdExtractoNotFoundException objeto = new IdExtractoNotFoundException("1");
        Assert.assertNotNull(objeto);
    }
}
