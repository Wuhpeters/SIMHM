package com.SIMHM.provider.ana.auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.SIMHM.provider.ana.exception.AnaAuthException;
import com.SIMHM.provider.ana.response.AnaAuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AnaAuthProvider {

    private final String authEndpoint;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String username;
    private final String password;
    private volatile String token;
    private volatile Instant expiry;
    private static final Logger log = LoggerFactory.getLogger(AnaAuthProvider.class);

    public AnaAuthProvider(
            @Value("${ana.endpoints.auth}") String authEndpoint,
            HttpClient httpClient,
            ObjectMapper objectMapper,
            @Value("${ana.username}") String username,
            @Value("${ana.password}") String password) {

        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.username = username;
        this.password = password;
        this.authEndpoint = authEndpoint;
    }

    public synchronized String getToken() {
        if (isTokenExpired()) {
            AnaAuthResponse response = fetchToken(username, password);
            if (response == null
                    || response.getItems() == null
                    || response.getItems().getTokenautenticacao() == null
                    || response.getItems().getTokenautenticacao().isBlank()) {

                throw new AnaAuthException("Falha ao obter token da ANA");
            }

            token = response.getItems().getTokenautenticacao();
            expiry = Instant.now().plusSeconds(3600);
        }

        return token;
    }

    protected boolean isTokenExpired() {
        return token == null || expiry == null || Instant.now().isAfter(expiry);
    }

    protected AnaAuthResponse fetchToken(String username, String password) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(authEndpoint))
                    .header("Identificador", username)
                    .header("Senha", password)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new AnaAuthException("Falha ao autenticar na ANA. HTTP " + response.statusCode());
            }

            return objectMapper.readValue(response.body(), AnaAuthResponse.class);
        } catch (IOException e) {
            throw new AnaAuthException("Erro ao processar resposta da ANA", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AnaAuthException("Requisição para a ANA interrompida", e);
        }
    }
}