package co.com.santander.chatbot.domain.validators.exceptions;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidateStatusAfterProcessTest {

    @Test
    public void test(){
        ValidateStatusAfterProcess objeto = new ValidateStatusAfterProcess("test", "correo@gmail.com", "123","123",10L, "123","1");
        Assert.assertNotNull(objeto);
    }

    @Test
    public void testGetNumCredito(){
        ValidateStatusAfterProcess objeto = new ValidateStatusAfterProcess("test", "correo@gmail.com", "123","123",10L, "123","1");
        Assert.assertNotNull(objeto.getNumeroCredito());
    }

    @Test
    public void testGetIdRespuesta(){
        ValidateStatusAfterProcess objeto = new ValidateStatusAfterProcess("test", "correo@gmail.com", "123","123",10L, "123","1");
        Assert.assertNotNull(objeto.getIdRespuesta());
    }

    @Test
    public void testGetTipoCredito(){
        ValidateStatusAfterProcess objeto = new ValidateStatusAfterProcess("test", "correo@gmail.com", "123","123",10L, "123","1");
        Assert.assertNotNull(objeto.getTipoCredito());
    }

}
