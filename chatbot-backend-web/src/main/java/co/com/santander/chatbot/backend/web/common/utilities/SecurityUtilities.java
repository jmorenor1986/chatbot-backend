
package co.com.santander.chatbot.backend.web.common.utilities;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;


public class SecurityUtilities {

    public static final String UTF_8 = "utf-8";
    public static final String MD_5 = "MD5";
    public static final String AES = "AES";

    private SecurityUtilities() {
    }

    public static final String SEMILLA_DEFAULT = "1qazxsw23EDC";
    @Value("${security.semilla}")
    private static String semilla;

    public static String encriptar(String texto) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if (Objects.isNull(semilla))
            semilla = SEMILLA_DEFAULT;
        MessageDigest md = MessageDigest.getInstance(MD_5);
        byte[] digestOfPassword = md.digest(semilla.getBytes(UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        SecretKey key = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] plainTextBytes = texto.getBytes(UTF_8);
        byte[] buf = cipher.doFinal(plainTextBytes);
        byte[] base64Bytes = Base64.encodeBase64(buf);
        return new String(base64Bytes);
    }

    public static String desencriptar(String texto) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if (Objects.isNull(semilla))
            semilla = SEMILLA_DEFAULT;
        byte[] message = Base64.decodeBase64(texto.getBytes(UTF_8));
        MessageDigest md = MessageDigest.getInstance(MD_5);
        byte[] digestOfPassword = md.digest(semilla.getBytes(UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        SecretKey key = new SecretKeySpec(keyBytes, AES);
        Cipher decipher = Cipher.getInstance(AES);
        decipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = decipher.doFinal(message);
        return new String(plainText, UTF_8);
    }
}
