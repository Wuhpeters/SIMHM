package com.SIMHM.provider.common;

import org.slf4j.Logger;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public abstract class BaseProvider {

    protected final HttpClient httpClient;

    protected BaseProvider(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected HttpResponse<String> send(HttpRequest request)
            throws IOException, InterruptedException {

        return httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString());
    }

    protected boolean isSuccess(HttpResponse<?> response) {
        return response.statusCode() >= 200
                && response.statusCode() < 300;
    }

    protected String encode(String value) {
        return URLEncoder.encode(
                value == null ? "" : value,
                StandardCharsets.UTF_8);
    }

    protected void validateResponse(
            HttpResponse<?> response,
            String logMessage,
            Supplier<? extends RuntimeException> exceptionSupplier,
            Logger log) {

        if (isSuccess(response)) {
            return;
        }

        log.error("{} HTTP={}", logMessage, response.statusCode());

        throw exceptionSupplier.get();
    }
}