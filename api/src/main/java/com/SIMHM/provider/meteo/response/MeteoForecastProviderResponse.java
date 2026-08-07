package com.SIMHM.provider.meteo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoForecastProviderResponse {

    @JsonProperty("generationtime_ms")
    private Double generationTimeMs;

    @JsonProperty("daily")
    private DailyForecastData daily;
}
