package com.zerobase.loanService.common.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class EncryptionUtil {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final byte[] KEY;

    static {
        String keyEnv = System.getenv("ENCRYPTION_KEY");
        if (keyEnv == null || keyEnv.length() != 16) {
            throw new IllegalArgumentException("ENCRYPTION_KEY must be set and must be exactly 16 bytes long.");
        }
        KEY = keyEnv.getBytes(StandardCharsets.UTF_8);
    }

    public String encrypt(String data) throws Exception {
        try {
            // Generate a new IV for each encryption operation
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            // Encode IV and encrypted data together
            String ivString = Base64.getEncoder().encodeToString(iv);
            String encryptedString = Base64.getEncoder().encodeToString(encrypted);
            return ivString + ":" + encryptedString;
        } catch (Exception e) {
            throw new Exception("Encryption failed", e);
        }
    }

    public String decrypt(String encryptedData) throws Exception {
        try {
            // Split the IV and encrypted data
            String[] parts = encryptedData.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid encrypted data format");
            }
            String ivString = parts[0];
            String encryptedString = parts[1];

            byte[] iv = Base64.getDecoder().decode(ivString);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("Decryption failed", e);
        }
    }
}
