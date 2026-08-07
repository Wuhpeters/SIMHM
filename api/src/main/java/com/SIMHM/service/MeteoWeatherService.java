package com.SIMHM.service;

import com.SIMHM.config.ApplicationMessages;
import com.SIMHM.controller.MeteoForecastRequest;
import com.SIMHM.controller.request.MeteoHistoricalWeatherRequest;
import com.SIMHM.controller.response.MeteoForecastResponse;
import com.SIMHM.controller.response.MeteoHistoricalWeatherResponse;
import com.SIMHM.provider.meteo.exception.MeteoWeatherException;
import com.SIMHM.provider.meteo.mapper.MeteoForecastMapper;
import com.SIMHM.provider.meteo.mapper.MeteoHistoricalWeatherMapper;
import com.SIMHM.provider.meteo.response.MeteoForecastProviderResponse;
import com.SIMHM.provider.meteo.response.MeteoHistoricalWeatherProviderResponse;
import com.SIMHM.provider.meteo.weather.MeteoForecastProvider;
import com.SIMHM.provider.meteo.weather.MeteoHistoricalWeatherProvider;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MeteoWeatherService {

    private final MeteoHistoricalWeatherMapper meteoHistoricalWeatherMapper;
    private final MeteoForecastMapper meteoForecastMapper;
    private final MeteoForecastProvider meteoForecastProvider;
    private final MeteoHistoricalWeatherProvider meteoHistoricalWeatherProvider;
    private static final Logger log = LoggerFactory.getLogger(MeteoWeatherService.class);

    public MeteoHistoricalWeatherResponse getHistoricalWeather(MeteoHistoricalWeatherRequest request) {
        log.info(ApplicationMessages.METEO_HISTORICAL_WEATHER_REQUEST);

        MeteoHistoricalWeatherProviderResponse response = meteoHistoricalWeatherProvider.historicalWeather(request);

        if (response == null
                || response.getDaily() == null
                || response.getDaily().getTime() == null
                || response.getDaily().getTime().isEmpty()) {

            log.warn(ApplicationMessages.METEO_HISTORICAL_WEATHER_RESPONSE_ERROR);
            throw new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_HISTORICAL_WEATHER);
        }

        log.info(ApplicationMessages.METEO_HISTORICAL_WEATHER_SUCCESS);
        return meteoHistoricalWeatherMapper.toResponse(response);
    }

    public MeteoForecastResponse getForecast(MeteoForecastRequest request) {
        log.info(ApplicationMessages.METEO_FORECAST_REQUEST);

        MeteoForecastProviderResponse response = meteoForecastProvider.weatherForecast(request);

        if (response == null
                || response.getDaily() == null
                || response.getDaily().getTime() == null
                || response.getDaily().getTime().isEmpty()) {

            log.warn(ApplicationMessages.METEO_FORECAST_RESPONSE_ERROR);
            throw new MeteoWeatherException(ApplicationMessages.EXCEPTION_METEO_FORECAST);
        }

        log.info(ApplicationMessages.METEO_FORECAST_SUCCESS);
        return meteoForecastMapper.toResponse(response);
    }
}
