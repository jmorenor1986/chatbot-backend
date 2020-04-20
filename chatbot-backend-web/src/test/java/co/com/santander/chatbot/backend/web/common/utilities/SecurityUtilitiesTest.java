package co.com.santander.chatbot.backend.web.common.utilities;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class SecurityUtilitiesTest {


    @Test
    public void testEncriptarSuccess() throws BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        String encriptado = SecurityUtilities.encriptar("12345678AAAA");
        Assert.assertNotNull(encriptado);
    }

    @Test
    public void testDesencriptarSuccess() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String desencriptado = SecurityUtilities.desencriptar("kcZsJENvAG1YUB7AZpHm5hhs2L4ZABjKyQD0IA==");
        Assert.assertNotNull(desencriptado);

    }

}