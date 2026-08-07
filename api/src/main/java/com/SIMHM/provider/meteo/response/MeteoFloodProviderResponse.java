package com.SIMHM.provider.meteo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoFloodProviderResponse {

    @JsonProperty("generationtime_ms")
    private Double generationTimeMs;

    @JsonProperty("daily")
    private DailyFloodData daily;
}
