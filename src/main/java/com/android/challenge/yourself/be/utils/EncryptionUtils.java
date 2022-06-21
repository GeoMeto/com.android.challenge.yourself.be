package com.android.challenge.yourself.be.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@Component
public class EncryptionUtils {
    private Cipher cipher;
    private final static String SECRET_KEY = "Challenge!";

    public EncryptionUtils() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance("AES");
    }

//    public String encrypt(String plainText) throws Exception {
//        byte[] plainTextByte = plainText.getBytes();
//        cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
//        byte[] encryptedByte = cipher.doFinal(plainTextByte);
//        Base64.Encoder encoder = Base64.getEncoder();
//        String encryptedText = encoder.encodeToString(encryptedByte);
//        return encryptedText;
//    }

    public String decrypt(String encryptedText) throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128);
//        SecretKey secretKey = keyGenerator.generateKey();
//        Base64.Decoder decoder = Base64.getDecoder();
//        byte[] encryptedTextByte = decoder.decode(encryptedText);
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
//        String decryptedText = new String(decryptedByte);
//        return decryptedText;
        return encryptedText;
    }
}
