package co.com.santander.chatbot.backend.web.common.utilities;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SecurityUtilitiesTest {


    @Test
    public void testEncriptarSuccess() throws Exception {
        String encriptado = SecurityUtilities.encriptar("12345678AAAA");
        Assert.assertNotNull(encriptado);
    }

    @Test
    public void testDesencriptarSuccess() throws Exception {
        String desencriptado = SecurityUtilities.desencriptar("kcZsJENvAG1YUB7AZpHm5hhs2L4ZABjKyQD0IA==");
        Assert.assertNotNull(desencriptado);

    }

}