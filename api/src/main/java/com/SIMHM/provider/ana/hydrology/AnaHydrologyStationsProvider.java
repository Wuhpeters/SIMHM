package com.SIMHM.provider.ana.hydrology;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.SIMHM.provider.ana.auth.AnaAuthProvider;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.SIMHM.provider.ana.exception.AnaHydrologyStationsException;
import com.SIMHM.provider.ana.mapper.AnaHydrologyStationsMapper;
import com.SIMHM.provider.ana.request.AnaHydrologyStationsRequest;

@Service
public class AnaHydrologyStationsProvider {

    private final HttpClient httpClient;
    private final AnaHydrologyStationsMapper mapper;
    private final AnaAuthProvider authProvider;
    private final String estacoesEndpoint;

    public AnaHydrologyStationsProvider(@Value("${ana.endpoints.estacoes}") String estacoesEndpoint,
                                        HttpClient httpClient, AnaHydrologyStationsMapper mapper,
                                        AnaAuthProvider authProvider) {
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.estacoesEndpoint = estacoesEndpoint;
        this.authProvider = authProvider;
    }

    public AnaHydrologyStationsResponse listStations(AnaHydrologyStationsRequest request) {
        if (request == null) {
            throw new AnaHydrologyStationsException("Requisição da ANA não pode ser nula");
        }

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(buildUri(request))
                .header("Authorization", "Bearer " + authProvider.getToken())
                .GET()
                .build();

        HttpResponse<String> response = send(httpRequest);
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new AnaHydrologyStationsException("Falha ao consultar estações hidrológicas da ANA. HTTP "
                    + response.statusCode());
        }

        return mapper.toResponse(response.body());
    }

    protected HttpResponse<String> send(HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new AnaHydrologyStationsException("Erro ao chamar a ANA", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AnaHydrologyStationsException("Requisição para a ANA interrompida", e);
        }
    }

    private URI buildUri(AnaHydrologyStationsRequest request) {
        String state = encode(request.getState());
        String basinCode = encode(request.getBasinCode());

        String url = estacoesEndpoint
                + "?Unidade%20Federativa=" + state
                + "&C%C3%B3digo%20da%20Bacia=" + basinCode;
        return URI.create(url);
    }

    private String encode(String value) {
        return URLEncoder.encode(value == null ? "" : value, StandardCharsets.UTF_8);
    }
}
