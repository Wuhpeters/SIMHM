package com.SIMHM.provider.meteo.geolocalization;

import com.SIMHM.controller.request.GeolocalizationRequest;
import com.SIMHM.provider.meteo.response.MeteoGeolocalizationResponse;
import com.SIMHM.provider.common.BaseProvider;
import com.SIMHM.provider.meteo.exception.MeteoGeolocalizationException;
import com.SIMHM.config.ApplicationMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class MeteoGeolocalizationProvider extends BaseProvider {

    private final String geocodingEndpoint;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(MeteoGeolocalizationProvider.class);

    public MeteoGeolocalizationProvider(
            @Value("${open-meteo.endpoints.geocoding}") String geocodingEndpoint,
            HttpClient httpClient,
            ObjectMapper objectMapper) {

        super(httpClient);
        this.geocodingEndpoint = geocodingEndpoint;
        this.objectMapper = objectMapper;
    }

    public MeteoGeolocalizationResponse coordinates(GeolocalizationRequest request) {

        if (request == null || request.getCity() == null || request.getCity().isBlank()) {
            log.error(ApplicationMessages.METEO_GEOLOC_REQUEST_INVALID);
            throw new MeteoGeolocalizationException(ApplicationMessages.METEO_GEOLOC_REQUEST_INVALID);
        }

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(buildUri(request))
                    .GET()
                    .build();

            HttpResponse<String> response = send(httpRequest);

            validateResponse(response, ApplicationMessages.METEO_GEOLOC_HTTP_ERROR,
                    () -> new MeteoGeolocalizationException(ApplicationMessages.EXCEPTION_METEO_GEOLOC + " HTTP " + response.statusCode()), log);

            return objectMapper.readValue(response.body(), MeteoGeolocalizationResponse.class);

        } catch (IOException e) {
            log.error(ApplicationMessages.METEO_GEOLOC_RESPONSE_ERROR, e);
            throw new MeteoGeolocalizationException(ApplicationMessages.METEO_GEOLOC_RESPONSE_ERROR, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(ApplicationMessages.METEO_GEOLOC_INTERRUPTED, e);
            throw new MeteoGeolocalizationException(ApplicationMessages.METEO_GEOLOC_INTERRUPTED, e);
        }
    }

    private URI buildUri(GeolocalizationRequest request) {
        String city = encode(request.getCity());

        String url = geocodingEndpoint
                + "?name=" + city
                + "&count=1&language=pt&format=json&countryCode=BR";
        return URI.create(url);
    }
}
