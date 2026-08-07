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
public class DailyHistoricalWeatherData {

    @JsonProperty("time")
    private List<String> time;

    @JsonProperty("precipitation_sum")
    private List<Double> precipitationSum;

    @JsonProperty("temperature_2m_mean")
    private List<Double> temperature2mMean;

    @JsonProperty("rain_sum")
    private List<Double> rainSum;

    @JsonProperty("precipitation_hours")
    private List<Double> precipitationHours;
}
