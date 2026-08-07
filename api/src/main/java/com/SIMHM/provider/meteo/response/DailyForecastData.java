package com.SIMHM.provider.meteo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyForecastData {

    @JsonProperty("time")
    private List<String> time;

    @JsonProperty("temperature_2m_max")
    private List<Double> temperatureMax;

    @JsonProperty("temperature_2m_min")
    private List<Double> temperatureMin;

    @JsonProperty("wind_gusts_10m_max")
    private List<Double> windGustsMax;

    @JsonProperty("wind_direction_10m_dominant")
    private List<Integer> windDirectionDominant;

    @JsonProperty("rain_sum")
    private List<Double> rainSum;

    @JsonProperty("showers_sum")
    private List<Double> showersSum;

    @JsonProperty("precipitation_sum")
    private List<Double> precipitationSum;

    @JsonProperty("precipitation_hours")
    private List<Double> precipitationHours;

    @JsonProperty("precipitation_probability_max")
    private List<Integer> precipitationProbabilityMax;

}
