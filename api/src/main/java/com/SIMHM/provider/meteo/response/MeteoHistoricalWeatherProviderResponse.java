package com.SIMHM.provider.meteo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoHistoricalWeatherProviderResponse {

    @JsonProperty("generationtime_ms")
    private Double generationTimeMs;

    @JsonProperty("daily")
    private DailyHistoricalWeatherData daily;
}
