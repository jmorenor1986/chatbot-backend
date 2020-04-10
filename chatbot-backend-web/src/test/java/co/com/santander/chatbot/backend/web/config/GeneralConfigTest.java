package co.com.santander.chatbot.backend.web.config;

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
public class GeneralConfigTest {

    private GeneralConfig generalConfig;

    @Before
    public void setUp() {
        generalConfig = new GeneralConfig();
    }

    @Test
    public void testEncriptarSuccess() throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String clave = "12345678AAAA";
        String encriptado = generalConfig.desencriptar(clave);
        Assert.assertNotNull(encriptado);

    }


}