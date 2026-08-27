package com.sathwikhbhat.reponyx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class CryptoService {

    private final BytesEncryptor encryptor;

    public String encrypt(String value) {
        if (value == null) {
            return null;
        }

        byte[] encrypted = encryptor.encrypt(value.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String value) {
        if (value == null) {
            return null;
        }

        byte[] encrypted = Base64.getDecoder().decode(value);
        byte[] decrypted = encryptor.decrypt(encrypted);

        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
