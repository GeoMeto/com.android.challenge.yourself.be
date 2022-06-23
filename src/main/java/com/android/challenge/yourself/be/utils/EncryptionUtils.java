package com.android.challenge.yourself.be.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class EncryptionUtils {

    public String decrypt(String encryptedText) {
        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < encryptedText.length(); i++) {
            tmp.append((char)(encryptedText.charAt(i) - OFFSET));
        }
        String reversed = new StringBuffer(tmp.toString()).reverse().toString();
        return new String(Base64.getDecoder().decode(reversed));
    }
}
