package co.com.santander.chatbot.backend.web.common.utilities;

import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class SecurityUtilitiesTest {

    private SecurityUtilities securityUtilities;

    @Before
    public void setUp() {
        securityUtilities = new SecurityUtilities();
    }

    @Test
    public void testEncriptarSuccess() throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String encriptado = securityUtilities.encriptar("12345678AAAA");
        Assert.assertNotNull(encriptado);
    }

    @Test
    public void testDesencriptarSuccess() throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String desencriptado = securityUtilities.desencriptar("WhMHuAOSLgKlidOUbVwnng==");
        Assert.assertNotNull(desencriptado);

    }


}