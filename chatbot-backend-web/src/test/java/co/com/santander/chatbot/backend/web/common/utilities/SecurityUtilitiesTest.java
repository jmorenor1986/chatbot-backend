package co.com.santander.chatbot.backend.web.common.utilities;

import org.junit.Assert;
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


    @Test
    public void testEncriptarSuccess() throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String encriptado = SecurityUtilities.encriptar("12345678AAAA");
        Assert.assertNotNull(encriptado);
    }

    @Test
    public void testDesencriptarSuccess() throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String desencriptado = SecurityUtilities.desencriptar("UT1osAHC8aWf1PjIpc/BLA==");
        Assert.assertNotNull(desencriptado);

    }


}