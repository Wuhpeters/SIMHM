package com.SIMHM.provider.meteo.geolocalization;

import com.SIMHM.controller.request.GeolocalizationRequest;
import com.SIMHM.provider.meteo.response.MeteoGeolocalizationResponse;
import com.SIMHM.provider.meteo.exception.MeteoGeolocalizationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeteoGeolocalizationProviderTest {

    @Mock
    private HttpClient httpClient;

    @SuppressWarnings("unchecked")
    @Mock
    private HttpResponse<String> httpResponse;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldThrowWhenRequestIsNullOrBlank() {
        var provider = new MeteoGeolocalizationProvider("https://api.test/geocoding", httpClient, objectMapper);

        assertThrows(MeteoGeolocalizationException.class, () -> provider.coordinates(null));
        assertThrows(MeteoGeolocalizationException.class, () -> provider.coordinates(new GeolocalizationRequest("  ")));
    }

    @Test
    void shouldReturnResponseWhenHttpSuccess() throws Exception {
        var provider = new MeteoGeolocalizationProvider("https://api.test/geocoding", httpClient, objectMapper);

        String body = "{\"results\":[{\"name\":\"Recife\",\"admin1\":\"Pernambuco\",\"country\":\"BR\",\"latitude\":-8.0,\"longitude\":-34.9}],\"generationtime_ms\":1.0}";

        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(body);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        MeteoGeolocalizationResponse resp = provider.coordinates(new GeolocalizationRequest("Recife"));

        assertNotNull(resp);
        assertFalse(resp.getResults().isEmpty());
        assertEquals("Recife", resp.getResults().get(0).getName());
    }

    @Test
    void shouldThrowWhenHttpErrorStatus() throws Exception {
        var provider = new MeteoGeolocalizationProvider("https://api.test/geocoding", httpClient, objectMapper);

        when(httpResponse.statusCode()).thenReturn(500);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        assertThrows(MeteoGeolocalizationException.class, () -> provider.coordinates(new GeolocalizationRequest("Recife")));
    }

    @Test
    void shouldThrowWhenInvalidJson() throws Exception {
        var provider = new MeteoGeolocalizationProvider("https://api.test/geocoding", httpClient, objectMapper);

        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("not a json");
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        assertThrows(MeteoGeolocalizationException.class, () -> provider.coordinates(new GeolocalizationRequest("Recife")));
    }
}
