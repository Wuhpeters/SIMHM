package com.SIMHM.provider.meteo.weather;

import com.SIMHM.config.ApplicationMessages;
import com.SIMHM.controller.request.MeteoHistoricalWeatherRequest;
import com.SIMHM.provider.common.BaseProvider;
import com.SIMHM.provider.meteo.exception.MeteoWeatherException;
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
public class MeteoHistoricalWeatherProvider extends BaseProvider {

    private final String historicalWeatherEndpoint;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(MeteoHistoricalWeatherProvider.class);

    protected MeteoHistoricalWeatherProvider(
            @Value("${open-meteo.endpoints.historicalWeather}") String historicalWeatherEndpoint,
            HttpClient httpClient, ObjectMapper objectMapper) {
        super(httpClient);
        this.historicalWeatherEndpoint = historicalWeatherEndpoint;
        this.objectMapper = objectMapper;
    }

    public MeteoHistoricalWeatherProviderResponse historicalWeather(MeteoHistoricalWeatherRequest request) {
        if (request == null) {
            log.error(ApplicationMessages.EXCEPTION_METEO_HISTORICAL_WEATHER_REQUEST_INVALID);
            throw new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_HISTORICAL_WEATHER_REQUEST_INVALID);
        }

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(buildUri(request))
                    .GET()
                    .build();

            var response = send(httpRequest);

            validateResponse(response, ApplicationMessages.METEO_HISTORICAL_WEATHER_HTTP_ERROR,
                    () -> new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_HISTORICAL_WEATHER), log);

            return objectMapper.readValue(response.body(), MeteoHistoricalWeatherProviderResponse.class);

        } catch (IOException e) {
            log.error(ApplicationMessages.METEO_HISTORICAL_WEATHER_RESPONSE_ERROR, e);
            throw new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_HISTORICAL_WEATHER_RESPONSE, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(ApplicationMessages.METEO_HISTORICAL_WEATHER_INTERRUPTED, e);
            throw new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_HISTORICAL_WEATHER_INTERRUPTED, e);
        }
    }

    private URI buildUri(MeteoHistoricalWeatherRequest request) {
        String latitude = request.getLatitude();
        String longitude = request.getLongitude();
        String startDate = request.getStartDate();
        String endDate = request.getEndDate();

        String url = historicalWeatherEndpoint
                + "?latitude=" + latitude
                + "&longitude=" + longitude
                + "&start_date=" + startDate
                + "&end_date=" + endDate
                + "&daily=precipitation_sum,temperature_2m_mean,rain_sum,precipitation_hours";
        return URI.create(url);
    }
}
