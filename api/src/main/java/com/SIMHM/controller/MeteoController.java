package com.SIMHM.controller;

import com.SIMHM.controller.request.MeteoFloodRequest;
import com.SIMHM.controller.request.MeteoHistoricalWeatherRequest;
import com.SIMHM.controller.response.MeteoFloodResponse;
import com.SIMHM.controller.response.MeteoHistoricalWeatherResponse;
import com.SIMHM.service.MeteoFloodService;
import com.SIMHM.service.MeteoWeatherService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Builder
@RestController
@RequestMapping("/meteo")
public class MeteoController {

    private final MeteoFloodService meteoFloodService;
    private final MeteoWeatherService meteoWeatherService;

    @GetMapping("/flood")
    public MeteoFloodResponse getLocalization(MeteoFloodRequest localization) {
        return meteoFloodService.getRiverDischarge(localization);
    }

    @GetMapping("/historical-weather")
    public MeteoHistoricalWeatherResponse getHistoricalWeather(MeteoHistoricalWeatherRequest request) {
       return meteoWeatherService.getHistoricalWeather(request);
    }
}
