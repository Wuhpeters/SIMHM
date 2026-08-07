package com.SIMHM.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoHistoricalWeatherDataResponse  {

    private String date;

    private Double precipitationSum;

    private Double temperatureMean;

    private Double rainSum;

    private Double precipitationHours;
}