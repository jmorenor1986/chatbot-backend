
package co.com.santander.chatbot.domain.common.utilities;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Objects;

@Log
public class SecurityUtilities {

    public static final int AES_KEY_SIZE = 256;
    public static final int GCM_IV_LENGTH = 12;
    public static final int GCM_TAG_LENGTH = 16;
    private static final byte[] IV = new byte[GCM_IV_LENGTH];
    public static final String SEMILLA_DEFAULT = "1234567890123456";
    public static final String CIPHER = "AES/GCM/NoPadding";
    public static final String CIPHER_SECRET_KEY = "AES";

    @Value("${security.semilla}")
    private static String semilla;

    private SecurityUtilities() {
    }

    public static String encriptar(String texto) throws GeneralSecurityException {
        if (Objects.isNull(semilla))
            semilla = SEMILLA_DEFAULT;
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance(CIPHER);
        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(semilla.getBytes(), CIPHER_SECRET_KEY);
        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
        // Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Encryption
        byte[] cipherText = cipher.doFinal(texto.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String desencriptar(String texto) throws GeneralSecurityException {
        if (Objects.isNull(semilla))
            semilla = SEMILLA_DEFAULT;
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance(CIPHER);
        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(semilla.getBytes(), CIPHER_SECRET_KEY);

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);

        // Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Decryption
        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(texto));

        return new String(decryptedText);
    }

    public static String desencriptarCatch(String texto){
        try {
            return SecurityUtilities.desencriptar(texto);
        } catch (Exception e) {
            log.severe("Error al desencriptar el credito: ".concat(e.getMessage()));
        }
        return "";
    }

}
