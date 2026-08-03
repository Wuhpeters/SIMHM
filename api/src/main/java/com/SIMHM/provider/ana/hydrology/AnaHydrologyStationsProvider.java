package com.SIMHM.provider.ana.hydrology;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.SIMHM.config.ApplicationMessages;
import com.SIMHM.provider.ana.auth.AnaAuthProvider;
import com.SIMHM.provider.ana.response.AnaHydrologyStationsResponse;
import com.SIMHM.provider.common.BaseProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.SIMHM.provider.ana.exception.AnaHydrologyStationsException;
import com.SIMHM.provider.ana.mapper.AnaHydrologyStationsMapper;
import com.SIMHM.provider.ana.request.AnaHydrologyStationsRequest;

@Service
public class AnaHydrologyStationsProvider extends BaseProvider {

    private final AnaHydrologyStationsMapper mapper;
    private final AnaAuthProvider authProvider;
    private final String estacoesEndpoint;
    private static final Logger log = LoggerFactory.getLogger(AnaHydrologyStationsProvider.class);

    public AnaHydrologyStationsProvider(@Value("${ana.endpoints.estacoes}") String estacoesEndpoint,
                                        HttpClient httpClient, AnaHydrologyStationsMapper mapper,
                                        AnaAuthProvider authProvider) {
        super(httpClient);
        this.mapper = mapper;
        this.estacoesEndpoint = estacoesEndpoint;
        this.authProvider = authProvider;
    }

    public AnaHydrologyStationsResponse listStations(AnaHydrologyStationsRequest request) {

        if (request == null) {
            throw new AnaHydrologyStationsException(
                    ApplicationMessages.EXCEPTION_ANA_STATIONS_REQUEST);
        }

        log.info(ApplicationMessages.ANA_STATIONS_REQUEST);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(buildUri(request))
                .header("Authorization", "Bearer " + authProvider.getToken())
                .GET()
                .build();

        HttpResponse<String> response;

        try {
            response = send(httpRequest);
        } catch (IOException e) {

            log.error(ApplicationMessages.ANA_STATIONS_RESPONSE_ERROR, e);

            throw new AnaHydrologyStationsException(
                    ApplicationMessages.EXCEPTION_ANA_STATIONS_RESPONSE,
                    e);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            log.error(ApplicationMessages.ANA_STATIONS_INTERRUPTED, e);

            throw new AnaHydrologyStationsException(
                    ApplicationMessages.EXCEPTION_ANA_STATIONS_INTERRUPTED,
                    e);
        }

        validateResponse(response, ApplicationMessages.ANA_STATIONS_HTTP_ERROR,
                () -> new AnaHydrologyStationsException(ApplicationMessages.EXCEPTION_ANA_STATIONS + " HTTP " + response.statusCode()), log);

        log.info(ApplicationMessages.ANA_STATIONS_SUCCESS);
        return mapper.toResponse(response.body());
    }


    private URI buildUri(AnaHydrologyStationsRequest request) {
        String state = encode(request.getState());
        String basinCode = encode(request.getBasinCode());

        String url = estacoesEndpoint
                + "?Unidade%20Federativa=" + state
                + "&C%C3%B3digo%20da%20Bacia=" + basinCode;
        return URI.create(url);
    }
}
