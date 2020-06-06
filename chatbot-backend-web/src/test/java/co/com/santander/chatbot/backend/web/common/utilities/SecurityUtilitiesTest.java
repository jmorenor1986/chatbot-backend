package co.com.santander.chatbot.backend.web.common.utilities;

import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.GeneralSecurityException;

@SpringBootTest
public class SecurityUtilitiesTest {


    @Test
    public void testEncriptarSuccess() throws GeneralSecurityException {
        String encriptado = SecurityUtilities.encriptar("6000000457");
        Assert.assertNotNull(encriptado);
    }

    @Test
    public void testDesencriptarSuccess() throws GeneralSecurityException {
        String desencriptado = SecurityUtilities.desencriptar("kcZsJENvAG1YUB7AZpHm5hhs2L4ZABjKyQD0IA==");
        Assert.assertNotNull(desencriptado);

    }

}