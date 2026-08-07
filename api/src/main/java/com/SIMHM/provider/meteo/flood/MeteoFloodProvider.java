package com.SIMHM.provider.meteo.flood;

import com.SIMHM.config.ApplicationMessages;
import com.SIMHM.controller.request.MeteoFloodRequest;
import com.SIMHM.provider.common.BaseProvider;
import com.SIMHM.provider.meteo.exception.MeteoFloodException;
import com.SIMHM.provider.meteo.response.MeteoFloodProviderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Service
public class MeteoFloodProvider extends BaseProvider {

    private final String floodEndpoint;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(MeteoFloodProvider.class);

    protected MeteoFloodProvider(
            @Value("${open-meteo.endpoints.floodEndpoint}") String floodEndpoint,
            HttpClient httpClient, ObjectMapper objectMapper) {
        super(httpClient);
        this.floodEndpoint = floodEndpoint;
        this.objectMapper = objectMapper;
    }

    public MeteoFloodProviderResponse riverDischarge(MeteoFloodRequest request) {
        if (request == null) {
            log.error(ApplicationMessages.EXCEPTION_METEO_FLOOD_REQUEST_INVALID);
            throw new MeteoFloodException(ApplicationMessages.EXCEPTION_METEO_FLOOD_REQUEST_INVALID);
        }

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(buildUri(request))
                    .GET()
                    .build();

            var response = send(httpRequest);

            validateResponse(response, ApplicationMessages.METEO_FLOOD_HTTP_ERROR,
                    () -> new MeteoFloodException(ApplicationMessages.EXCEPTION_METEO_FLOOD), log);

            return objectMapper.readValue(response.body(), MeteoFloodProviderResponse.class);

        } catch (IOException e) {
            log.error(ApplicationMessages.METEO_FLOOD_RESPONSE_ERROR, e);
            throw new MeteoFloodException(ApplicationMessages.EXCEPTION_METEO_FLOOD_RESPONSE, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(ApplicationMessages.METEO_FLOOD_INTERRUPTED, e);
            throw new MeteoFloodException(ApplicationMessages.EXCEPTION_METEO_FLOOD_INTERRUPTED, e);
        }
    }

    private URI buildUri(MeteoFloodRequest request) {
        String latitude = request.getLatitude();
        String longitude = request.getLongitude();

        String url = floodEndpoint
                + "?latitude=" + latitude
                + "&longitude=" + longitude
                + "&daily=river_discharge,river_discharge_mean,river_discharge_median&forecast_days=183";
        return URI.create(url);
    }
}
