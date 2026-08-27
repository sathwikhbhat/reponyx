package com.sathwikhbhat.reponyx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.AesCbcBytesEncryptor;
import org.springframework.security.crypto.encrypt.BytesEncryptor;

@Configuration
public class CryptoConfig {

    @Bean
    BytesEncryptor tokenEncryptor(
            @Value("${app.token.encryptor.password}") String password,
            @Value("${app.token.encryptor.salt}") String salt) {
        return AesCbcBytesEncryptor
                .withPassword(password, salt)
                .build();
    }
}
