package com.SIMHM.provider.meteo.weather;

import com.SIMHM.config.ApplicationMessages;
import com.SIMHM.controller.MeteoForecastRequest;
import com.SIMHM.controller.request.MeteoHistoricalWeatherRequest;
import com.SIMHM.provider.common.BaseProvider;
import com.SIMHM.provider.meteo.exception.MeteoWeatherException;
import com.SIMHM.provider.meteo.response.MeteoForecastProviderResponse;
import com.SIMHM.provider.meteo.response.MeteoHistoricalWeatherProviderResponse;
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
public class MeteoForecastProvider extends BaseProvider {

    private final String forecastEndpoint;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(MeteoForecastProvider.class);

    protected MeteoForecastProvider(
            @Value("${open-meteo.endpoints.forecast}") String forecastEndpoint,
            HttpClient httpClient, ObjectMapper objectMapper) {
        super(httpClient);
        this.forecastEndpoint = forecastEndpoint;
        this.objectMapper = objectMapper;
    }

    public MeteoForecastProviderResponse weatherForecast(MeteoForecastRequest request) {
        if (request == null) {
            log.error(ApplicationMessages.EXCEPTION_METEO_FORECAST_REQUEST_INVALID);
            throw new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_FORECAST_REQUEST_INVALID);
        }

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(buildUri(request))
                    .GET()
                    .build();

            var response = send(httpRequest);

            validateResponse(response, ApplicationMessages.METEO_FORECAST_HTTP_ERROR,
                    () -> new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_FORECAST), log);

            return objectMapper.readValue(response.body(), MeteoForecastProviderResponse.class);

        } catch (IOException e) {
            log.error(ApplicationMessages.METEO_FORECAST_RESPONSE_ERROR, e);
            throw new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_FORECAST_RESPONSE, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(ApplicationMessages.METEO_FORECAST_INTERRUPTED, e);
            throw new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_FORECAST_INTERRUPTED, e);
        }
    }

    private URI buildUri(MeteoForecastRequest request) {
        String latitude = request.getLatitude();
        String longitude = request.getLongitude();

        String url = forecastEndpoint
                + "?latitude=" + latitude
                + "&longitude=" + longitude
                + "&daily=temperature_2m_max,temperature_2m_min,wind_gusts_10m_max,wind_direction_10m_dominant,rain_sum,"
                + "showers_sum,precipitation_sum,precipitation_hours,precipitation_probability_max";
        return URI.create(url);
    }
}
