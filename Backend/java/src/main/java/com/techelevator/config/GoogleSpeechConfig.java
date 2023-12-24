package com.techelevator.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechSettings;

@Configuration
public class GoogleSpeechConfig {

    @Value("${google.speech.api_key}")
    private String apiKey;

    @Bean
    public SpeechClient speechClient() throws IOException {
        return SpeechClient.create(SpeechSettings.newBuilder().setCredentialsProvider(() ->
                GoogleCredentials.fromStream(
                        new ByteArrayInputStream(apiKey.getBytes(StandardCharsets.UTF_8))
                )).build());
    }
}