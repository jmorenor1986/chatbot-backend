package co.com.santander.chatbot.domain.common.utilities;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.GeneralSecurityException;

@SpringBootTest
public class SecurityUtilitiesTest {
    @Test
    public void testEncriptar() throws GeneralSecurityException {
        String response = SecurityUtilities.encriptar("texto_encriptado");
        Assert.assertNotNull(response);
    }

    @Test
    public void testDesEncriptarCatchSUCCESS() {
        String response = SecurityUtilities.desencriptarCatch("1JEnZBkGUjt6YzbxAD805mIa5vmZyn2Rtjlr/pQ0Ll4=");
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
        Assert.assertEquals("texto_encriptado", response);
    }

    @Test
    public void testDesEncriptarSUCCESS() throws GeneralSecurityException {
        String response = SecurityUtilities.desencriptar("1JEnZBkGUjt6YzbxAD805mIa5vmZyn2Rtjlr/pQ0Ll4=");
        Assert.assertNotNull(response);
        Assert.assertEquals("texto_encriptado", response);
    }

    @Test(expected = GeneralSecurityException.class)
    public void testDesEncriptarFAILED() throws GeneralSecurityException {
        String response = SecurityUtilities.desencriptar("1234");
        Assert.assertNotNull(response);
        Assert.assertEquals("texto_encriptado", response);
    }
    @Test
    public void testDesEncriptarCatchFAILED() {
        String response = SecurityUtilities.desencriptarCatch("1234");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());
    }

}