package com.SIMHM.provider.meteo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoGeolocalizationResponse {

    @JsonProperty("results")
    private List<GeolocalizationLocation> results = new ArrayList<>();
    @JsonProperty("generationtime_ms")
    private Double generationTimeMs;
}
